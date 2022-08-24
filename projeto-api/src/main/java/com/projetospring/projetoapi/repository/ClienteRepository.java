package com.projetospring.projetoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projetospring.projetoapi.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	//@NamedQuery
	//jpql
	//NativeQury
	@Query(value = "select s.* from mercado.cliente s where s.sexo = :sexo ", nativeQuery = true)
	List<Cliente> findBySexoContains(@Param("sexo") String sexo); 
	
	@Query(value = "select s.* from mercado.cliente s where s.idade > :idade ", nativeQuery = true)
	List<Cliente> encontraPessoaPelaIdade(@Param("idade") int idade);
	
	@Query(value = "select c.* from mercado.cliente c where c.sexo = :sexo and c.idade > :idade", nativeQuery = true)
	List<Cliente> encontraPessoaPelaIdadeSexo(@Param("sexo") String sexo, @Param("idade") Integer idade);
	
	@Query(value = "select c.* from mercado.cliente c where c.cpf = :cpf", nativeQuery = true)
	Cliente findByCpf(@Param("cpf") String cpf);
	
	
	
}