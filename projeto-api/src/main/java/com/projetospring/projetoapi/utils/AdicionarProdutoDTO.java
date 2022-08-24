package com.projetospring.projetoapi.utils;

import java.math.BigDecimal;

public class AdicionarProdutoDTO {
	
	private String cpf;
	private String codProduto;
	private int qtdItens;
	private BigDecimal valorPago;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getcodProduto() {
		return codProduto;
	}
	public void setNumeroPedido(String codProduto) {
		this.codProduto = codProduto;
	}
	public int getQtdItens() {
		return qtdItens;
	}
	public void setQtdItens(int qtdItens) {
		this.qtdItens = qtdItens;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	
	
}
