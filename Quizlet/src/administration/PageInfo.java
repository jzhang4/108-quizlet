package administration;

import java.util.ArrayList;

public class PageInfo {
	public String pageName;
	public int numMth1, numMth2, numMth3, numMth4, numMth5;
	private ArrayList<Integer> values;
	public PageInfo(String nameOfPage, int num1,int num2, int num3, int num4, int num5){
		pageName = nameOfPage;
		values = new ArrayList<Integer>();
		values.add(num1);
		values.add(num2);
		values.add(num3);
		values.add(num4);
		values.add(num5);
	}
	@Override
	public String toString(){
		String temp = pageName + ": " + Integer.toString(numMth1) + ", " + Integer.toString(numMth2) + ", " + Integer.toString(numMth3)+ ", " + Integer.toString(numMth4);
		return temp;	
	}
	public String fetchFreq(int query){
		return Integer.toString(values.get(query));
	}
}
