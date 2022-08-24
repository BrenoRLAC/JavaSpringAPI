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

import com.projetospring.projetoapi.service.CompraService;
import com.projetospring.projetoapi.utils.AdicionarProdutoDTO;
import com.projetospring.projetoapi.utils.ResultadoPesquisaDTO;

@RestController
@RequestMapping(value = "/compras")
public class CompraController {
	
	@Autowired
	private CompraService compraService;
	
	
	@GetMapping
	public List<ResultadoPesquisaDTO> buscarVendasPorCPF(@RequestParam(value="vendasporcpf", required=true)String produtoPorCPF) {
		return compraService.buscarVendasPorCPF(produtoPorCPF);
	}
	
	
	@PostMapping
	public void comprarProdutos(@RequestBody AdicionarProdutoDTO adicionarProduto) {
		compraService.comprarProdutos(adicionarProduto);
	}
	
	@GetMapping("/codigo")
	public List<ResultadoPesquisaDTO> buscarPorCodigoProduto(@RequestParam(value="codproduto", required=true) String codProduto){
	return compraService.buscarProdutosPorCod(codProduto);
}
	
	@GetMapping("/data")
	public List<ResultadoPesquisaDTO> buscarPorData(@RequestParam(value="data", required=true) Date data){
	return compraService.buscarProdutoPorData(data);
}
	
	@GetMapping("/quantidade")
	public List<ResultadoPesquisaDTO> buscarPorQuantidade(@RequestParam(value="quantidademaior", required=true) int quantidade){
	return compraService.buscarProdutoPorQuantidade(quantidade);
}

	
	
}
