package edu.unimagdalena.modeloRelacional.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.unimagdalena.modeloRelacional.entities.Partida;

public interface PartidaRepository extends JpaRepository<Partida,Long>{
    List<Partida> findPartidasByProvincia(String provincia);
    List<Partida> findPartidasByCreador(String Creador);
  
}
