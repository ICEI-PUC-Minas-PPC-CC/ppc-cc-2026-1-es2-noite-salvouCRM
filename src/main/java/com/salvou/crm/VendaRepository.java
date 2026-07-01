package com.salvou.crm;

import com.salvou.crm.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Avisa ao Spring que esta peça gerencia o banco de dados

public interface VendaRepository extends JpaRepository<Venda, Long> {}