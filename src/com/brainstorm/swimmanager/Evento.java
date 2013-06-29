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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	private String nombre;
	private Date fecha;
	private String descripcion;
	private ArrayList<Key> competencias;
	
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public Date getFecha()
	{
		return fecha;
	}
	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}
	public String getDescripcion()
	{
		return descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public ArrayList<Key> getCompetencias()
	{
		return competencias;
	}
	public void setCompetencias(ArrayList<Key> competencias)
	{
		this.competencias = competencias;
	}
	public Key getKey()
	{
		return key;
	}
	
	
}
