
package com.tech5.models;


public class Usuario {

	//Atributos
	private int uid;
	private String email;
	private String password;
	private String username;
	private String nombre;
	private String apellido;

	//Constructor
	public Usuario(int uid, String email, String password, String username, String nombre, String apellido) {
		super();
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.username=username;
		this.nombre = nombre;
		this.apellido = apellido;
	}


	public Usuario(int uid, String email, String username, String nombre, String apellido) {
		super();
		this.uid = uid;
		this.email = email;
		this.username=username;
		this.nombre = nombre;
		this.apellido = apellido;
	}


	public Usuario() {

	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	
}
