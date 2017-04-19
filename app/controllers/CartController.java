package controllers;

import models.tables.Item;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.CartService;

import javax.inject.Inject;
import java.util.UUID;

/**
 * The Cart Controller.
 *
 * @author dinopraso
 */
public class CartController extends BaseController {

	private static final Class<Item> ITEM_CLASS = Item.class;

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
	 * Returns a list of all items currently in the cart for the current user.
	 *
	 * @return the {@link Result}
	 */
	@Transactional(readOnly = true)
	public Result cart() {
		return wrapForUser(() -> this.service.cart(this.cache.get(session("uid"))));
	}

	/**
	 * Adds {@link Item} to the cart.
	 *
	 * @return the {@link Result}
	 */
	@Transactional
	public Result add() {
		return wrapForUser(() -> this.service.add(formFactory.form(ITEM_CLASS).bindFromRequest().get(), this.cache.get(session("uid"))));
	}

	/**
	 * Removes {@link Item} the cart based on the ID.
	 *
	 * @param id the id
	 * @return the {@link Result}
	 */
	@Transactional
	public Result delete(String id) {
		return wrapForUser(() -> this.service.delete(UUID.fromString(id), this.cache.get(session("uid"))));
	}

	/**
	 * Clears the cart.
	 *
	 * @return the {@link Result}
	 */
	@Transactional
	public Result clear() {
		return wrapForUser(() -> this.service.clear(this.cache.get(session("uid"))));
	}

}
