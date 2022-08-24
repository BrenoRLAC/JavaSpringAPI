package com.projetospring.projetoapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.projetoapi.model.Cliente;
import com.projetospring.projetoapi.model.Compra;
import com.projetospring.projetoapi.model.Produto;
import com.projetospring.projetoapi.repository.ClienteRepository;
import com.projetospring.projetoapi.repository.CompraRepository;
import com.projetospring.projetoapi.repository.ProdutoRepository;
import com.projetospring.projetoapi.utils.AdicionarProdutoDTO;
import com.projetospring.projetoapi.utils.ProdutoDTO;
import com.projetospring.projetoapi.utils.ResultadoPesquisaDTO;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	
	
	
	public String comprarProdutos(AdicionarProdutoDTO adicionarProduto) {
		Cliente verificarCPF = clienteRepository.findByCpf(adicionarProduto.getCpf());
		if(verificarCPF.getCpf() == null) {
			return "erro";
		}
		else if(verificarCPF.getId() == null) {
			return "erro";
		}

		Produto verificarProduto = produtoRepository.findProdutosPeloCodigo(adicionarProduto.getcodProduto());
		if (verificarProduto.getCodProduto() == null) {
			return "erro";
		}
		
		if(adicionarProduto.getQtdItens() < 1) {
			return "erro";
		}
		
		
		boolean verificarValorPago = validarPreco(adicionarProduto); 
		if(verificarValorPago == false) {
			return "deu erro";
		}
		
		
		Date data = new Date();
		data.toString();
		String dataMaisCodigo = data + verificarProduto.getCodProduto();
		Compra compra = new Compra();
		compra.setDataCompra(data);
		compra.setFkIdProduto(verificarProduto.getIdProduto());
		compra.setFkIdCliente(verificarCPF.getId());
		compra.setValorPago(adicionarProduto.getValorPago());
		compra.setQtdItens(adicionarProduto.getQtdItens());
		compra.setNumeroPedido(dataMaisCodigo);
		compraRepository.save(compra);
		
		return "ok";
		
	}
	
	private boolean validarPreco(AdicionarProdutoDTO adicionarProduto) {
		int valorPago = adicionarProduto.getValorPago().intValue();
		int quantidade = adicionarProduto.getQtdItens();
		int calculo = quantidade * valorPago;
		
		int calculoDivisao = calculo / quantidade;
		if(calculoDivisao != valorPago) {
			return false;
		}
		return true;
	}

	public  List<ResultadoPesquisaDTO> buscarVendasPorCPF(String produtoPorCPF) {
		
		List<Compra> produtosPorCpf = compraRepository.buscarVendasPorCPF(produtoPorCPF);

		List<ResultadoPesquisaDTO> clientesDTO = new ArrayList<ResultadoPesquisaDTO>();
		
		Cliente cliente = clienteRepository.findByCpf(produtoPorCPF);

		for (Compra compra : produtosPorCpf) {
			ResultadoPesquisaDTO resultadoPesquisaDTO = new ResultadoPesquisaDTO();
			resultadoPesquisaDTO.setCpf(cliente.getCpf());
			resultadoPesquisaDTO.setSexo(cliente.getSexo());
			resultadoPesquisaDTO.setIdade(cliente.getIdade());
			resultadoPesquisaDTO.setNomeCompleto(cliente.getCliente() + " " + cliente.getSobrenome());;;
			
	
			Produto produto = produtoRepository.findById(compra.getFkIdProduto()).get();
			resultadoPesquisaDTO.setNomeProduto(produto.getNomeProduto());
			resultadoPesquisaDTO.setCodProduto(produto.getCodProduto());
			
			resultadoPesquisaDTO.setQtdItens(compra.getQtdItens());
			resultadoPesquisaDTO.setValorPago(compra.getValorPago());
			clientesDTO.add(resultadoPesquisaDTO);
		}
	
		return clientesDTO;
		
	}

	
	
	
	
	public List<ResultadoPesquisaDTO> buscarProdutosPorCod(String codProduto) {
			
		List<ResultadoPesquisaDTO> resultadoDTO = new ArrayList<ResultadoPesquisaDTO>();
		ResultadoPesquisaDTO resultadoPesquisaDTO = new ResultadoPesquisaDTO();
		
		List<Compra> produtosPorCpf = compraRepository.buscarVendasPorCodProduto(codProduto);
		
		for (Compra compras : produtosPorCpf) {
			Produto produto = produtoRepository.findProdutosPeloCodigo(codProduto);
			resultadoPesquisaDTO.setCodProduto(produto.getCodProduto());
			
			Cliente cliente = clienteRepository.findById(compras.getFkIdCliente()).get();
			resultadoPesquisaDTO.setCpf(cliente.getCpf());
			resultadoPesquisaDTO.setSexo(cliente.getSexo());
			resultadoPesquisaDTO.setIdade(cliente.getIdade());
			resultadoPesquisaDTO.setNomeCompleto(cliente.getCliente() + " " + cliente.getSobrenome());;;

			
			
			Produto produtos = produtoRepository.findById(compras.getFkIdProduto()).get();
			resultadoPesquisaDTO.setNomeProduto(produtos.getNomeProduto());
			resultadoPesquisaDTO.setCodProduto(produtos.getCodProduto());
			
			resultadoPesquisaDTO.setQtdItens(compras.getQtdItens());
			resultadoPesquisaDTO.setValorPago(compras.getValorPago());
			resultadoDTO.add(resultadoPesquisaDTO);
			
		}
		
		return resultadoDTO;
	}
	
	
	

	public List<ResultadoPesquisaDTO> buscarProdutoPorQuantidade(int quantidade) {
		
		List<ResultadoPesquisaDTO> resultadoDTO = new ArrayList<ResultadoPesquisaDTO>();
		ResultadoPesquisaDTO resultadoPesquisaDTO = new ResultadoPesquisaDTO();
		
		List<Compra> produtosPorCpf = compraRepository.buscarProdutosPelaQuantidade(quantidade);
		
		for (Compra compras : produtosPorCpf) {
			
			Cliente cliente = clienteRepository.findById(compras.getFkIdCliente()).get();
			resultadoPesquisaDTO.setCpf(cliente.getCpf());
			resultadoPesquisaDTO.setSexo(cliente.getSexo());
			resultadoPesquisaDTO.setIdade(cliente.getIdade());
			resultadoPesquisaDTO.setNomeCompleto(cliente.getCliente() + " " + cliente.getSobrenome());;;
			
			Produto produtos = produtoRepository.findById(compras.getFkIdProduto()).get();
			resultadoPesquisaDTO.setNomeProduto(produtos.getNomeProduto());
			resultadoPesquisaDTO.setCodProduto(produtos.getCodProduto());
			
			resultadoPesquisaDTO.setQtdItens(produtos.getQuantidade());
			resultadoPesquisaDTO.setValorPago(produtos.getPreco());
			resultadoDTO.add(resultadoPesquisaDTO);
			
		}
		
		return resultadoDTO;
	}

	public List<ResultadoPesquisaDTO> buscarProdutoPorData(Date data) {
		List<ResultadoPesquisaDTO> resultadoDTO = new ArrayList<ResultadoPesquisaDTO>();
		ResultadoPesquisaDTO resultadoPesquisaDTO = new ResultadoPesquisaDTO();
		
		List<Compra> produtosPorCpf = compraRepository.findProdutosValidos(data);
		
		for (Compra compras : produtosPorCpf) {
			
			Cliente cliente = clienteRepository.findById(compras.getFkIdCliente()).get();
			resultadoPesquisaDTO.setCpf(cliente.getCpf());
			resultadoPesquisaDTO.setSexo(cliente.getSexo());
			resultadoPesquisaDTO.setIdade(cliente.getIdade());
			resultadoPesquisaDTO.setNomeCompleto(cliente.getCliente() + " " + cliente.getSobrenome());;;
			
			Produto produtos = produtoRepository.findById(compras.getFkIdProduto()).get();
			resultadoPesquisaDTO.setNomeProduto(produtos.getNomeProduto());
			resultadoPesquisaDTO.setCodProduto(produtos.getCodProduto());
			
			resultadoPesquisaDTO.setQtdItens(produtos.getQuantidade());
			resultadoPesquisaDTO.setValorPago(produtos.getPreco());
			resultadoDTO.add(resultadoPesquisaDTO);
			
		}
		
		return resultadoDTO;
	}


}
