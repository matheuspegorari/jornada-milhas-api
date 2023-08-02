package br.com.jornadamilhas.api.model.destinos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinosRepository extends JpaRepository<Destino,Long> {

    @Query("SELECT d from Destinos d WHERE d.nome LIKE :nome%")
    List<Destino> findDestinoByNome(@Param("nome") String nome);

}
