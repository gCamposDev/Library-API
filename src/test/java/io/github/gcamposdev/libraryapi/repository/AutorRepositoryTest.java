package io.github.gcamposdev.libraryapi.repository;

import io.github.gcamposdev.libraryapi.model.Autor;
import io.github.gcamposdev.libraryapi.model.GeneroLivro;
import io.github.gcamposdev.libraryapi.model.Livro;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setDataNascimento(LocalDate.of(1959,12,30));
        autor.setNome("Jonas");
        autor.setNacionalidade("Brasileira");

        autorRepository.save(autor);
    }

    @Test
    public void atualizarTest(){
        UUID id = UUID.fromString("44922b57-5fcc-4ab3-a68a-29b938c245a2");

        Optional<Autor> optionalAutor = autorRepository.findById(id);

        if(optionalAutor.isPresent()){
            Autor autor = optionalAutor.get();
            autor.setDataNascimento(LocalDate.of(1969, 12, 30));

            autorRepository.save(autor);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest(){
        UUID id = UUID.fromString("44922b57-5fcc-4ab3-a68a-29b938c245a2");
        autorRepository.deleteById(id);

    }

    @Test
    public void deleteTest(){
        UUID id = UUID.fromString("44922b57-5fcc-4ab3-a68a-29b938c245a2");
        Autor autor = autorRepository.findById(id).get();
        autorRepository.delete(autor);

    }

    @Test
    public void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setDataNascimento(LocalDate.of(1978,2,3));
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");

        Livro livro = new Livro();
        livro.setIsbn("1312912-12103");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("O roubo");
        livro.setDataPublicacao(LocalDate.of(2000,1,2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("112342-12103");
        livro2.setPreco(BigDecimal.valueOf(105));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("O assalto");
        livro2.setDataPublicacao(LocalDate.of(2002,1,2));
        livro2.setAutor(autor);

        // Crio uma lista para salvar os livros do autor.
        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    public void listarLivrosAutor(){
        UUID id = UUID.fromString("5aaf4ffb-f58d-4cd2-8a23-414aecdef8ce");
        Autor autor = autorRepository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}
