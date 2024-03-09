package edu.unimagdalena.modeloRelacional.repository;

import edu.unimagdalena.modeloRelacional.entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

}
