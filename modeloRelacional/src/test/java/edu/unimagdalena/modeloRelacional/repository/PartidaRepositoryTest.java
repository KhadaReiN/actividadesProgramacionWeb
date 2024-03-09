package edu.unimagdalena.modeloRelacional.repository;

import edu.unimagdalena.modeloRelacional.AbstractIntegrationDBTest;
import edu.unimagdalena.modeloRelacional.entities.Partida;
import edu.unimagdalena.modeloRelacional.entities.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static edu.unimagdalena.modeloRelacional.repository.PartidaRepository.*;

public class PartidaRepositoryTest extends AbstractIntegrationDBTest {

    PartidaRepository partidaRepository;


    @Autowired
    public PartidaRepositoryTest(PartidaRepository partidaRepository) {

        this.partidaRepository = partidaRepository;
    }

    void initMockUsuarios(){
        Partida p1 = Partida.builder()
                .creador("Pedro Barros")
                .deporte("Futbol")
                .provincia("Caballito")
                .build();
        partidaRepository.save(p1);

        Partida p2 = Partida.builder()
                .creador("Luis Conrado")
                .deporte("Futbol")
                .provincia("Corozal")
                .build();
        partidaRepository.save(p2);

        Partida p3 = Partida.builder()
                .creador("Luis Conrado")
                .deporte("Beisbol")
                .provincia("Corozal")
                .build();
        partidaRepository.save(p3);

        Partida p4 = Partida.builder()
                .creador("Luis Conrado")
                .deporte("Futbol Sala")
                .provincia("Caballito")
                .build();
        partidaRepository.save(p4);
        partidaRepository.flush();
    }



    @BeforeEach
    void setUp() {

        partidaRepository.deleteAll();

    }

    @Test
    @DisplayName("Creación de 4 juegos en dos provincias distintas")
    void partidasCreationTest(){
        //GIVEN
        initMockUsuarios();
        //WHEN
        List<Partida> partidas = partidaRepository.findAll();
        //THEN
        Assertions.assertThat(partidas).hasSize(4);
    }

    @Test
    @DisplayName("Dada una ciudad obtener la lista de eventos o partidos allí")
    void givenCityWhenSearchByCityThenObtainTheGamesInThatPlace(String provincia){
        //GIVEN
        initMockUsuarios();
        //WHEN
        List<Partida> partidas = partidaRepository.findPartidasByProvincia(provincia);

        //THEN
        Assertions.assertThat(partidas).hasSize(2);
    }

    @Test
    @DisplayName("Dado un creador eliminar sus eventos")
    void RemoveTest(String creador){
        //GIVEN
        initMockUsuarios();
        //WHEN
        List<Partida> partidasDe = partidaRepository.findPartidasByCreador(creador);
        partidaRepository.deleteAll(partidasDe);
        List<Partida> partidas = partidaRepository.findAll();
        //THEN
        Assertions.assertThat(partidas).hasSize(2);
    }

}
