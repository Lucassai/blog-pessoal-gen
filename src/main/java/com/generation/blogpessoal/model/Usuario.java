package com.generation.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @NotNull(message = "O atributo nome é obrigatório")
    private String nome;


    @NotNull(message = "O atributo Usuário é obrigatório!")
    @Email(message = "O atributo Usuário deve ser um email válido!")
    @Schema(example = "email@email.com.br")
    private String usuario;

    @NotBlank(message = "O atributo senha é obrigatório")
    @Size(min = 6, message = "Tamanho mínimo da senha é de 6 dígitos")
    private String senha;

    @Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
    private String foto;

    @OneToMany(fetch = FetchType.LAZY
            , mappedBy = "usuario"
            , cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<Postagem> postagem;

    public Usuario(Long id, String nome, String usuario, String senha, String foto) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.foto = foto;
        this.version = 1L;
    }

    public Usuario(Long id, String nome, String email, String senha, String foto, Long version) {
        this(id, nome, email, senha, foto);
        this.version = version;
    }

    public Usuario() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O nome deve conter entre 3 e 50 caracteres") @Size(min = 3, max = 50) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome deve conter entre 3 e 50 caracteres") @Size(min = 3, max = 50) String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O atributo Usuário é obrigatório!") @Email(message = "O atributo Usuário deve ser um email válido!") String getUsuario() {
        return usuario;
    }

    public void setUsuario(@NotNull(message = "O atributo Usuário é obrigatório!") @Email(message = "O atributo Usuário deve ser um email válido!") String usuario) {
        this.usuario = usuario;
    }

    public @NotNull(message = "O atributo senha é obrigatório!") String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull(message = "O atributo senha é obrigatório!") String senha) {
        this.senha = senha;
    }

    public @Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres") String getFoto() {
        return foto;
    }

    public void setFoto(@Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres") String foto) {
        this.foto = foto;
    }

    public List<Postagem> getPostagem() {
        return postagem;
    }

    public void setPostagem(List<Postagem> postagem) {
        this.postagem = postagem;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
