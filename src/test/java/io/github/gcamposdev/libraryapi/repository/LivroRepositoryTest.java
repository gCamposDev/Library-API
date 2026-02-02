package io.github.gcamposdev.libraryapi.repository;

import io.github.gcamposdev.libraryapi.model.Autor;
import io.github.gcamposdev.libraryapi.model.GeneroLivro;
import io.github.gcamposdev.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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

    @Test
    public void atualizarAutorDoLivroTest(){
        //Busco no banco o Livro que desejo atualizar
        Livro livroEntity = livroRepository
                .findById(UUID.fromString("3e4f5224-5c47-4798-8c1a-b4344b397e09"))
                .orElse(null); //Caso não exista retornará null

        //Busco no banco o Autor do livro
        Autor novoAutor = autorRepository
                .findById(UUID.fromString("b7db293a-6601-4b19-8a29-d6743c509707"))
                .orElse(null);

        livroEntity.setAutor(novoAutor);
        livroRepository.save(livroEntity);

    }

    @Test
    public void deletarPorIdTest(){
        UUID id = UUID.fromString("17032cb4-1102-4954-9022-6f55d69df703");
        livroRepository.deleteById(id);

    }

    @Test
    public void deletarTest(){
        //Busco no banco o Livro que desejo deletar
        Livro livroEntity = livroRepository
                .findById(UUID.fromString("3e4f5224-5c47-4798-8c1a-b4344b397e09"))
                .orElse(null); //Caso não exista retornará null

        livroRepository.delete(livroEntity);

    }

    @Test
    public void buscarLivroTest(){
        UUID id = UUID.fromString("0f7628e0-bec4-4ae4-91c2-b868125bd531");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());
        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    public void pesquisaPorTituloTest(){
        List<Livro> lista = livroRepository.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    public void pesquisaPorIsbnTest(){
        Livro livro = livroRepository.findByIsbn("912912-121203");
        System.out.println(livro);
    }

    @Test
    public void pesquisaPorTituloEPrecoTest(){
        Livro livro = livroRepository.findByTituloAndPreco("UFO", BigDecimal.valueOf(100.00));
        System.out.println(livro);
    }

    @Test
    public void listarLivrosComQueryJPQL(){
        List<Livro> lista = livroRepository.listarTodos();
        lista.forEach(System.out::println);
    }

    @Test
    public void listarAutoresDosLivros(){
        List<Autor> lista = livroRepository.listarAutoresDosLivros();
        lista.forEach(System.out::println);
    }

    @Test
    public void listarTitulosNaoRepetidosDosLivros(){
        List<String> lista = livroRepository.listarTitulosDiferentesLivros();
        lista.forEach(System.out::println);
    }

    @Test
    public void listarGerenosDeLivrosAutoresBrasileiros(){
        List<String> lista = livroRepository.listarGenerosAutoresBrasileiros();
        lista.forEach(System.out::println);
    }

    @Test
    public void listarPorGeneroQueryNamedParamTest(){
        List<Livro> lista = livroRepository.findByGenero(GeneroLivro.MISTERIO);
        lista.forEach(System.out::println);
    }

    @Test
    public void listarPorGeneroQueryPositionalParamTest(){
        List<Livro> lista = livroRepository.findByGeneroPositionalParam(GeneroLivro.MISTERIO);
        lista.forEach(System.out::println);
    }

}

