package io.github.gcamposdev.libraryapi.dto;

import io.github.gcamposdev.libraryapi.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade) {

    /*
    Metodo para transformar o DTO em uma entidade mapeada(Autor)
    Porém não é uma boa pratica esse metodo estar na classe DTO, é melhor instanciar
    a entidade no Service, ou ter classes mapper.
    */
    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setNacionalidade(this.nacionalidade);
        autor.setDataNascimento(this.dataNascimento);
        return autor;
    }
}
