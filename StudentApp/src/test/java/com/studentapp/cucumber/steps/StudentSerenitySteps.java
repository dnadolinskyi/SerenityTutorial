package com.studentapp.cucumber.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import static com.studentapp.utils.ReusableSpecifications.*;
public class StudentSerenitySteps {

	@Step("Create the nuew student with firstName: {0}, lastName: {1}, email: {2}, programme: {3}, courcses: {4}")
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses) {

		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		return SerenityRest.rest().given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then();
	}
	
	@Step("Getting the student information with firstName: {0}")
	public HashMap<String, Object> getStudentIfoByFirstName(String firstName){
		String p1 = "findAll{it.firstName=='";
		String p2 ="'}.get(0)";
		
		
		return SerenityRest.rest().given()
		.when()
		.get("/list")
		.then()
		.statusCode(200)
		.extract()
		.path(p1+firstName+p2);
	}
	
	@Step("Update the student information by studentId: {0}. Where the firstName: {1}, lastName: {2}, email: {3}, programme: {4}, courcses: {5}")
	public ValidatableResponse updateStudentById(int studentId, String firstName, String lastName, String email, String programme, List<String> courses){
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		
		return SerenityRest.rest().given()
		.spec(ReusableSpecifications.getGenericRequestSpec())	
		.when()
		.body(student)
		.put("/"+studentId)
		.then();
	}
	
	@Step("Deleting student information by studentId: {0}.")
	public void deleteStudent(int studentId){
		SerenityRest.rest()
		.given()
		.when()
		.delete("/" + studentId);
	}
	
	@Step("Getting the student information with studentId: {0}")
	public ValidatableResponse getStudentIfoById(int studentId){
		return SerenityRest.rest()
		.given()
		.when()
		.get("/" + studentId)
		.then();
	}
}
