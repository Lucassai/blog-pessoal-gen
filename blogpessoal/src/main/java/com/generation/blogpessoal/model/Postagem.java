

package com.generation.blogpessoal.model;
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
    private long id;

    @NotBlank(message = "O atributo título pe obrigatório")
    @Size(min = 5, max = 100, message = "O atributo titulo deve conter entre 5 e 100 caracteres")
    private String titulo;

    @NotBlank(message = "O atributo título pe obrigatório")
    @Size(min = 10, max = 1000, message = "O atributo texto deve conter entre 10 e 1000 caracteres")
    private String texto;

    @UpdateTimestamp
    private LocalDateTime data;

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
}
