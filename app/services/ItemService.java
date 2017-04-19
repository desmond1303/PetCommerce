package services;

import models.helpers.ItemFilter;
import models.helpers.PaginationAdapter;
import models.tables.Item;
import models.tables.ItemReview;
import models.tables.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

/**
 * The type item service.
 */
@Singleton
public class ItemService extends BaseService {

	@Inject
	private ItemService() { }

	/**
	 * Gets item with id.
	 *
	 * @param id the id
	 * @return the item with id
	 */
	public Item find(final UUID id) {
		return (Item) getSession().createCriteria(Item.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	/**
	 * Create item boolean.
	 *
	 * @param item the item
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean create(final Item item) throws Exception {
		getSession().save(item);
		return true;
	}

	/**
	 * Edit item boolean.
	 *
	 * @param item the item
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean edit(final Item item) throws Exception {
		getSession().merge(item);
		return true;
	}

	/**
	 * Delete item boolean.
	 *
	 * @param id the id
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean delete(final UUID id) throws Exception {
		getSession().delete(find(id));
		return true;
	}

	/**
	 * Find items with filter pagination adapter.
	 *
	 * @param itemFilter the item filter
	 * @return the pagination adapter
	 */
	@SuppressWarnings("unchecked")
	public PaginationAdapter<Item> filter(final ItemFilter itemFilter) {
		Criteria criteria = getSession().createCriteria(Item.class);

		if (itemFilter.name != null) {
			criteria.add(Restrictions.ilike("name", itemFilter.name, MatchMode.ANYWHERE));
		}

		Double rowCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).doubleValue();
		Long numberOfPages = ((Double) Math.ceil(rowCount / itemFilter.pageSize)).longValue();

		criteria.setProjection(null)
				.setFirstResult((itemFilter.pageNumber - 1) * itemFilter.pageSize)
				.setMaxResults(itemFilter.pageSize);

		if (itemFilter.sortOrder != null) {
			if (itemFilter.sortOrder.equals("asc")) {
				criteria.addOrder(Order.asc(itemFilter.sortKey));
			} else if (itemFilter.sortOrder.equals("desc")) {
				criteria.addOrder(Order.desc(itemFilter.sortKey));
			}
		} else {
			criteria.addOrder(Order.asc("name"));
		}


		List<Item> items = criteria.list();

		return PaginationAdapter.createOutput()
				.setPageNumber(itemFilter.pageNumber)
				.setPageSize(itemFilter.pageSize)
				.setModel(items)
				.setNumberOfPages(numberOfPages);
	}

	/**
	 * Review boolean.
	 *
	 * @param itemReview the item review
	 * @param user       the user
	 * @return the boolean
	 */
	public Boolean review(ItemReview itemReview, User user) {
		return false;
	}
}
