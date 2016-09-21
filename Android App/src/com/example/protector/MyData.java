package com.example.protector;

public class MyData {

	private String name;
	private String sex;
	private String date;
	private String pronum;

	public MyData(String name, String date, String sex, String pronum){
		this.name = name;
		this.date = date;
		this.sex = sex;
		this.pronum = pronum;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public String getDate() {
		return date;
	}

	public String getPronum() {
		return pronum;
	}

}

