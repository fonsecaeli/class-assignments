//Eli F.
//Section C
//Assignment 9
//Description: client program that uses Student Object to simulate a school of sorts 
//Version 1.0
//Class Name: School
//2/6/16

import java.util.*;

public class School {

	/**
	* Maximum grade level for a student
	*/
	public static final int MAX_GRADE = 12;
 	
	/**
	* Minimum grade level for a student
	*/
	public static final int MIN_GRADE = 9;

	/**
	* Maximum number of classes a student can take
	*/
	public static final int MAX_CLASSES = 8;

	/**
	* Minimum number of classes a student can take
	*/
	public static final int MIN_CLASSES = 1;

	/**
	* The entry point into the program, where it all begins
	*
	* @param args user input from the console
	*/
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Student student1 = createStudent(input);
		int courseNumStudent1 = getCourseNum(input); //get the courses student 1 is taking
		setupCourses(courseNumStudent1, student1, input);

		System.out.println();

		Student student2 = createStudent(input);
		int courseNumStudent2 = getCourseNum(input); //get the courses student 2 is taking
		setupCourses(courseNumStudent2, student2, input);

		System.out.println();

		studentInfo(student1);
		studentInfo(student2);
     
		commonClasses(student1, student2);

		//Extension code: 
		//Scanner fileScan = Inputter.getScanner(input, "What is the name of the file containing 
		//the list of Courses");
		//ArrayList<String> courses = generateCourseList(fileScan);
	}
   
   /**
   * Prints out information about each student
   *
   * @param student the student who's info is to be printed
   */
   public static void studentInfo(Student student) {
		System.out.println("Student "+student.getFullName()+
			" is taking courses "+Arrays.deepToString(student.getCourses()));  
   }

   /**
   * calculates the common classes between two students and then prints this information
   *
   * @param student1 the first student to be compared
   * @param student2 the second student being compared
   */
	public static void commonClasses(Student student1, Student student2) {
      String commonClasses = "";
      //get information about students schedules
      String[] student1Courses = student1.getCourses();
		String[] student2Courses = student2.getCourses();
      int stud1Classes = student1Courses.length;
      int stud2Classes = student2Courses.length;
      //so no IndexOutofBoundsException is thrown
      int maxIndex = (stud1Classes >= stud2Classes) ? stud1Classes : stud2Classes; 
      int common = 0;
      //find common courses and save info in a formatted String 
	   for(int ii = 0;  ii < maxIndex; ii++) {
         int periodNum = ii+1;
			if(student1Courses[ii].equalsIgnoreCase(student2Courses[ii])) {
         	commonClasses += String.format("%4s - %s", periodNum, student1Courses[ii]+"\n");
         	common++;
         }
      }
      if(common == 0) {
      	commonClasses = "The students have no shared classes.\n"+commonClasses;
      }
      else if(common == 1) {
      	commonClasses = "The students have the following class in common:\n"+commonClasses;
      }
      else if(common > 1) {
      	commonClasses = "The students have the following classes in common:\n"+commonClasses;
      }
		System.out.println(commonClasses); 
	}	

	/**
	* allows the user to set the courses a student is taking
	*
	* @param courses the number of course the student is taking
	* @param student the students whos courses will be set
	* @param input Scanner Object used to get input from the user
	*/
	
	public static void setupCourses(int courses, Student student, Scanner input) {
		for(int ii = 1;  ii <= courses; ii++) {
			String courseName = Inputter.getName(input, "Course "+(ii)+" name? ");
			//Extension code, courseList would be a param of this method
			//while(validateCourse(courseName()), courseList) {
			//	courseName = Inputter.getName(input, "Course "+(ii)+" name? ");
			//}
			int period = Inputter.getNumber(
			input, "Period for "+courseName+" ("+MIN_CLASSES+"-"+MAX_CLASSES+")? ", 
			MIN_CLASSES, MAX_CLASSES);
			boolean added = student.addCourse(courseName, period);
			if(!added) {
				System.out.println(student.getFullName()+"is already taking a course in period "+period);
				ii--; //if user accidentally enters same period number twice
			}
		} 
	}

	/**
	* creates a student based off of information entered by the user
	*
	* @param input Scanner Object used to get input from the user
	* @return a Student whos fields has been initialized based off the users input
	*/
	public static Student createStudent(Scanner input) {
		String name = Inputter.getName(input, "What is the student's name (firstname lastname)? ");
      Scanner lineScan = new Scanner(name);
      String firstName = lineScan.next();
      String lastName = "";
      while(lineScan.hasNext()) {
         lastName += lineScan.next();
      }
		int grade = Inputter.getNumber(input, 
		"What is the student's grade ("+MIN_GRADE+"-"+MAX_GRADE+")? ", MIN_GRADE, MAX_GRADE);
		return new Student(firstName, lastName, grade, MAX_CLASSES);
	}

	/**
	 * Gets the number of courses a student is taking it is a helper method
	 * 
	 * @param  input Scanner Object used to collect input from the user
	 * @return the number of classes the student is taking
	 */
	public static int getCourseNum(Scanner input) {
		return Inputter.getNumber(
		input, "How many courses is this student taking ("+MIN_CLASSES+"-"+MAX_CLASSES+")? ",
		MIN_CLASSES, MAX_CLASSES);
	}

	/**
	 * Generates a list of all the courses contained in the file the user specified
	 * @param  fileScan Scanner Object used to read the file
	 * @return a list containing all of the courses within the given file
	 */
	public static ArrayList<String> generateCourseList(Scanner fileScan) {
		ArrayList<String> courseList = new ArrayList<String>();
		while(fileScan.hasNextLine()) {
			courseList.add(fileScan.nextLine());
		}
		return courseList;
	}

	/**
	 * Validates a course name
	 * 
	 * @param  courseName the course name to be verified
	 * @param  courseList the list containing all of the courses
	 * @return boolean indicating whether the course was valid or not
	 */
	public static boolean validateCourse(String courseName, ArrayList<String> courseList) {
		return courseList.contains(courseName);
	}
}