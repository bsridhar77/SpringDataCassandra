package com.demo.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value="users")
public class User {
	 
	 @PrimaryKey 
	 private int user_id; 
	 
	 public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	private String fname; 
	 private String lname; 
	 
	 public User(int user_id, String fname, String lname) { 
	  this.user_id = user_id; 
	  this.fname = fname; 
	  this.lname = lname; 
	 } 
	 
	
	 @Override 
	 public String toString() { 
	  return "Person [user_id=" + user_id + ", fname=" + fname + ", lname=" + lname + "]"; 
	 } 
	 
	}
	



