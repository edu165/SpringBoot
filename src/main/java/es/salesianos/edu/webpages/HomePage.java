package es.salesianos.edu.webpages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class HomePage extends WebPage {
	public HomePage() {
		BookmarkablePageLink bookmarkablePageLink = new BookmarkablePageLink("linkAuthorForm", AuthorPage.class);
		add(bookmarkablePageLink);
	}
}
