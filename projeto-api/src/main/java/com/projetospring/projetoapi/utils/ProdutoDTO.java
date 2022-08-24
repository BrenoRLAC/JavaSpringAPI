package com.projetospring.projetoapi.utils;

import java.math.BigDecimal;

public class ProdutoDTO {
	private Long idProduto;
	private String codProduto;
	private String nomeProduto;
	private Integer quantidade;
	private String dataValidade;
	private BigDecimal preco;
	

	public ProdutoDTO() {
		
	}

	public ProdutoDTO(Long idProduto, String codProduto, String nomeProduto, Integer quantidade, String dataValidade, BigDecimal preco) {
		super();
		this.idProduto = idProduto;
		this.codProduto = codProduto;
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.dataValidade = dataValidade;
		this.preco = preco;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	
}
