
package com.salvou.crm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // O método precisa estar aqui dentro!
    boolean existsByCpf(String cpf);

} // Esta chave fecha a interface
