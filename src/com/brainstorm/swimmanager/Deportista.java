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
	private ArrayList<Long> competencia;
	
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
	public ArrayList<Long> getCompetencia()
	{
		return competencia;
	}
	public void setCompetencia(ArrayList<Long> competencia)
	{
		this.competencia = competencia;
	}
	
}
