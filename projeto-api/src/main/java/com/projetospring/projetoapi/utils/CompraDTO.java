package com.projetospring.projetoapi.utils;

import java.util.Date;

public class CompraDTO {
	
	private Long id;
	private String numeroPedido;
	private Date dataCompra;
	private Long idCliente;
	private Long idProduto;
	private int qtdItens;


	public Long getId() {
		return id;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Long getIdCliente() {
		return idCliente;
	}

//	public void setIdCliente(Long idCliente) {
//		this.idCliente = idCliente;
//	}

	public Long getIdProduto() {
		return idProduto;
	}

//	public void setIdProduto(Long idProduto) {
//		this.idProduto = idProduto;
//	}
	
	public int getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(int qtdItens) {
		this.qtdItens = qtdItens;
	}
	
	
}
