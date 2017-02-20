package es.salesianos.edu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.salesianos.edu.connection.AbstractConnectionManager;

import es.salesianos.edu.model.Book;

@Service
public class BookRepository {
	@Autowired
	private AbstractConnectionManager conManager;

	public void insertNewBook(Book book) {
		// final Logger logger =
		// LogManager.getLogger(AuthorRepository.class.getName());

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		// Author author = new Author();
		// asesembler.toEntity(authorDto, author);
		try {
			connection = conManager.open();
			preparedStatement = connection
					.prepareStatement("INSERT INTO BOOK(nameBook, ISBN, nameAuthor) VALUES (?, ?, ?)");
			preparedStatement.setString(1, book.getNameBook());
			preparedStatement.setString(2, book.getISBN());
			preparedStatement.setString(3, book.getNameAuthor());
			preparedStatement.executeUpdate();
			conManager.close(preparedStatement);

		} catch (Exception e) {
			// logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
	}

	public List<Book> findBooks(Book book) {
		ArrayList<Book> list = new ArrayList<Book>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = conManager.open();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM BOOK WHERE nameBook LIKE ? OR ISBN LIKE ? OR nameAuthor LIKE ?");
			preparedStatement.setString(1, "%" + book.getNameBook() + "%");
			preparedStatement.setString(2, "%" + book.getISBN() + "%");
			preparedStatement.setString(3, "%" + book.getNameAuthor() + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book bookResult = new Book();
				book.setNameBook(resultSet.getString(1));
				book.setISBN(resultSet.getString(2));
				book.setNameAuthor(resultSet.getString(3));
				list.add(bookResult);
			}
		} catch (Exception e) {

			throw new RuntimeException(e);
		} finally {
			conManager.close(resultSet);
			conManager.close(preparedStatement);
			conManager.close(connection);
		}

		return list;
	}

}
