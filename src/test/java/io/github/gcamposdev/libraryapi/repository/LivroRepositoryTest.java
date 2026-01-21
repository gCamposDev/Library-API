package io.github.gcamposdev.libraryapi.repository;

import io.github.gcamposdev.libraryapi.model.Autor;
import io.github.gcamposdev.libraryapi.model.GeneroLivro;
import io.github.gcamposdev.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("912912-121203");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = autorRepository
                .findById(UUID.fromString("44922b57-5fcc-4ab3-a68a-29b938c245a2"))
                .orElse(null);

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    //Testando cascade -> O autor Cleitin foi salvo no banco mesmo eu não chamando a função save() para o Autor, apenas para o Livro
    @Test
    public void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("912912-121203");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO2");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = new Autor();
        autor.setDataNascimento(LocalDate.of(1959,12,30));
        autor.setNome("Cleitin");
        autor.setNacionalidade("Brasileira");

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

}