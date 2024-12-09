package com.generation.blogpessoal.security;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service //A Classe foi anotada com a anotação @Service, o que indica que esta Classe é uma Classe de Serviço. Classe de Serviço é uma Classe responsável por implementar as regra de negócio e as tratativa de dados de uma parte do ou recurso do sistema.

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String UserName) throws UsernameNotFoundException{

        Optional<Usuario> usuario = usuarioRepository.findByUsuario(UserName);

        if (usuario.isPresent())
            return new UserDetailsImpl(usuario.get());
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}
