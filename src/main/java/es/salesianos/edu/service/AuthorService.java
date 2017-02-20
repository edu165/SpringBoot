package es.salesianos.edu.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.salesianos.edu.repository.AuthorRepository;

import es.salesianos.edu.model.Author;

@Service
public class AuthorService {
	@Autowired
	AuthorRepository repository;

	public boolean insertNewAuthor(Author author) {
		repository.insertNewAuthor(author);
		return true;
	}
	public ArrayList<Author> findAuthors(Author author) {
		return repository.findAuthors(author);
	}

}
