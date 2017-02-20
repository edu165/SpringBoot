package es.salesianos.edu.webpages;

import java.util.Collections;
import java.util.List;

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


import es.salesianos.edu.model.Author;
import es.salesianos.edu.service.AuthorService;;

public class ListAuthorPage extends WebPage {
	
	private static final long serialVersionUID = 1L;

	@SpringBean
	AuthorService authorService;
	
	@SpringBean
	Author author;
	
	
	
	private String authorName = null;
	private String date = null;
	
	private List<Author> listAuthor = Collections.emptyList();
	
	public ListAuthorPage(PageParameters parameters) {
		authorName = parameters.get("authorName").toString();
		date = parameters.get("dateOfBirth").toString();
		
		//logger.debug("Cargando la pagina con el parametro Autor: " + authorName);
		initComponents();
	}
	
	public ListAuthorPage() {
		initComponents();
	}

	private void initComponents() {
		add(new Label("title", "Listar Autores"));
		addForm();
		addFeedBackPanel();
		addListAuthorView();
		
	}
	
	private void addForm() {
		Form<Author> form = new Form<Author>("formListAuthor", new CompoundPropertyModel<Author>(author)){

			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit() {
				super.onSubmit();
				listAuthor.clear();
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("authorName", getModelObject().getNameAuthor());
				pageParameters.add("dateOfBirth", getModelObject().getDateOfBirth());
				setResponsePage(ListAuthorPage.class, pageParameters);
			}
		};
		
		
	    form.add(new TextField<String>("nameAuthor"));
	    DateTextField  datetimePicker = new DateTextField ("dateOfBirth", "yyyy-MM-dd");
		form.add(datetimePicker);
		add(form);
	}

	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}

	private void addListAuthorView() {
		Author author = new Author();
		if(authorName != null)
			author.setNameAuthor(authorName);
		if(date != null){
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
	        author.setDateOfBirth(sqlDate);
		}
		
		listAuthor = authorService.findAuthors(author);
		ListView<Author> listview = new ListView<Author>("author-group", listAuthor) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Author> item) {
				Author author = item.getModelObject();
				item.add(new Label("authorNameItem", author.getNameAuthor()));
				item.add(new Label("dateOfBirthItem", author.getDateOfBirth()));
			}
		};
		add(listview);
	}
}