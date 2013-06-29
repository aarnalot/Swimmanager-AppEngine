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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	private String nombre;
	private String descripcion;
	private ArrayList<String> deportistas;
	private Evento evento;
	
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
	public void setDeportistas(ArrayList<String> deportistas)
	{
		this.deportistas = deportistas;
	}
	public Evento getEvento()
	{
		return evento;
	}
	public void setEvento(Evento evento)
	{
		this.evento = evento;
	}
	public Key getKey()
	{
		return key;
	}
	
	
	
}
