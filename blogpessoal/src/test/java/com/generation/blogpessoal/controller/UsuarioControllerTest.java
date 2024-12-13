package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start() {
        usuarioRepository.deleteAll();

        usuarioService.cadastrarUsuario(new Usuario(null, "Root", "root@root.com", "rootroot", "-"));
    }

@Test
@DisplayName("Cadastrar um usuário")
public void deveCriarUmUsuario() {
        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "12313442", "-"));
        ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);
        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
}

    @Test
    @DisplayName("Não deve permitir duplicação de usuários")
    public void naoDeveDuplicarUsuariosTest() {

        usuarioService.cadastrarUsuario(new Usuario(null,"Dayana dos Santos", "dayana.sants@gmail.com", "147258369", "-"));

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(0L, "Dayana dos Santos", "dayana.sants@gmail.com", "147258369", "-"));

        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);
        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());

    }

    @Test
    @DisplayName("Atualizar um usuário")
    public void atualizarUsuarioTest() {

        Optional<Usuario> usuarioCadastro = usuarioService.cadastrarUsuario(new Usuario(null, "Clara Oliveira", "clara.oliveira@gmail.com", "123456", "-"));

        Usuario usuarioUpdate = new Usuario(usuarioCadastro.get().getId(), "Clara Vitória Oliveira", "clara.vit@gmail.com", "123456", "-");

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuarioUpdate);

        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuario.class);

        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

    }

    @Test
    @DisplayName("Listar todos os usuários")
    public void deveMostrarTodosUsuarios() {
        usuarioService.cadastrarUsuario(new Usuario(null, "Isabella Mendes", "isabella.mendes@gmail.com", "123456", "-"));

        usuarioService.cadastrarUsuario(new Usuario(null, "Tereza Mendes", "tereza.mendes@gmail.com", "123456", "-"));

        ResponseEntity<String> resposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/all", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());

    }
}
