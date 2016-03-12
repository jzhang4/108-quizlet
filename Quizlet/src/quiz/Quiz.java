package quiz;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import user.DBConnection;




public class Quiz {
	
	private ArrayList<Question> questions; 
	
	private String name; 
	
	private String description; 
	
	private boolean random; 
	
	private boolean multipage; 
	
	private boolean quickcorrect;
	
	private boolean practicemode; 
	
	public static Quiz readXML(String XMLfile) {
		Quiz quiz = null; 
		
		try {
			File fXmlFile = new File(XMLfile);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder =  dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			boolean random = Boolean.getBoolean(doc.getDocumentElement().getAttribute("random"));
			boolean multi = !Boolean.getBoolean(doc.getDocumentElement().getAttribute("one-page"));
			boolean practicemode = Boolean.getBoolean(doc.getDocumentElement().getAttribute("practice-mode"));
			
			String quizname = doc.getElementsByTagName("title").item(0).getTextContent();
			String description = doc.getElementsByTagName("description").item(0).getTextContent();
			
			quiz = new Quiz(quizname, description);
			quiz.setRandom(random);
			quiz.setPractice(practicemode);
			quiz.setPage(multi);
			
			NodeList nList = doc.getElementsByTagName("question");
			
			for (int i = 0; i < nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					String type = eElement.getAttribute("type");
					
					Question ques = null; 
					
					String name = "";
					
					Answer answerobj = null; 
					
					ArrayList<String> answers = new ArrayList<String>(); 
					
					switch (type) {

					case "multiple-choice":
						name = eElement.getElementsByTagName("query").item(0).getTextContent();
						NodeList choicenodes = eElement.getElementsByTagName("option");
						ArrayList<String> choices = new ArrayList<String>();
						String answer = "";
						for (int j = 0; j < choicenodes.getLength(); j++) {
							Node choicenode = choicenodes.item(j);
							if (choicenode.getNodeType() == Node.ELEMENT_NODE) {
								Element choiceElement = (Element) choicenode;
								choices.add(choiceElement.getTextContent());
								if (choiceElement.getAttribute("answer").equals("answer")) {
									answer = choiceElement.getTextContent();
								}
							}
						}
						
						ques = new MCQuestion(name, answer, choices);
						
						break;
					case "fill-in-blank":
						Element queselm = (Element)eElement.getElementsByTagName("blank-query").item(0);
						String pre = queselm.getElementsByTagName("pre").item(0).getTextContent();
						String post = queselm.getElementsByTagName("post").item(0).getTextContent();
						name = pre + " ______ " + post; 
						
						if (eElement.getElementsByTagName("answer-list").getLength() == 0) {
							answers.add(eElement.getElementsByTagName("answer").item(0).getTextContent());
						} else {
							Element answerelm = (Element)eElement.getElementsByTagName("answer-list").item(0);
							NodeList answernodes = answerelm.getElementsByTagName("answer");

							for (int j = 0; j < answernodes.getLength(); j++) {
								Node answernode = answernodes.item(j);
								if (answernode.getNodeType() == Node.ELEMENT_NODE) {
									Element answerElement = (Element) answernode;
									answers.add(answerElement.getTextContent());
								}
							}
						}
						answerobj = new Answer(answers);
						ques = new FBQuestion(name, answerobj); 
						
						break;
					case "picture-response":
						name = eElement.getElementsByTagName("image-location").item(0).getTextContent();
						if (eElement.getElementsByTagName("answer-list").getLength() == 0) {
							answers.add(eElement.getElementsByTagName("answer").item(0).getTextContent());
						} else {
							Element answerelm = (Element)eElement.getElementsByTagName("answer-list").item(0);
							NodeList answernodes = answerelm.getElementsByTagName("answer");

							for (int j = 0; j < answernodes.getLength(); j++) {
								Node answernode = answernodes.item(j);
								if (answernode.getNodeType() == Node.ELEMENT_NODE) {
									Element answerElement = (Element) answernode;
									answers.add(answerElement.getTextContent());
								}
							}
						}
						answerobj = new Answer(answers);
						ques = new PRQuestion(name, answerobj); 
						
						break;
					case "question-response":
						name = eElement.getElementsByTagName("query").item(0).getTextContent();
						if (eElement.getElementsByTagName("answer-list").getLength() == 0) {
							answers.add(eElement.getElementsByTagName("answer").item(0).getTextContent());
						} else {
							Element answerelm = (Element)eElement.getElementsByTagName("answer-list").item(0);
							NodeList answernodes = answerelm.getElementsByTagName("answer");

							for (int j = 0; j < answernodes.getLength(); j++) {
								Node answernode = answernodes.item(j);
								if (answernode.getNodeType() == Node.ELEMENT_NODE) {
									Element answerElement = (Element) answernode;
									answers.add(answerElement.getTextContent());
								}
							}
						}
						answerobj = new Answer(answers);
						ques = new QRQuestion(name, answerobj); 
						
						break;
					} 
					
					quiz.addQuestion(ques);
					
				}
				
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(JSONCreator.jsonQuiz(quiz).toString());
		
		return quiz;
	}
	
	public Quiz(String name, String description) {
		questions = new ArrayList<Question>(); 
		this.name = name; 
		this.description = description; 
		this.random = false; 
	}
	
	public void setRandom(boolean bool) {
		this.random = bool; 
	}
	
	public void shuffleQuiz() {
		Collections.shuffle(questions);
	}
	
	public boolean isRandom() {
		return this.random; 
	}
	
	public void setPage(boolean bool) {
		this.multipage = bool; 
	}
	public boolean isMulti() {
		return this.multipage; 
	}
	
	public void setCorrect(boolean bool) {
		this.quickcorrect = bool; 
	}
	public boolean isQuickCorrect() {
		return this.quickcorrect; 
	}
	
	public void setPractice(boolean bool) {
		this.practicemode = bool; 
	}
	public boolean isPractice() {
		return this.practicemode; 
	}
	
	public int getSize() {
		return questions.size();
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	public void removeQuestion(Question question) {
		questions.remove(question);
	}
	public Question getQuestion(int index) {
		return questions.get(index-1);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Iterator<Question> iterator() {
		return new QuizIterator(); 
	}
	
	//Nested Inner class to iterate through quiz question
	private class QuizIterator implements Iterator<Question> {
		int index; 
		
		public QuizIterator() {
			index = 0; 
		}
		
		@Override
		public boolean hasNext() {
			if (index < questions.size()) return true; 
			return false;
		}

		@Override
		public Question next() {
			return questions.get(index++);
		}
		
	}
	
	public void printStr() {
		System.out.println(this.getName());
		System.out.println(this.getDescription());
		Iterator<Question> it = this.iterator();
		while (it.hasNext()) {
			Question q = it.next();
			String ques = q.getQuestion();
			System.out.println(ques);
			ArrayList<String> answers = q.getAnswer().getAnswer();
			for (String str : answers) {
				System.out.println(str);
			}
		}
	}

	public static void loadXML(String XMLfile) {
		DBConnection connect = new DBConnection();
		PreparedStatement pstmt = connect.getPreparedStatement();
		
		Statement stmt = connect.getStatement();
		
		Quiz quiz = readXML(XMLfile);
		
		JSONObject obj = JSONCreator.jsonQuiz(quiz);
		StringWriter out = new StringWriter(); 
		try {
			obj.writeJSONString(out);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String jsonText = out.toString();
		InputStream in = new ByteArrayInputStream(jsonText.getBytes());
		String name = quiz.getName();
		String username = "xmlcreator"; 
		long time = System.currentTimeMillis();
		
		ScoreBoard sb = new ScoreBoard(); 
		byte[] sbytes = sb.boardToBytes();
		
		try {
			stmt.executeUpdate("DELETE FROM quizzes WHERE name = \"" + name +"\"");
			pstmt.setString(1, username);
			pstmt.setString(2, name);
			pstmt.setLong(3, 0);
			pstmt.setLong(4, time);
			pstmt.setBinaryStream(5, in);
			pstmt.setLong(6, 0);
			pstmt.setBytes(7, sbytes);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connect.closeConnection();
	}
	
	public static void main(String[] args) {
		//loadXML("bunny.xml");
		//loadXML("cities.xml");
		//loadXML("continents.xml");
		//loadXML("OscarWinningActors.xml");
		//loadXML("stanford.xml");
		//loadXML("text2.xml");
		//loadXML("WWIIgenerals.xml");
	}
}
