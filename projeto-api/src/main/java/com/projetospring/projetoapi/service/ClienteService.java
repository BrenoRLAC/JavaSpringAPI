package com.projetospring.projetoapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetospring.projetoapi.model.Cliente;
import com.projetospring.projetoapi.repository.ClienteRepository;
import com.projetospring.projetoapi.utils.ClienteDTO;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<ClienteDTO> listaDeClientes() {
		List<Cliente> clientes = clienteRepository.findAll();

		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();

		for (Cliente cliente : clientes) {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setSobrenome(cliente.getSobrenome());
			clienteDTO.setId(cliente.getId());
			clienteDTO.setIdade(cliente.getIdade());
			clienteDTO.setSexo(cliente.getSexo());
			clienteDTO.setCpf(cliente.getCpf());
			clienteDTO.setNomeCliente(cliente.getCliente() + " " + cliente.getSobrenome());

			clientesDTO.add(clienteDTO);
		}
		return clientesDTO;
	}

	public String cadastrarCliente(ClienteDTO clienteCadastrar) {

		System.out.println(clienteCadastrar.getCpf());
		if (clienteCadastrar.getCpf().length() != 11) {
			System.out.println("CPF invalido: " + clienteCadastrar.getCpf());
			return "deu errado";
		}

		if (clienteCadastrar.getIdade() < 18) {
			System.out.println("Idade invalida: " + clienteCadastrar.getIdade());
			return "deu erro";
		}

		Cliente verificarCPF = clienteRepository.findByCpf(clienteCadastrar.getCpf());

		if (verificarCPF != null) {
			return "deu errado";
		}

		Cliente cliente = new Cliente();
		cliente.setNomeCliente(clienteCadastrar.getNomeCliente());
		cliente.setSobrenome(clienteCadastrar.getSobrenome());
		cliente.setCpf(clienteCadastrar.getCpf());
		cliente.setIdade(clienteCadastrar.getIdade());
		cliente.setSexo(clienteCadastrar.getSexo());

		System.out.println("Chegou aqui");
		clienteRepository.save(cliente);
		return "sucesso!";

	}

	public List<Cliente> encontraPessoaPeloSexo(String sexo) {
		return this.clienteRepository.findBySexoContains(sexo);

	}

	public List<Cliente> encontraPessoaPelaIdade(int idade) {
		return clienteRepository.encontraPessoaPelaIdade(idade);
	}

	public List<Cliente> encontraPessoaPelaIdadeSexo(String sexo, Integer idade) {
		if(idade != null && sexo != null) {
			return clienteRepository.encontraPessoaPelaIdadeSexo(sexo, idade);
		}
		
		if (idade != null) {
			return clienteRepository.encontraPessoaPelaIdade(idade);
		}
		else if (sexo != null) {
			return clienteRepository.findBySexoContains(sexo);
		} 
		return null;

	}

}