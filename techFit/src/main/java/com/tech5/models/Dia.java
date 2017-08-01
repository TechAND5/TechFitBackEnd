package com.tech5.models;

import java.util.Calendar;
import java.util.Date;

public class Dia {

	//Atributos
	private int did;
	//private Calendar diaSemana;
	private Calendar fechaActual;
	private int estado;
	private int hid;
	
	//Constructor
	public Dia(int did,  Calendar fecha, int estado, int hid) {
		super();
		this.did = did;
		//this.diaSemana = diaSemana;
		this.fechaActual = fecha;
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
	//public Calendar getDiaSemana() {
	///	return diaSemana;
	//}
	//public void setDiaSemana(Calendar diaSemana) {
	//	this.diaSemana = diaSemana;
	//}
	public Calendar getFecha() {
		return fechaActual;
	}
	public void setFecha(Calendar fecha) {
		this.fechaActual = fecha;
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
