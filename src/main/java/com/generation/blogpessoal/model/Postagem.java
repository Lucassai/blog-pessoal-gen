

package com.generation.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_postagens")
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo título pe obrigatório")
    @Size(min = 5, max = 100, message = "O atributo titulo deve conter entre 5 e 100 caracteres")
    private String titulo;

    @NotBlank(message = "O atributo título pe obrigatório")
    @Size(min = 10, max = 1000, message = "O atributo texto deve conter entre 10 e 1000 caracteres")
    private String texto;

    @UpdateTimestamp
    private LocalDateTime data;

    @ManyToOne
    @JsonIgnoreProperties("usuario")
    private Usuario usuario;

    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Tema tema;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
