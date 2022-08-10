// Ishaan bhattacharya  Greedy lab 1
import java.util.*;


public class Greedypq {
	
public static void main(String args[]) {
	//all variables
	int n; //number of students coming to office hours
	int m; //number of hours Guha is holding
	int answer=0; //counter to hold answer
	int x,y; //temporary to hold input of start time and duration
	int starting_index, ending_index; // used to insert students into an array at optimal times
	Student a1; //temporary object to hold other objects during priority queue poll and peek
	
	
	Scanner sc = new Scanner(System.in); //scanner
	//get input
	n = sc.nextInt(); 
	m = sc.nextInt();
	m = m * 60; //convert hours to minutes
	
	//PQ settings
	Comparator<Student> timesorter = Comparator.comparingInt(Student::gettotal_time); //very interesting sorts the pq based on the variable total_time in class 
	PriorityQueue<Student> pQ = new PriorityQueue<Student>(100, timesorter); //make a pq of size 100 and use the timesorter as the sort/ thank god i learned timesort form another class
	
	//object array to easily hold the students
	Student [] arr_to_hold_students = new Student[n];
	
	//getting input from table or user
	for(int i=0; i<n; i++) {
		x = sc.nextInt();
		y = sc.nextInt();
		arr_to_hold_students[i] = new Student(x,y); 
	}
	
		//add at the objects to the PQ
		for(int i=0; i<n; i++) {
			pQ.add(arr_to_hold_students[i]);
		}
			//close scanner not taking in anymore input
			sc.close();
			
			//make second array of size minutes arup is holding hours/5 and initalize it all to 0
			int [] arr2 = new int[m/5];
			for(int i=0; i< (m/5); i++) {
				arr2[i]=0;
			}
			
				// so peek the top element if the array is empty then end.
				// if not then check the top of the PQ and input if can
				// if u cannot input then just poll and go to the next element and try again.
				for(int i=0; i<n; i++) {
					a1 = pQ.peek();
							
					if(pQ.isEmpty()) {
						break;
						}
		
						starting_index = a1.enterpoint / 5 ;
						ending_index = a1.total_time / 5;
		
							for(int j = starting_index; j<ending_index; j++) {
								if(arr2[j]==0) {
									arr2[j]++;
									break;
											}
											}
					         pQ.poll();
									}
				
					
								//go through the array and tally up ones to get the answer.
								for(int i=0; i< (m/5); i++) {
									if(arr2[i]==1) {
										answer++;
									}
									
								}
	
	                               	//print out the answer nicely
									System.out.println("The Number of students that can be served is " + answer);
									//print out the spots where students are served in the time interval.
									for(int i=0; i< (m/5); i++) {
										if(arr2[i]==0) {
											System.out.println("No Student was Served from " + i*5 + " to " + (i+1)*5 +" Minutes");
														}
										if(arr2[i]==1) {
											System.out.println("A Student was Served from " + i*5 + " to " + ((i+1)*5) + " Minutes");
										}
															}
									
								}



}

//class that is used to have the student object.
class Student implements Comparator<Student>  {
	public int enterpoint, duration, total_time;
	
	public Student(int enter,int timestayed) {
		enterpoint = enter;
		duration = timestayed;
		total_time = enterpoint + duration;
	}
	public int getx() {
		return enterpoint;
	}
	public int gety() {
		return duration;
	}
	public int gettotal_time() {
		return total_time;
	}
	@Override
	//custom comparator yea ik how to do these.
	public int compare(Student o1, Student o2) {
		if(o1.total_time < o2.total_time) {
			return 1;
		}
		if(o1.total_time > o2.total_time) {
			return -1;
		}
		return 0;
	}
	
}
	




