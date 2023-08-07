package br.com.jornadamilhas.api.repository;

import br.com.jornadamilhas.api.model.Depoimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepoimentoRepository extends JpaRepository<Depoimento, Long> {

    Page<Depoimento> findAllByAtivoTrue(Pageable pageable);


    @Query("SELECT d FROM Depoimentos d WHERE d.ativo = true ORDER BY RAND() LIMIT 3")
    List<Depoimento> findTop3Rand();
}
