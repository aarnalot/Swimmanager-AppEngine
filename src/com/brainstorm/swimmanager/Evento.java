package com.brainstorm.swimmanager;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Evento
{
	@Id	
	private String nombre;
	private int day;
	private int month;
	private int year;
	private String descripcion;
	private ArrayList<String> competencias;
	
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
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
	public String getDescripcion()
	{
		return descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public ArrayList<String> getCompetencias()
	{
		return competencias;
	}
	public void setCompetencias(ArrayList<String> competencias)
	{
		this.competencias = competencias;
	}
	public String getKey()
	{
		return nombre;
	}
	
	
}
