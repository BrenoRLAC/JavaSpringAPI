package com.projetospring.projetoapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.projetoapi.model.Produto;
import com.projetospring.projetoapi.repository.ProdutoRepository;
import com.projetospring.projetoapi.utils.ProdutoDTO;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto buscarProdutosPeloCodigo(String codProduto) {
		return produtoRepository.findProdutosPeloCodigo(codProduto);
	}

	public List<Produto> findByParteNome(String nomeProduto) {
		return produtoRepository.findByParteNome(nomeProduto);
	}
	//
	public boolean verificarCodigo(ProdutoDTO cadastrarProduto) {
		boolean verificarCodProdutoNumeros = cadastrarProduto.getCodProduto().substring(0, 4).matches("[0-9]*");
		boolean verificarCodProdutoLetras = cadastrarProduto.getCodProduto().toUpperCase().substring(4, 8).matches("[A-Z]*");
		
		if (verificarCodProdutoNumeros == false || verificarCodProdutoLetras == false) {
			System.out.println("Deu errado aqui, letras ou numeros do codigo fora do padrao");
			return false;
		}
		return true;
	}
	public String adicionarProduto(ProdutoDTO cadastrarProduto) {
		//inicio verificações relacionadas ao codigo do produto
//		boolean verificarCodProdutoNumeros = cadastrarProduto.getCodProduto().substring(0, 4).matches("[0-9]*");
//		boolean verificarCodProdutoLetras = cadastrarProduto.getCodProduto().toUpperCase().substring(4, 8)
//				.matches("[A-Z]*");
//
//		if (verificarCodProdutoNumeros == false || verificarCodProdutoLetras == false) {
//			System.out.println("Deu errado aqui, letras ou numeros do codigo fora do padrao");
//			return "Deu errado aqui";
//		}
		
		if(verificarCodigo(cadastrarProduto) == false) {
			System.out.println("Deu erro aqui, letras ou numeros do codigo fora do padrão");
			return "Deu erro";
		}
		
		String codProdutoUppercase = cadastrarProduto.getCodProduto().toUpperCase();
		Produto verificarCodProduto = produtoRepository.findProdutosPeloCodigo(codProdutoUppercase);
		if (verificarCodProduto != null) {
			System.out.println("Codigo do produto não pode se repetir");
			return "Deu erro";
		}
		
		int verificarTamanhoCodProduto = cadastrarProduto.getCodProduto().length();
		if (verificarTamanhoCodProduto != 8) {
			System.out.println("Deve ter 8 caracteres!");
			return "Deve ter 8 caracteres!";
		}
	
	
		//fim verificações relacionadas ao codigo do produto
		
		//inicio verificações relacionadas a quantidade do produto
		if(quantidadeMenorQueUm(cadastrarProduto) == false) {
			System.out.println("Quantidade deu erro menor que um");
			return "Quantidade deu erro menor que um";
		}
		//fim verificações relacionadas a quantidade do produto
		
		//inicio verificações relacionadas ao nome do produto
		
//		String verificarNomedoProduto = cadastrarProduto.getNomeProduto().toString();
		String verificarTamanhoProduto = cadastrarProduto.getNomeProduto().toString();
		
//		if(verificarNomedoProduto.matches("[a-z]*")  == false) {
//			System.out.println(cadastrarProduto.getNomeProduto().toLowerCase());
//			System.out.println("Nome do produto esta errado, apenas letras aceitos");
//			return "Nome do produto esta errado, apenas letras aceitos";
//		}
			if(verificarTamanhoProduto.length() < 3) {
			System.out.println("erro no tamanho do nome do produto");
			return "erro no tamanho do nome do produto";
		}
		//fim verificações relacionadas ao nome do produto
		
		Produto produto = new Produto();
		//inicio verificações relacionadas a data do produto
			try {
				String cadastrarProdutoString = cadastrarProduto.getDataValidade();
		
				System.out.println(cadastrarProduto.getDataValidade());

				LocalDate dataValidadParse = LocalDate.parse(cadastrarProdutoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				//Posso setar o produto dentro do try, porque se ele tentar setar e der algum erro, o catch vai reclamar. lembrar do if else
				produto.setDataValidade(converterLocalDateParaDate(dataValidadParse));
				System.out.println(dataValidadParse);
				System.out.println("chegou ate aqui e deu tudo certo");
			} catch (Exception e) {
				System.out.println("Data é inválida");
				System.out.println(e);
				return "Deu erro";
			}
			//fim verificações relacionadas a data do produto
			boolean verificarPreco = validarPreco(cadastrarProduto);
			if(verificarPreco == false) {
				System.out.println("Preço do produto é inválido");
				return "Errado.";
			}
		produto.setCodProduto(cadastrarProduto.getCodProduto().toUpperCase());
		produto.setNomeProduto(cadastrarProduto.getNomeProduto());
		produto.setQuantidade(cadastrarProduto.getQuantidade());
		produto.setPreco(cadastrarProduto.getPreco());
		produtoRepository.save(produto);	

		return "Sucesso ao cadastrar!";
	}

	private boolean quantidadeMenorQueUm(ProdutoDTO cadastrarProduto) {
		if (cadastrarProduto.getQuantidade() < 1) {
			return false;
		}
		return true;
	}

	private Date converterLocalDateParaDate(LocalDate dataValidadParse) {
		return java.util.Date.from(dataValidadParse.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
	}
	
	//do valor do produto
	private boolean validarPreco(ProdutoDTO cadastrarProduto) {
		BigDecimal precoProduto = cadastrarProduto.getPreco();
		BigDecimal big = new BigDecimal(0.0);
		System.out.println(precoProduto);
		System.out.println(big);
		int compararPrecoProduto = precoProduto.compareTo(big);
		if(compararPrecoProduto < 0 ) {
			return false;
		}
		return true;
	}
	
	

	public List<Produto> findProdutosValidos(String dataValidade) {	
		//converter de localdate para data 
		LocalDate dataValidadParse = LocalDate.parse(dataValidade, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		LocalDate dataValidadParse = LocalDate.parse(cadastrarProdutoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Date dataConvertida = converterLocalDateParaDate(dataValidadParse);
		List<Produto> verificarValidadeProdutos = produtoRepository.findProdutosValidos(dataConvertida);
		
		return verificarValidadeProdutos;	
	}

}
