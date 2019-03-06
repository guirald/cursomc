package com.guirald.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guirald.cursomc.domain.Categoria;
import com.guirald.cursomc.repositories.CategoriaRepository;
import com.guirald.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id
				+ ", Tipo: " + Categoria.class.getName()));
	}

}
