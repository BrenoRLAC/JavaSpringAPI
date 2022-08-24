package com.projetospring.projetoapi.utils;

public class ClienteDTO {
	
	private Long id;
	
	private String nomeCliente;

	private String sexo;

	private String cpf;
	
	private Integer idade;
	
	private String sobrenome;
	
	
	public ClienteDTO(Long id, String nomeCliente, String sexo, String cpf, Integer idade) {
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.sexo = sexo;
		this.cpf = cpf;
		this.idade = idade;
	}
	
	public void Cliente(String sexo){
		this.sexo = sexo;
	}

	public ClienteDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public String setCpf(String cpf) {
		return this.cpf = cpf;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
		
}