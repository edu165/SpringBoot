package es.salesianos.edu.model;

import java.sql.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class Author {
	
	private int idAuthor;
	private String nameAuthor;
	private Date dateOfBirth;

	public int getIdAuthor() {
		return idAuthor;
	}
	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

}
