package com.vin.java2503;

public class A004b_Project {
	private String projectCode;
	private String name;
	private String client;
	private String buLeadName;
	public A004b_Project() {
		super();
	}
	public A004b_Project(String projectCode, String name, String client, String buLeadName) {
		super();
		this.projectCode = projectCode;
		this.name = name;
		this.client = client;
		this.buLeadName = buLeadName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getBuLeadName() {
		return buLeadName;
	}
	public void setBuLeadName(String buLeadName) {
		this.buLeadName = buLeadName;
	}
	@Override
	public String toString() {
		return "A004b_Project [projectCode=" + projectCode + ", name=" + name + ", client=" + client + ", buLeadName="
				+ buLeadName + "]";
	}
}
