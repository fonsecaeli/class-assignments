//Eli F.
//Section C
//Assignment 9
//Description: class that models a student
//Version 1.0
//Class Name: Student
//1/6/16

import java.util.*;

public class Student {

	private String firstName;
	private String lastName;
	private int grade;
	private int maxClasses;
	private String[] courses;

	/**
	* constructor for a Student Object, all fields initialized based of of parameters
	* 
	* @param firstName the first name of the student
	* @param the lastName off the student
	* @param the gradelevel of the student
	* @param the max number of classes the student can take
	*/
	public Student(String firstName, String lastName, int grade, int maxClasses) {
		if(firstName == null || lastName == null || grade <= 0 || maxClasses <= 0) {
			throw new IllegalArgumentException();
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.maxClasses = maxClasses;
		this.courses = new String[maxClasses];
		for(int ii = 0; ii < this.courses.length; ii++) {
			this.courses[ii] = "Free";
		}
	}

	/**
	* getter method for the firstName field
	*
	* @return the firstName of student
	*/
	public String getFirstName() {
		return firstName;
	}

	/**
	* getter method for the lastName field
	*
	* @return the lastName of student
	*/
	public String getLastName() {
		return lastName;
	}

	/**
	* gets the full name of the student
	*
	* @return the full name of the student
	*/
	public String getFullName() {
		return firstName+" "+lastName;
	}

	/**
	* getter method for the grade field
	*
	* @return the grade of the student
	*/
	public int getGrade() {
		return grade;
	}

	/**
	* setter method for the firstName field
	*
	* @param grade the grade of the student
	*/
	public void setGrade(int grade) {
		if(grade <= 0) {
			throw new IllegalArgumentException();
		}
		this.grade = grade;
	}

	/**
	* getter method for the courses field
	*
	* @return the array containing the student courses
	*/
	public String[] getCourses() {
		return courses;
	}

	/**
	* calculates the number of courses not including frees a student is taking
	*
	* @return the number of courses the student is taking
	*/
	public int getCourseCount() {
		int counter = 0;
		for(int ii = 0; ii < courses.length; ii++) {
			if(!this.courses[ii].equals("Free")) {
				counter++;
			}
		}
		return counter;
	}

	/**
	* adds a specific course at a specific period
	* will not add the course if a course is already scheduled for the period
	* or if that period number is less than or equal to zero or greater than the number of periods
	* in that students day schedule
	*
	* @param course the name of the course
	* @param period the period of the course
	* @return boolean indicating whether the course was added or not
	*/
	public boolean addCourse(String course, int period) {
		int index = period-1; 
		if(index >= 0 && index < this.courses.length && this.courses[index].equals("Free") 
			&& course != null) {
			this.courses[index] = course;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	* prints out relevant information about a student
	* name, grade, and courses
	*
	* @return String containing information about the student
	*/
	@Override
	public String toString() {
		return grade+": "+firstName+" "+lastName+" "+java.util.Arrays.deepToString(courses);
	}

	/**
	* tests to see if two students are the same
	* must have the same name, grade level, maxClasses, and classes
	*
	* @param o object to be compared
	* @return boolean indicating whether the two students are equal or not
	*/
	public boolean equals(Object o) {
		if(o instanceof Student) {
			Student test = (Student) o;
         return this.firstName.equals(test.getFirstName()) 
         && this.lastName.equals(test.getLastName())
         && this.grade == test.getGrade()
         && this.maxClasses == test.getCourses().length
         && Arrays.deepToString(this.courses).equals(Arrays.deepToString(test.getCourses()));
		}
		return false;
	}
}