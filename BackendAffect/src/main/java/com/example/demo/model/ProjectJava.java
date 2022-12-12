package com.example.demo.model;

public class ProjectJava {
	public static int counter=0;
	private int id;
private String name;
public static int getCounter() {
	return counter;
}
public static void setCounter(int counter) {
	ProjectJava.counter = counter;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public ProjectJava(String name) {
	super();
	this.id=counter;
	counter++;
	this.name = name;
}
public ProjectJava() {
	
}

}
