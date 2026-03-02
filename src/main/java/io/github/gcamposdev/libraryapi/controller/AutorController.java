package io.github.gcamposdev.libraryapi.controller;

import io.github.gcamposdev.libraryapi.dto.AutorDTO;
import io.github.gcamposdev.libraryapi.model.Autor;
import io.github.gcamposdev.libraryapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping()
    public ResponseEntity salvar(@RequestBody AutorDTO autorDTO){
        //implementação em breve
        return new ResponseEntity("Autor criado" + autorDTO, HttpStatus.CREATED);

    }
}
