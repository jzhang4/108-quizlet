package administration;

import java.util.ArrayList;

public class PageInfo {
	public String pageName;
	public int day1, day2, day3, day4, day5,day6,day7;
	private ArrayList<Integer> values;
	public PageInfo(String nameOfPage, int num1,int num2, int num3, int num4, int num5,int num6,int num7){
		pageName = nameOfPage;
		values = new ArrayList<Integer>();
		values.add(num1);
		values.add(num2);
		values.add(num3);
		values.add(num4);
		values.add(num5);
		values.add(num6);
		values.add(num7);
	}
	@Override
	public String toString(){
		String temp = pageName + ": " + Integer.toString(day1) + ", " + Integer.toString(day2) + ", " + Integer.toString(day3)+ ", " + Integer.toString(day4);
		return temp;	
	}
	public String fetchFreq(int query){
		return Integer.toString(values.get(query));
	}
}
