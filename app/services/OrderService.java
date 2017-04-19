package services;

import models.tables.Order;
import models.tables.OrderItem;
import models.tables.User;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

/**
 * The type Order service.
 *
 * @author dinopraso
 */
@Singleton
public class OrderService extends BaseService {

	private CartService cartService;

	/**
	 * Sets cart service.
	 *
	 * @param cartService the cart service
	 */
	@Inject
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	@Inject
	private OrderService() {}

	/**
	 * From cart boolean.
	 *
	 * @param user the user
	 * @return the boolean
	 */
	public Boolean fromCart(User user) {
		Order order = new Order();
		order.setUser(user);
		getSession().persist(order);

		cartService.cart(user).stream()
				.map(cartItem -> {
					OrderItem orderItem = new OrderItem(cartItem);
					orderItem.setOrder(order);
					return orderItem;
				})
				.forEach(orderItem -> getSession().persist(orderItem));

		cartService.clear(user);
		return true;
	}

	/**
	 * All list.
	 *
	 * @param user the user
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Order> all(User user) {
		return (List<Order>) getSession().createCriteria(Order.class)
				.add(Restrictions.eq("user.id", user.getId()))
				.list();
	}

	/**
	 * Find order.
	 *
	 * @param orderId the order id
	 * @param user    the user
	 * @return the order
	 */
	@SuppressWarnings("unchecked")
	public Order find(UUID orderId, User user) {
		return (Order) getSession().createCriteria(Order.class)
				.add(Restrictions.eq("id", orderId))
				.add(Restrictions.eq("user.id", user.getId()))
				.uniqueResult();
	}
}
