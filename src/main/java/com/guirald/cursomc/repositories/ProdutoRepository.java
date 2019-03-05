package com.guirald.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guirald.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
