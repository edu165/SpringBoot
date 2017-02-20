package es.salesianos.edu.webpages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.ws.Holder;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import es.salesianos.edu.model.Book;
import es.salesianos.edu.service.BookService;;

public class ListBookPage extends WebPage {
	private static final long serialVersionUID = -1935854748907274886L;

	@SpringBean
	BookService bookService;
	
	@SpringBean
	Book book;

	//private static final Logger logger = LogManager.getLogger(ListBookPage.class.getName());

	private String titleParameters = null;
	private String isbnParameters = null;
	private String authorParameters = null;

	private List<Book> listBook =  Collections.emptyList();

	public ListBookPage(PageParameters parameters) {
		titleParameters = parameters.get("nameBook").toString();
		isbnParameters = parameters.get("isbn").toString();
		authorParameters = parameters.get("author").toString();
		//logger.debug("Cargando la pagina con el parametro titulo: "+ title +" ISBN: "+ isbn +" Autor: "+ author);
		initComponents();
	}

	public ListBookPage() {
		initComponents();
	}

	private void initComponents() {
		add(new Label("title", "Listar Librossss"));
		addFormBook();
		addFeedBackPanel();
		addListBookView();
		add(new BookmarkablePageLink<String>("libraryLink", HomePage.class));
	}

	private void addFormBook() {
		Form<Book> form = new Form<Book>("formListBook", new CompoundPropertyModel<Book>(book)) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				listBook.clear();
				PageParameters pageParameters = new PageParameters();
				if(getModelObject().getNameBook() != null)
					pageParameters.add("nameBook", getModelObject().getNameBook());
				if(getModelObject().getISBN() != null)
					pageParameters.add("isbn", getModelObject().getISBN());
				if(getModelObject().getNameAuthor() != null)
					pageParameters.add("author", getModelObject().getNameAuthor());
				setResponsePage(ListBookPage.class, pageParameters);
			}
		};
		form.add(new Label("nameBookLabel", getString("book.name")));
		form.add(new Label("ISBNLabel", getString("ISBN")));
		form.add(new Label("nameAuthorLabel", getString("date.of.birth")));
		
		form.add(new TextField<String>("nameBook"));
		form.add(new TextField<String>("ISBN"));
		form.add(new TextField<String>("nameAuthor"));
		add(form);
	}

	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}

	private void addListBookView() {
		Book book = new Book();
		
			book.setNameBook(titleParameters);
		
			book.setISBN(isbnParameters);
		
			book.setNameAuthor(authorParameters);
		listBook = bookService.findBook(book);
		ListView<Book> listview = new ListView<Book>("book-group", listBook) {
			
	private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Book> item) {
				Book book = item.getModelObject();
				item.add(new Label("titleList", book.getNameBook()));
				item.add(new Label("isbnList", book.getISBN()));
				item.add(new Label("authorList", book.getNameAuthor()));
			}
		};
		add(listview);
	}

}
