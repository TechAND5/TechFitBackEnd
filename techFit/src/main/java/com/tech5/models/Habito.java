package com.tech5.models;

import java.util.List;



public class Habito {

	//Atributos
	private int hid;
	private String titulo;
	private String descripcion;
	private int progreso;
	private int estado;
	private List<Dia> listaDias;

	//Constructor
	public Habito(){}
	
	
	public Habito(int hid, String titulo, String descripcion, int progreso, int estado, List<Dia> listaDias) {
		super();
		this.hid = hid;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.progreso = progreso;
		this.estado = estado;
		this.listaDias = listaDias;
		
	}

	//Getters&Setters
	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getProgreso() {
		return progreso;
	}

	public void setProgreso(int progreso) {
		this.progreso = progreso;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<Dia> getListaDias() {
		return listaDias;
	}

	public void setListaDias(List<Dia> listaDias) {
		this.listaDias = listaDias;
	}

	
}
