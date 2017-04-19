package controllers;

import models.tables.Item;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.CartService;

import javax.inject.Inject;
import java.util.UUID;

/**
 * The type Cart controller.
 *
 * @author dinopraso
 */
public class CartController extends BaseController {

	private CartService service;

	/**
	 * Sets service.
	 *
	 * @param service the service
	 */
	@Inject
	public void setService(CartService service) {
		this.service = service;
	}

	/**
	 * Cart result.
	 *
	 * @return the result
	 */
	@Transactional(readOnly = true)
	public Result cart() {
		return wrapForUser(() -> this.service.cart(this.cache.get(session("uid"))));
	}

	/**
	 * Add result.
	 *
	 * @return the result
	 */
	@Transactional
	public Result add() {
		return wrapForUser(() -> this.service.add(formFactory.form(Item.class).bindFromRequest().get(), this.cache.get(session("uid"))));
	}

	/**
	 * Delete result.
	 *
	 * @param id the id
	 * @return the result
	 */
	@Transactional
	public Result delete(String id) {
		return wrapForUser(() -> this.service.delete(UUID.fromString(id), this.cache.get(session("uid"))));
	}

	/**
	 * Clear result.
	 *
	 * @return the result
	 */
	@Transactional
	public Result clear() {
		return wrapForUser(() -> this.service.clear(this.cache.get(session("uid"))));
	}

}
