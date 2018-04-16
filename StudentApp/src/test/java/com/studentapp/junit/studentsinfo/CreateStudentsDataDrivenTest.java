package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.cucumber.steps.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Concurrent(threads="4x") //parallel execution for DD Tests
@UseTestDataFrom(value="feature/testdata/serenityinfo.csv")
@RunWith(SerenityParameterizedRunner.class)

public class CreateStudentsDataDrivenTest extends TestBase{
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	private String firstName;
	private String lastName;
	private String email;
	private String programme;
	private String course;
	
	@Steps
	StudentSerenitySteps steps;
	@Title("DataDriven Test for adding multiple students to the Students app")
	@Test
	public void createMultipleStrudents() {
		ArrayList<String> courses = new ArrayList<>();
		courses.add(course);
		steps.createStudent(firstName, lastName, email, programme, courses);
	}
}
