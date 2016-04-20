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
		int[] times = new int[26];
		int count = 0;
		String cleanString = receive.replaceAll("[(),]", "");//getting rid of unnecessary string characters
		
		//Variables used in creating the condensed meeting blocks
		int start = 0;
		int i, j, k;
		int[] storeTimes = new int[26];
		ArrayList<Integer> newTimes = new ArrayList<Integer>();
		String reply = "";
		
		//Pulling the times out of the cleanString and storing them in times[]
		Scanner scanString = new Scanner(cleanString);
		while(scanString.hasNext())
		{
			times[count] = Integer.parseInt(scanString.next());
			count++;
		}
		scanString.close();
		
		/*Using the times[] array, the storeTimes[] array is being populated with either
		1s or 2s corresponding to the time blocks of the meetings*/
		for(i = 0; i < count; i += 2)
		{
			for(j = times[i]; j <= times[i+1]; j++)
			{
				if(j == times[i+1] && (storeTimes[j+10] != 1))
					storeTimes[j+10] = 2;
				else
					storeTimes[j+10] = 1;
			}
		}
		
		/*Now using the storeTimes[] an ArrayList called newTimes will be populated with
		the actual numeric times of the scheduled meetings*/
		for(k = 10; k < storeTimes.length; k++)
		{
			if((storeTimes[k] == 1) && ((storeTimes[k-1] == 0) || (storeTimes[k-1] == 2)))
				start = k-10;
			else if(storeTimes[k] == 2)
			{
				newTimes.add(start);
				newTimes.add(k-10);
			}
		}
		
		/*Using the data from newTimes the string reply will be formatted with the 
		condensed times*/
		for(k = 0; k < newTimes.size()-1; k++)
		{
			if(k%2 == 0)
				reply += "("+newTimes.get(k)+", ";
			else
				reply += newTimes.get(k)+"), ";
		}
		reply += newTimes.get(newTimes.size()-1)+")";
		

		return reply;
	}//End of condensedMeetingTimes method
	
	public static void main(String[] args)
	{
		Meeting s1 = new Meeting(0,1);
		Meeting s2 = new Meeting(3,5);
		Meeting s3 = new Meeting(4,8);
		Meeting s4 = new Meeting(10,12);
		Meeting s5 = new Meeting(9,10);
		String sender = s1.toString() + ", " + s2.toString() + ", " + s3.toString() + ", " + s4.toString() + ", " + s5.toString();
		String returned = condenseMeetingTimes(sender);
		System.out.println(returned);
	}
}
