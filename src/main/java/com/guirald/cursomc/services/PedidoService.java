package com.guirald.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guirald.cursomc.domain.Pedido;
import com.guirald.cursomc.repositories.PedidoRepository;
import com.guirald.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id
				+ ", Tipo: " + Pedido.class.getName()));
	}

}
