package io.github.gcamposdev.libraryapi.service;

import io.github.gcamposdev.libraryapi.model.Autor;
import io.github.gcamposdev.libraryapi.model.GeneroLivro;
import io.github.gcamposdev.libraryapi.model.Livro;
import io.github.gcamposdev.libraryapi.repository.AutorRepository;
import io.github.gcamposdev.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar(){
        Livro livro = livroRepository
                .findById(UUID.fromString("bef5b23f-ffde-4370-a213-062ce23edc49"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2026,1,1));
    }

    @Transactional
    public void executar(){
        //Testando como funciona transações
        Autor autor = new Autor();
        autor.setDataNascimento(LocalDate.of(1959,12,30));
        autor.setNome("Fran");
        autor.setNacionalidade("Brasileira");

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("912912-121203");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Fran Book");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        livro.setAutor(autor);
        livroRepository.save(livro);

        // Forço a exceção
        if(autor.getNome().equals("Fran")){
            throw new RuntimeException("Rollback!");
        }
    }
}
