package com.brainstorm.swimmanager;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Deportista {
	@Id
	private String email;
	
	private String nombres;
	private String apellidos;
	private ArrayList<String> competencia;
	private ArrayList<String> tiempos;
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public ArrayList<String> getCompetencia()
	{
		return competencia;
	}
	public void setCompetencia(ArrayList<String> competencia)
	{
		this.competencia = competencia;
	}
	public ArrayList<String> getTiempos()
	{
		return tiempos;
	}
	public void setTiempos(ArrayList<String> tiempos)
	{
		this.tiempos = tiempos;
	}
	
}
