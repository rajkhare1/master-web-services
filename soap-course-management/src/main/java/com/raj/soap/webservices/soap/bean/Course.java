package com.raj.soap.webservices.soap.bean;

public class Course {
	
	private int id;
	private String name;
	private String descrtiption;
	
	public Course(int id, String name, String descrtiption) {
		super();
		this.id = id;
		this.name = name;
		this.descrtiption = descrtiption;
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

	public String getDescrtiption() {
		return descrtiption;
	}

	public void setDescrtiption(String descrtiption) {
		this.descrtiption = descrtiption;
	}

	@Override
	public String toString() {
		return String.format("Course [id=%s, name=%s, descrtiption=%s]", id, name, descrtiption);
	}
	
	

}
