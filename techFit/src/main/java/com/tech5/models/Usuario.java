package com.tech5.models;


public class Usuario {

	//Atributos
	private int uid;
	private String email;
	private String password;
	private String nick;
	private String name;
	private String apellido;

	//Constructor
	public Usuario(int uid, String email, String password, String nick, String name, String apellido) {
		super();
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.nick = nick;
		this.name = name;
		this.apellido = apellido;
	}


	public Usuario(int uid, String email, String nick, String name, String apellido) {
		super();
		this.uid = uid;
		this.email = email;
		this.nick = nick;
		this.name = name;
		this.apellido = apellido;
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

	public String getPass() {
		return password;
	}

	public void setPass(String password) {
		this.password = password;
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
