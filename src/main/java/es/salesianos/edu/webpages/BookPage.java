package es.salesianos.edu.webpages;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;

import es.salesianos.edu.service.BookService;

import es.salesianos.edu.model.Author;
import es.salesianos.edu.model.Book;


public class BookPage extends WebPage {

	@SpringBean
	BookService bokService;
	
	@SpringBean
	Book book;
	
	public BookPage() {

		add(new Label("title", getString("title")));
		Form<Book> formBook = new Form<Book>("formAddNewBook", new CompoundPropertyModel<Book>(book)){

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				boolean isInserted = bokService.insertNewAuthor(getModelObject());
				FeedbackMessage message;
				if(isInserted){
					message = new FeedbackMessage(this, "Libro  insertado", FeedbackMessage.INFO);
				} else {
					message = new FeedbackMessage(this, "No se pudo insertar", FeedbackMessage.INFO);
				}
				getFeedbackMessages().add(message);
			}
		};
		//los Label
		formBook.add(new Label("nameBookLabel", getString("book.name")));
		formBook.add(new Label("ISBNLabel", getString("ISBN")));
		formBook.add(new Label("nameAuthorLabel", getString("author.name")));
		//añadimos donde se escribe
		formBook.add(new RequiredTextField("nameBook"));
		formBook.add(new RequiredTextField("ISBN"));
		formBook.add(new RequiredTextField("nameAuthor"));
		
		//añadimos el panel don esta los mensajes
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);
		add(formBook);
		
		add(new BookmarkablePageLink<String>("LinkHomePage", HomePage.class));

	}

}
