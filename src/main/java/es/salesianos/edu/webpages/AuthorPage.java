package es.salesianos.edu.webpages;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;



import es.salesianos.edu.model.Author;
import es.salesianos.edu.service.AuthorService;


public class AuthorPage extends WebPage {


	private static final long serialVersionUID = 1L;

	@SpringBean
	AuthorService simulacroService;

	@SpringBean
	Author authorDto;

	public AuthorPage() {
		add(new Label("title", getString("title")));
		Form<Author> formAuthor = new Form<Author>("formAddNewAuthor", new CompoundPropertyModel<Author>(authorDto)){

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				boolean isInserted = simulacroService.insertNewAuthor(getModelObject());
				FeedbackMessage message;
				if(isInserted){
					message = new FeedbackMessage(this, "Autor insertado", FeedbackMessage.INFO);
				} else {
					message = new FeedbackMessage(this, "No se pudo insertar", FeedbackMessage.INFO);
				}
				getFeedbackMessages().add(message);
			}
		};
		formAuthor.add(new Label("nameAuthorLabel", getString("author.name")));
		formAuthor.add(new Label("dateOfBirthLabel", getString("date.of.birth")));
		formAuthor.add(new RequiredTextField<String>("nameAuthor"));
		DateTextField  datetimePicker = new DateTextField ("dateOfBirth", "yyyy-MM-dd");
		formAuthor.add(datetimePicker);
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);
		add(formAuthor);

		
	}

}
