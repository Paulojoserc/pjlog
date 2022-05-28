package com.algawork.pjlog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algawork.pjlog.domain.exeception.NegocioException;
import com.algawork.pjlog.domain.model.Cliente;
import com.algawork.pjlog.domain.repository.ClienteResository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatalagoClienteService {

	private ClienteResository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
	}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
