package controllers;

import play.db.jpa.Transactional;
import play.mvc.Result;
import services.OrderService;

import javax.inject.Inject;
import java.util.UUID;

/**
 * The type Order controller.
 */
public class OrderController extends BaseController {

	private OrderService service;

	/**
	 * Sets service.
	 *
	 * @param service the service
	 */
	@Inject
	public void setService(OrderService service) {
		this.service = service;
	}

	/**
	 * All result.
	 *
	 * @return the {@link Result}
	 */
	@Transactional(readOnly = true)
	public Result all() {
		return wrapForUser(() -> this.service.all(this.cache.get(session("uid"))));
	}

	/**
	 * Find result.
	 *
	 * @param id the id
	 * @return the {@link Result}
	 */
	@Transactional(readOnly = true)
	public Result find(String id) {
		return wrapForUser(() -> this.service.find(UUID.fromString(id), this.cache.get(session("uid"))));
	}

	/**
	 * From cart result.
	 *
	 * @return the {@link Result}
	 */
	@Transactional
	public Result fromCart() {
		return wrapForUser(() -> this.service.fromCart(this.cache.get(session("uid"))));
	}
}
