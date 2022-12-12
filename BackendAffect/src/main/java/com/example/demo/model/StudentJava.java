package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;



public class StudentJava {
	public static int counter=0;
private int id ;
private String nom ;
private double  moyen;
private List<ProjectJava> listP;
public static int getCounter() {
	return counter;
}
public static void setCounter(int counter) {
	StudentJava.counter = counter;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public double getMoyen() {
	return moyen;
}
public void setMoyen(double moyen) {
	this.moyen = moyen;
}
public List<ProjectJava> getListP() {
	return listP;
}
public void setListP(List<ProjectJava> listP) {
	this.listP = listP;
}
public StudentJava() {
	super();
	this.id=counter;
	counter++;
	listP= new ArrayList<ProjectJava>();
}
public StudentJava( String nom, double moyen) {
	super();
	this.id=counter;
	counter++;
	this.nom = nom;
	this.moyen = moyen;
	listP= new ArrayList<ProjectJava>();
}

}
