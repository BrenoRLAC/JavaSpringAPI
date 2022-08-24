package com.projetospring.projetoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetospring.projetoapi.model.Cliente;
import com.projetospring.projetoapi.service.ClienteService;
import com.projetospring.projetoapi.utils.ClienteDTO; 

@RestController
@RequestMapping(value = "/home")
public class ClienteController {
	
	//Instanciar a classe ClienteService
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<ClienteDTO> listarClientes() {
		
		return clienteService.listaDeClientes();
		
	}	
	
	@GetMapping("/buscarpelosexo/{sexo}")
	public List<Cliente> buscarPessoaPeloSexo(@PathVariable String sexo) {
		System.out.println(sexo);
		return clienteService.encontraPessoaPeloSexo(sexo);
}
	

	@GetMapping("/buscarpelaidade")
	public List<Cliente> buscarPessoaPelaIdade(@RequestParam(value = "idade", required= true) int idade) {
	
		return clienteService.encontraPessoaPelaIdade(idade);
}
	
	@GetMapping("/buscarPeloSexoIdadefilter")
	public List<Cliente> buscarPeloSexoIdadefilter
			(@RequestParam(value = "sexo", required = false) String sexo, 
			@RequestParam(value="idade", required = false) Integer idade) {
	
		return clienteService.encontraPessoaPelaIdadeSexo(sexo, idade);
}
	
	
	@PostMapping
	public void adicionarClientes(@RequestBody ClienteDTO adicionarCliente) {
		 clienteService.cadastrarCliente(adicionarCliente);
	
	}
	
	
	
	
	
}
