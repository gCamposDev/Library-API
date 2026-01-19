package io.github.gcamposdev.libraryapi.repository;

import io.github.gcamposdev.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

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
        UUID id = UUID.fromString("860bf609-cf10-4ae3-ace0-5eb97870de10");

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
        UUID id = UUID.fromString("860bf609-cf10-4ae3-ace0-5eb97870de10");
        autorRepository.deleteById(id);

    }

    @Test
    public void deleteTest(){
        UUID id = UUID.fromString("45b34030-7f34-4f87-a8ee-d24050517c02");
        Autor autor = autorRepository.findById(id).get();
        autorRepository.delete(autor);

    }
}
