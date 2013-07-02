package com.brainstorm.swimmanager;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Competencia
{
	@Id
	private String nombre;
	private String descripcion;
	private ArrayList<String> deportistas;
	private ArrayList<Double> tiempos;
	private String eventoKey;
	
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getDescripcion()
	{
		return descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public ArrayList<String> getDeportistas()
	{
		return deportistas;
	}
	public String getEventoKey()
	{
		return eventoKey;
	}
	public void setEventoKey(String evento)
	{
		this.eventoKey = evento;
	}
	public String getKey()
	{
		return nombre;
	}
	public ArrayList<Double> getTiempos()
	{
		return tiempos;
	}
	public void setDeportistas(ArrayList<String> deportistas)
	{
		this.deportistas = deportistas;
	}
	public void setTiempos(ArrayList<Double> tiempos)
	{
		this.tiempos = tiempos;
	}
}
