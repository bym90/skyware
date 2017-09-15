package com.scf.skyware.test.domain;

public class Test
{
	private String id;
	private String pass;
	private String name;
	private String phone;
	
	public Test()
	{
	}
	
	public Test(String id, String pass, String name, String phone)
	{
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.phone = phone;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getPass()
	{
		return pass;
	}
	
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
}
