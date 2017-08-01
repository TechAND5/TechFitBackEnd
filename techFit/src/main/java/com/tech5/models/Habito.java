
package com.tech5.models;


import java.util.Date;




public class Habito {

	//Atributos
	private int hid;
	private String titulo;
	private String descripcion;
	private Date fechaI;
	private int progreso;
	private int estado;
	

	//Constructor
	public Habito(){}
	
	
	public Habito(int hid, String titulo, String descripcion,Date fechaI, int progreso, int estado) {
		super();
		this.hid = hid;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaI=fechaI;
		this.progreso = progreso;
		this.estado = estado;
				
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


	public Date getFechaI() {
		return fechaI;
	}


	public void setFechaI(Date fechaI) {
		this.fechaI = fechaI;
	}
	
}
