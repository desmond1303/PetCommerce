package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * The Ember Controller. Only uses to pass though routes to Ember.JS
 */
public class EmberController extends Controller {

	/**
	 * @param slug the slug
	 * @return the {@link Result}
	 */
	public Result index(String slug) {
		return ok(index.render());
	}

}
