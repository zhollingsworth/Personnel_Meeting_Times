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
import java.util.ArrayList;

public class Main 
{
	public static String condenseMeetingTimes(String receive)
	{
		//Variables related to parsing the string and storing the integers in an int[]
		int[] times = new int[(1 + ((receive.length()-5) / 8)) * 2];
		int count = 0;
		String cleanString = receive.replaceAll("[(),]", "");
		
		//Variables used in creating the condensed meeting blocks
		int start;
		int end;
		ArrayList<Integer> newTimes = new ArrayList<Integer>();
		String reply = "";
		
		//Pulling times out of string and storing in array
		Scanner scanString = new Scanner(cleanString);
		while(scanString.hasNext())
		{
			times[count] = Integer.parseInt(scanString.next());
			count++;
		}
		scanString.close();
		
		//Cycling through and arranging meeting times
		start = times[0];
		end = times[1];
		
		for(int i = 2; i < times.length; i += 2)
		{
			if(times[i] < start && times[i+1] >= times[i])
			{
				if(times[i+1] > end)
					end = times[i+1];
				if(times[i] < start)
					start = times[i];
				i += 2;
			}
			
			else if(times[i] > start && times[i] <= times[i-1] && times[i+1] > end)
			{
				end = times[i+1];
				i += 2;
			}
			
			else if(times[i] > start && times[i] <= times[i-1] && times[i+1] < times[i-1])
			{
				end = times[i-1];
				i += 2;
			}
			
			newTimes.add(start);
			newTimes.add(end);
			if((i+2) < times.length)
			{
				start = times[i];
				end = times[i+1];
			}
		}
		
		//Preparing string to return with finalized data
		for(int j = 0; j < newTimes.size()-1; j++)
		{
			if(j%2 == 0)
				reply += "("+newTimes.get(j)+", ";
			else
				reply += newTimes.get(j)+"), ";
		}
		reply += newTimes.get(newTimes.size()-1)+")";
		
		return reply;
	}
	
	
	
	public static void main(String[] args)
	{
		Meeting s1 = new Meeting(1,5);
		Meeting s2 = new Meeting(2,3);
		Meeting s3 = new Meeting(4,8);
		Meeting s4 = new Meeting(10,12);
		Meeting s5 = new Meeting(9,10);
		String sender = s1.toString() + ", " + s2.toString() + ", " + s3.toString() + ", " + s4.toString() + ", " + s5.toString();
		String returned = condenseMeetingTimes(sender);
		System.out.println(returned);
	}
}
