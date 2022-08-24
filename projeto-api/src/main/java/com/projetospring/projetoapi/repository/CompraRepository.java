package com.projetospring.projetoapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projetospring.projetoapi.model.Compra;
import com.projetospring.projetoapi.model.Produto;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	@Query(value = "select c1.* from mercado.cliente c join mercado.compras c1 on c.id_cliente = c1.fk_id_clientes where c.cpf = :cpf", nativeQuery=true)
	List<Compra> buscarVendasPorCPF(@Param("cpf")String produtoPorCPF);
	
	@Query(value = "select c1.* from mercado.produto p join mercado.compras c1 on p.id_produto = c1.fk_id_produto where p.cod_produto = :codProduto", nativeQuery=true)
	List<Compra> buscarVendasPorCodProduto(@Param("codProduto")String codProduto);

	@Query(value = "select c1.* from mercado.produto p join mercado.compras c1 on p.id_produto = c1.fk_id_produto where c1.qtd_itens > :quantidade", nativeQuery=true)
	List<Compra> buscarProdutosPelaQuantidade(@Param("quantidade")int quantidade);

	@Query(value= "select c1.* from mercado.produto p join mercado.compras c1 on p.id_produto = c1.fk_id_produto where p.data_validade > :dataValidade", nativeQuery = true)
	List<Compra> findProdutosValidos(@Param("dataValidade")Date dataValidade);

}
