package es.salesianos.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.salesianos.edu.repository.BookRepository;

import es.salesianos.edu.model.Book;

@Service
public class BookService {
	@Autowired
	BookRepository repository;

	public boolean insertNewAuthor(Book book) {
		repository.insertNewBook(book);
		return true;
	}

	public List<Book> findBook(Book book) {
		return repository.findBooks(book);
	}

}
