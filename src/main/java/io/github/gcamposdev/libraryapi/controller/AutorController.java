package io.github.gcamposdev.libraryapi.controller;

import io.github.gcamposdev.libraryapi.dto.AutorDTO;
import io.github.gcamposdev.libraryapi.model.Autor;
import io.github.gcamposdev.libraryapi.repository.AutorRepository;
import io.github.gcamposdev.libraryapi.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorService autorService;

    public AutorController(AutorService autorService){
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autorDTO){
        Autor autorEntidade = autorDTO.mapearParaAutor();
        autorService.salvar(autorEntidade);

        // http://localhost:8080/autores/{id}
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterAutorPorId(@PathVariable("id") String id){
        UUID idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterAutorPorId(idAutor);
        if(autorOptional.isPresent()){ // Verifico se Optional realmente tem uma entidade
            Autor autor = autorOptional.get(); //Optional para Entidade
            AutorDTO autorDTO = new AutorDTO(autor.getId(),autor.getNome(), //Crio um DTO a partir de uma entidade
                    autor.getDataNascimento(),autor.getNacionalidade());

            return ResponseEntity.ok(autorDTO);
        }
        return ResponseEntity.notFound().build();//Caso a entidade não exista
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable String id){
        UUID idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterAutorPorId(idAutor);
        if(autorOptional.isEmpty()){
            return ResponseEntity.notFound().build(); //Se o id não existir
        }
        autorService.deletarAutor(autorOptional.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisarAutor(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade){

        List<Autor> autores = autorService.pesquisarAutor(nome,nacionalidade);
        List<AutorDTO> autoresDTO = autores
                .stream()
                .map(autor -> new AutorDTO(
                    autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade())
                ).collect(Collectors.toList());

        return ResponseEntity.ok(autoresDTO);
    }
}
