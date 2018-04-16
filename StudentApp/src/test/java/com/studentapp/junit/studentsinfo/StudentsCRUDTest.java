package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.steps.StudentSerenitySteps;
import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentsCRUDTest extends TestBase {
	
	static String firstName = "John"+TestUtils.getRandomValue();
	static String lastName = "Travolta"+TestUtils.getRandomValue();
	static String programme = "Computer Science";
	static String email = TestUtils.getRandomValue()+"j.travolta@gmail.com";
	static public int studentId;
	
	@Steps
	StudentSerenitySteps steps;
	
	@Title("This test will create a new student")
	@Test
	public void test001_createStudent() {
				
		ArrayList<String> courses = new ArrayList<String>(); 
		courses.add("Java");
		courses.add("C#");
		
		steps.createStudent(firstName, lastName, email, programme, courses)		
		.statusCode(201)
		.spec(ReusableSpecifications.getGenericResponseSpec());
		
	}
	
	@Title("Verify the student was added to the application")
	@Test
	public void test002_getStudent() {
	
		HashMap<String, Object> value = steps.getStudentIfoByFirstName(firstName);

		studentId = (int) value.get("id");
		assertThat(value, hasValue(firstName));
	}
	
	@Title("Update the user information and verify if updated information is present")
	@Test
	public void test003_updateStudent() {
		firstName = firstName +"_UPDATED";
		
		ArrayList<String> courses = new ArrayList<String>(); 
		courses.add("Java");
		courses.add("C#");
		
		steps.updateStudentById(studentId, firstName, lastName, email, programme, courses)
		.statusCode(200);
		
		HashMap<String, Object> value =steps.getStudentIfoByFirstName(firstName);
		assertThat(value, hasValue(firstName));
	}
	
	@Title("Delete the student and verify is student is deleted")
	@Test
	public void test004_deleteStudent() {
		
		steps.deleteStudent(studentId);
		steps.getStudentIfoById(studentId).statusCode(404);
		
	}
	
	
	
}
