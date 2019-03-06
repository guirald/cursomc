package com.guirald.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guirald.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
