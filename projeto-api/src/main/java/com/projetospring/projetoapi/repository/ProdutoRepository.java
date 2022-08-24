package com.projetospring.projetoapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetospring.projetoapi.model.Compra;
import com.projetospring.projetoapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query(value= "select p.* from mercado.produto p where cod_produto = :produto", nativeQuery = true)
	Produto findProdutosPeloCodigo(@Param("produto")String codProduto);
	
	@Query(value= "select p.* from mercado.produto p where nome_produto like %:produto%", nativeQuery = true)
	List<Produto> findByParteNome(@Param("produto")String nomeProduto);
	
	@Query(value= "select p.* from mercado.produto p where data_validade = :dataValidade", nativeQuery = true)
	List<Produto> findProdutosValidos(@Param("dataValidade")Date dataValidade);
	
	@Query(value= "select p.* from mercado.produto p where nome_produto = :produto", nativeQuery = true)
	Produto findProdutosPeloNome(@Param("produto")String nomeProduto);
	
	@Query(value= "select p.* from mercado.produto p where quantidade = :produto", nativeQuery = true)
	Produto findProdutosPelaQuantidade(@Param("produto")int quantidadeProduto);
	
	@Query(value= "select p.* from mercado.produto p where quantidade = :produto", nativeQuery = true)
	Produto findProdutosPelaData(@Param("produto")Date date);

	@Query(value= "select p.* from mercado.produto p where cod_produto = :produto", nativeQuery = true)
	List<Produto> findProdutosPeloCodigoCompra(@Param("produto")String codProduto);
	
	

	
	
}
