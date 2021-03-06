package com.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class MyDate 
{
	private int day;
	private int month;
	private int year;
	
	public int getDay() 
	{
		return day;
	}
	
	public void setDay(int day) 
	{
		this.day = day;
	}
	
	public int getMonth() 
	{
		return month;
	}
	
	public void setMonth(int month) 
	{
		this.month = month;
	}
	
	public int getYear() 
	{
		return year;
	}
	
	public void setYear(int year) 
	{
		this.year = year;
	}

	@Override
	public String toString() 
	{
		return "MyDate [day=" + day + ", month=" + month + ", year=" + year + "]";
	}
	
}
