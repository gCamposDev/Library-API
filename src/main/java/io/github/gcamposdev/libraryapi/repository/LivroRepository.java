package io.github.gcamposdev.libraryapi.repository;

import io.github.gcamposdev.libraryapi.model.Autor;
import io.github.gcamposdev.libraryapi.model.GeneroLivro;
import io.github.gcamposdev.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query Method
    // select * from livro where id_autor = id_autor
    List<Livro> findByAutor(Autor autor);

    //select * from livro where titulo = titulo
    List<Livro> findByTitulo(String titulo);

    //select * from livro where isbn = isbn
    Livro findByIsbn(String isbn);

    Livro findByTituloAndPreco(String titulo, BigDecimal preco);

    @Query("select l from Livro as l order by l.titulo")
    List<Livro> listarTodos();

    // SQL = select a.* from livro l join autor a on a.id = l.id_autor
    /* Nessa pesquisa só irá retornar autores que possuem livros,
     pois estamos buscando em livro, se o autor não tiver livro não será encontrado
     */
    @Query("select a from Livro l join l.autor a")
    List<Autor> listarAutoresDosLivros();

    // SQL = select distinct l.titulo from livro l
    @Query("select distinct l.titulo from Livro l")
    List<String> listarTitulosDiferentesLivros();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
    """)
    List<String> listarGenerosAutoresBrasileiros();

    //named parameters -> parametros nomeados
    @Query("select l from Livro l where l.genero = :generoLivro")
    List<Livro> findByGenero(@Param("generoLivro") GeneroLivro generoLivro);

    //positional parameters
    @Query("select l from Livro l where l.genero = ?1")
    List<Livro> findByGeneroPositionalParam(GeneroLivro generoLivro);


}
