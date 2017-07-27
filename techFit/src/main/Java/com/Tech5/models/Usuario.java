package com.tech5.models;


public class Usuario {

	//Atributos
	private int uid;
	private String email;
	private String pass;
	private String nick;
	private String name;
	private String apellido;

	//Constructor
	public Usuario(int uid, String email, String pass, String nick, String name, String apellido) {
		super();
		this.uid = uid;
		this.email = email;
		this.pass = pass;
		this.nick = nick;
		this.name = name;
		this.apellido = apellido;
	}

	//Metodos
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
