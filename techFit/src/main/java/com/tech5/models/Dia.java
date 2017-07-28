package com.tech5.models;

import java.util.Date;

public class Dia {

	//Atributos
	private int did;
	private String diaSemana;
	private Date fecha;
	private int estado;
	private int hid;
	
	//Constructor
	public Dia(int did, String diaSemana, Date fecha, int estado, int hid) {
		super();
		this.did = did;
		this.diaSemana = diaSemana;
		this.fecha = fecha;
		this.estado = estado;
		this.hid = hid;
	}
	
	//Getters&Setters
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}

}
