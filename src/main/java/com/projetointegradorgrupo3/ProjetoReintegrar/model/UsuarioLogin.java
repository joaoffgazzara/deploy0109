package com.projetointegradorgrupo3.ProjetoReintegrar.model;

import java.sql.Date;
import java.time.LocalDate;

public class UsuarioLogin {
	
	private long id;
	
	private String nome ;
	
	private String genero;
	
	private String email ; 
	
	private String senha ;
	
	private String token ;
	
	private String foto;
	
	private String telefone;
	
	private String tipo;
	
	private LocalDate dataNascimento;
	
	private String cargo;
	
	private String linkLinkedin;
	
	private String linkInsta;
	
	private String linkFace;
	
	private String bio;
	
	
	
	

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getLinkLinkedin() {
		return linkLinkedin;
	}

	public void setLinkLinkedin(String linkLinkedin) {
		this.linkLinkedin = linkLinkedin;
	}

	public String getLinkInsta() {
		return linkInsta;
	}

	public void setLinkInsta(String linkInsta) {
		this.linkInsta = linkInsta;
	}

	public String getLinkFace() {
		return linkFace;
	}

	public void setLinkFace(String linkFace) {
		this.linkFace = linkFace;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
