package com.projetospring.projetoapi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.projetoapi.model.Produto;
import com.projetospring.projetoapi.service.ProdutoService;
import com.projetospring.projetoapi.utils.ProdutoDTO;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	public ProdutoService produtoService;
	//retornar apenas o produto que é unico
	@GetMapping("/codigoproduto")
	public Produto buscarProdutosPeloCodigo(@RequestParam(value="codigo", required= true) String codProduto) {
		return produtoService.buscarProdutosPeloCodigo(codProduto);
	}
	
	@GetMapping("/filtrarnomeproduto")
	public List<Produto> findByParteNome(@RequestParam(value="nomeproduto", required=true) String nomeProduto){
		return produtoService.findByParteNome(nomeProduto);
		
	}
	
	@GetMapping("/filtrarprodutoValidade")
	public List<Produto> findProdutosValidos(@RequestParam(value="produtovalido", required=true) String dataValidade){
		return produtoService.findProdutosValidos(dataValidade);
	}
	
	@PostMapping//CriarDTO
	public void adicionarProduto(@RequestBody ProdutoDTO cadastrarProduto){
		produtoService.adicionarProduto(cadastrarProduto);
		
	}
}
