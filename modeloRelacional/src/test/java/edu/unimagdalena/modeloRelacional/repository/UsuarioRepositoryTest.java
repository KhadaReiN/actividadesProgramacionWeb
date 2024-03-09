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
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class UsuarioRepositoryTest extends AbstractIntegrationDBTest {
    UsuarioRepository usuarioRepository;


    @Autowired
    public UsuarioRepositoryTest(UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    void initMockUsuarios(){
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellido("Pizarro")
                .username("julianpizarro")
                .password("123")
                .build();
        usuarioRepository.save(usuario);

        Usuario usuario2 = Usuario.builder().nombre("Jose")
                .apellido("Pertuz")
                .username("josepertuz")
                .password("123")
                .build();
        usuarioRepository.save(usuario2);
        usuarioRepository.flush();
    }



    @BeforeEach
    void setUp() {

        usuarioRepository.deleteAll();

    }

    @Test
    void givenAnUser_whenSave_thenUserwithId(){
        //given
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellido("Pizarro")
                .username("julianpizarro")
                .email("jjpizarro@gmail.com")
                .password("123")
                .build();
        //when
        Usuario userSaved = usuarioRepository.save(usuario);
        //then
        assertThat(userSaved.getId()).isNotNull();

    }
    @Test
    @DisplayName("dado un conjunto de usuarios al buscarlo todos obtenemos la lista de los usuarios en la base de datos")
    void shouldGetAllUsers(){
        //GIVEN
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellido("Pizarro")
                .username("julianpizarro")
                .email("dssaddsd")
                .password("123")
                .build();
        usuarioRepository.save(usuario);

        Usuario usuario2 = Usuario.builder().nombre("Jose")
                .apellido("Pertuz")
                .username("josepertuz")
                .password("123")
                .email("sdggsdsfggsd")
                .build();
        usuarioRepository.save(usuario2);
        usuarioRepository.flush();
        //WHEN
        List<Usuario> usuarios = usuarioRepository.findAll();

        //THEN
        Assertions.assertThat(usuarios).hasSize(2);
    }
    @Test
    void givenUsuarios_whenBuscarPorNombreyApellido_thenObtienesUnaListaDeUsuarios(){
        Usuario usuario = Usuario.builder()
                .nombre("Julian")
                .apellido("Pizarro")
                .username("julianpizarro")
                .password("123")
                .email("fasfawaqwwrfw")
                .build();
        usuarioRepository.save(usuario);
        Usuario usuario2 = Usuario.builder().nombre("Jose")
                .apellido("Pertuz")
                .username("josepertuz")
                .email("adfafsfaafasfasf")
                .password("123")
                .build();
        usuarioRepository.save(usuario);

        List<Usuario> usuarios = usuarioRepository.findByNombreAndApellido("Julian", "Pizarro");

        Assertions.assertThat(usuarios).isNotEmpty();
        Assertions.assertThat(usuarios).first().hasFieldOrPropertyWithValue("nombre","Julian");
    }

    //SECCION ACTIVIDAD

    @Test
    @DisplayName("Creación de 2 USUARIOS")
    void usuarioCreationTest(){
        //GIVEN
        initMockUsuarios();
        Usuario u1 = Usuario.builder()
                .nombre("Jose")
                .apellido("Perez")
                .username("jperez")
                .password("123")
                .email("jperez@gmail.com")
                .build();
        usuarioRepository.save(u1);
        Usuario u2 = Usuario.builder().nombre("Julian")
                .apellido("Perez")
                .username("jptkd")
                .email("julitkd@gmail.com")
                .password("123")
                .build();
        usuarioRepository.save(u2);

        //WHEN
        List<Usuario> usuarios = usuarioRepository.findAll();
        //THEN
        Assertions.assertThat(usuarios).hasSize(4);
    }

    @Test
    @DisplayName("Dado un nombre encontrar todos los usuarios que lo posean")
    void getbyName(String nombre){
        //GIVEN
        initMockUsuarios();
        //WHEN
        List<Usuario> usuarios = usuarioRepository.findByNombre(nombre);

        //THEN
        Assertions.assertThat(usuarios).isNotEmpty();

    }

    @Test
    @DisplayName("Dado un username eliminar la cuenta")
    void RemoveTest(String username){
        //GIVEN
        initMockUsuarios();
        //WHEN
        Usuario usuario = usuarioRepository.findByUsername(username);
        usuarioRepository.delete(usuario);
        List<Usuario> usuarios = usuarioRepository.findAll();
        //THEN
        Assertions.assertThat(usuarios).hasSize(1);
    }

}