package com.projetolivraria.livraria.service;

import com.projetolivraria.livraria.model.Author;
import com.projetolivraria.livraria.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {


    @Autowired
    private AuthorRepository repository;

    public Author newAuthor(Author author) {
        if (author.getAuthor().isEmpty()) {
            throw new RuntimeException("Error while trying to create new Author");
        } else {
            return repository.save(author);
        }
    }

    public List<Author> findAll() {
        List<Author> authors = repository.findAll();
        if (authors.isEmpty()) {
            System.out.println("No data found");
            return authors;
        }
        return authors;
    }

    public Author findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author with id: " + id + "not found"));
    }

    public Author editAuthor(Author author) {
        Author authorToEdit = repository.findById(author.getId())
                .orElseThrow(() -> new RuntimeException("Author with id: " + author.getId() + "not found"));
        authorToEdit.setAuthor(author.getAuthor());
        return repository.save(author);
    }

    public void deleteAuthor(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        repository.deleteById(id);
        ResponseEntity.noContent().build();
    }
}
