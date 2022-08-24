package com.projetospring.projetoapi.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "compras", schema="mercado")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long idPedido;
	
	@Column(name = "nro_pedido")
	private String numeroPedido;
	
	@Column(name = "data_compra")
	private Date dataCompra;
	
	@Column(name = "qtd_itens")
	private int qtdItens;
	
	@Column(name = "valor_pago")
	private BigDecimal valorPago;
	
	@Column(name = "fk_id_clientes")
	private Long fkIdCliente;

	@Column(name = "fk_id_produto")
	private Long fkIdProduto;

	public Long getId() {
		return idPedido;
	}

	public void setId(Long idPedido) {
		this.idPedido = idPedido;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String dataMaisCodigo) {
		this.numeroPedido = dataMaisCodigo;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
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

	public Long getFkIdCliente() {
		return fkIdCliente;
	}

	public void setFkIdCliente(Long fkIdCliente) {
		this.fkIdCliente = fkIdCliente;
	}

	public Long getFkIdProduto() {
		return fkIdProduto;
	}

	public void setFkIdProduto(Long fkIdProduto) {
		this.fkIdProduto = fkIdProduto;
	}
	

	
}
