/********************************************************************************************************************************
Zac Hollingsworth
4/19/2016

Interview Cake
https://www.interviewcake.com/question/java/merging-ranges

Problem:
Your company built an in-house calendar tool called HiCal. You want to 
add a feature to see the times in a day when everyone is available.

To do this, you’ll need to know when any team is having a meeting. In HiCal, a meeting is stored as an object of a Meeting class 
with integer variables startTime and endTime. These integers represent the number of 30-minute blocks past 9:00am.

Write a function condenseMeetingTimes() that takes a list of meeting time ranges and returns a list of condensed ranges.
********************************************************************************************************************************/
import java.util.Scanner;

public class Main 
{
	public static String condenseMeetingTimes(String receive)
	{
		int[] times = new int[(1 + ((receive.length()-5) / 8)) * 2];
		int count = 0;
		String cleanString = receive.replaceAll("[(),]", "");
		Scanner scanString = new Scanner(cleanString);
		
		while(scanString.hasNext())
		{
			times[count] = Integer.parseInt(scanString.next());
			count++;
		}
		
		for(int i = 0; i < times.length; i++)
		{
			System.out.println(times[i]);
		}
		
		scanString.close();
		return receive;
	}
	
	
	
	public static void main(String[] args)
	{
		Meeting s1 = new Meeting(0,1);
		Meeting s2 = new Meeting(3,5);
		Meeting s3 = new Meeting(4,8);
		Meeting s4 = new Meeting(10,12);
		Meeting s5 = new Meeting(9,10);
		String sender = s1.toString() + ", " + s2.toString() + ", " + s3.toString() + ", " + s4.toString() + ", " + s5.toString();
		condenseMeetingTimes(sender);
		
	}
}
