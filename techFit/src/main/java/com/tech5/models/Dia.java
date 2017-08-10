package com.tech5.models;

import java.util.Date;

public class Dia {

	// Atributos
	private int did;
	private String diaSemana;
	private Date fechaActual;
	private int estado;
	private int habito;

	// Constructor
	public Dia(int did, String diaSemana, Date fechaActual, int estado, int habito) {
		super();
		this.did = did;
		this.diaSemana = diaSemana;
		this.fechaActual = fechaActual;
		this.estado = estado;
		this.habito = habito;
	}

	public Dia(int did) {
		this.did = did;
	}
	public Dia() {
		
	}

	// Getters&Setters
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
		return fechaActual;
	}

	public void setFecha(Date fecha) {
		this.fechaActual = fecha;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public int getHabito() {
		return habito;
	}

	public void setHabito(int habito) {
		this.habito = habito;
	}

	
}
