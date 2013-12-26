package com.checktenerife;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	public HomePage() {
		add(new BookmarkablePageLink<Object>("homeLink", Home.class));
		add(new BookmarkablePageLink<Object>("servicesLink", Services.class));
		add(new BookmarkablePageLink<Object>("aboutUsLink", AboutUs.class));
		add(new BookmarkablePageLink<Object>("contactLink", Contact.class));
	}

	public HomePage(final PageParameters parameters) {

		//		super(parameters);
		//
		//		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		// TODO Add your page's components here

	}
}
