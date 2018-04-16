package com.studentapp.junit.studentsinfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;

	@RunWith(SerenityRunner.class)
	
public class FirstSerenityTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://localhost:8080";
		RestAssured.basePath="/student/list";
	}
	
	@AfterClass
	public static void TearDown() {
		RestAssured.reset();
	}
	
	@Test
	public void getAllStudent() {
	SerenityRest.given()
	.when()
	.get()
	.then()
	.log()
	.all()
	.statusCode(200);
	}
	
	@Test
	public void thisIsFailingTest() {
	SerenityRest.given()
	.when()
	.get()
	.then()
	.log()
	.all()
	.statusCode(500);
	}
	
	@Pending
	@Test
	public void thisIsPendingTest() {
		
	}
	
	@Ignore
	@Test
	public void thisIsSkippedTest() {
		
	}
	
	@Test
	public void thisIsATestWithError() {
		System.out.println("This is an error"+(5/0));
	}
	
	@Test
	public void fileDoesNotExist() throws FileNotFoundException{
		File file = new File("E://file.txt");
		FileReader fr = new FileReader(file); 
	}
	
	@Manual
	@Test
	public void thisIsManualTest(){
	}
	
	@Title("This test will get the information of all students from the Students Apps")
	@Test
	public void test001() {
	SerenityRest.given()
	.when()
	.get()
	.then()
	.log()
	.all()
	.statusCode(200);
	}
}
