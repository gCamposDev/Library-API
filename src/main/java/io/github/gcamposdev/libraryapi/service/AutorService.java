package io.github.gcamposdev.libraryapi.service;

import io.github.gcamposdev.libraryapi.model.Autor;
import io.github.gcamposdev.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterDetalhesPorId(UUID id){
        return autorRepository.findById(id);
    }

}
