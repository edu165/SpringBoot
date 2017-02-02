package es.salesianos.edu.webpages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {

	public HomePage() {
		Label label = new Label("holamundo", Model.of("buenos dias"));
		add(label);
	}

}
