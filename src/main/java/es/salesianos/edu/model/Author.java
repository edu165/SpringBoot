package es.salesianos.edu.model;

import java.io.Serializable;
import java.util.Date;

public class Author implements Serializable {

	private String nameAuthor;
	private Date dateOfBirth;

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
