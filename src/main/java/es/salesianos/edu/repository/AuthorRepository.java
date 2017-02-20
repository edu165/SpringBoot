package es.salesianos.edu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import es.salesianos.edu.connection.AbstractConnectionManager;
import es.salesianos.edu.model.Author;

@Service
public class AuthorRepository {
    @Autowired
	private AbstractConnectionManager conManager;
  



   public void insertNewAuthor(Author author) {
	//final Logger logger = LogManager.getLogger(AuthorRepository.class.getName());

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		//Author author = new Author();
		//asesembler.toEntity(authorDto, author);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("INSERT INTO AUTHOR(name, dateBirth) " + "VALUES (?, ?)");
			preparedStatement.setString(1, author.getNameAuthor());
			preparedStatement.setDate(2, author.getDateOfBirth());
			preparedStatement.executeUpdate();
			conManager.close(preparedStatement);

		} catch (Exception e) {
			//logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
	}
public ArrayList<Author> findAuthors(Author authorDto) {
	ArrayList<Author> list = new ArrayList<Author>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	try {
		connection = conManager.open();
		preparedStatement = connection.prepareStatement("SELECT * FROM AUTHOR WHERE name LIKE ? OR dateBirth = ?");
		preparedStatement.setString(1, "%" + authorDto.getNameAuthor() + "%");
		preparedStatement.setDate(2, authorDto.getDateOfBirth());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Author author = new Author();
			author.setNameAuthor(resultSet.getString(1));
			author.setDateOfBirth(resultSet.getDate(2));
			list.add(author);
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
