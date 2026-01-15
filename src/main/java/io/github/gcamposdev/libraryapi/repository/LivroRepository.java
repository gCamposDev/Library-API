package io.github.gcamposdev.libraryapi.repository;

import io.github.gcamposdev.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
