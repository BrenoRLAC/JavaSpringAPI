package com.projetospring.projetoapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Data do lombok
@Entity
@Table(name = "cliente", schema="mercado")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long id;

	//falar que x coluna é y coluna no Banco de Dados
	@Column(name = "nome_cliente")
	private String nomeCliente;
	
	@Column(name = "sobrenome")
	private String sobrenome;
	
//	@CPF
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "idade")
	private Integer idade;
	
	@Column(name = "sexo")
	private String sexo;
	
	
	public Long getId() {
		return id;
	}

	public String getCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String cliente) {
		this.nomeCliente = cliente;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


}
