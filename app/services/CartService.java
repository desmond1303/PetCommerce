package services;

import models.tables.CartItem;
import models.tables.Item;
import models.tables.User;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The type Cart service.
 *
 * @author dinopraso
 */
@Singleton
public class CartService extends BaseService {

	@Inject
	private CartService() {}

	/**
	 * Find cart item.
	 *
	 * @param id the id
	 * @return the cart item
	 */
	public CartItem find(UUID id) {
		return (CartItem) getSession().createCriteria(CartItem.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	/**
	 * Cart list.
	 *
	 * @param user the user
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<CartItem> cart(User user) {
		return (List<CartItem>) getSession().createCriteria(CartItem.class)
				.add(Restrictions.eq("user.id", user.getId()))
				.list();
	}

	/**
	 * Add boolean.
	 *
	 * @param item the item
	 * @param user the user
	 * @return the boolean
	 */
	public Boolean add(Item item, User user) {
		List<CartItem> persistedItems = cart(user).stream()
				.filter(persistedItem -> persistedItem.getItem().getId().equals(item.getId()))
				.collect(Collectors.toList());

		if (!persistedItems.isEmpty()) {
			CartItem persistedItem = persistedItems.get(0);
			persistedItem.setQuantity(persistedItem.getQuantity() + item.getQuantity());
			getSession().merge(persistedItem);
			return true;
		} else {
			getSession().persist(new CartItem(item, user));
			return true;
		}
	}

	/**
	 * Delete boolean.
	 *
	 * @param id   the id
	 * @param user the user
	 * @return the boolean
	 */
	public Boolean delete(UUID id, User user) {
		CartItem cartItem = find(id);
		if (cartItem.getUser().getId().equals(user.getId())) {
			getSession().delete(find(id));
			return true;
		}
		return false;
	}

	/**
	 * Clear boolean.
	 *
	 * @param user the user
	 * @return the boolean
	 */
	public Boolean clear(User user) {
		cart(user).forEach(cartItem -> this.delete(cartItem.getId(), user));
		return true;
	}

}
