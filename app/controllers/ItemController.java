package controllers;

import models.helpers.ItemFilter;
import models.tables.Item;
import models.tables.ItemReview;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.ItemService;

import javax.inject.Inject;
import java.util.UUID;

/**
 * The Item Controller.
 */
public class ItemController extends BaseController {

	private static final String PAGE_NUMBER = "page";
	private static final String PAGE_SIZE = "pageSize";
	private static final String NAME_FILTER = "name";
	private static final String SORT_KEY = "sortKey";
	private static final String SORT_ORDER = "sortOrder";
	private static final Integer DEFAULT_PAGE_NUMBER = 1;
	private static final Integer DEFAULT_PAGE_SIZE = 12;
	private static final Class<Item> ITEM_CLASS = Item.class;
	private static final Class<ItemReview> REVIEW_CLASS = ItemReview.class;

	private ItemService service;

	/**
	 * Sets service.
	 *
	 * @param service the service
	 */
	@Inject
	public void setService(ItemService service) {
		this.service = service;
	}

	/**
	 * Creates new {@link Item}.
	 *
	 * @return the {@link Result}
	 */
	@Transactional
	public Result create() {
		return wrapForAdmin(() -> this.service.create(formFactory.form(ITEM_CLASS).bindFromRequest().get()));
	}

	/**
	 * Edits an existing {@link Item}.
	 *
	 * @return the {@link Result}
	 */
	@Transactional
	public Result edit() {
		return wrapForAdmin(() -> this.service.edit(formFactory.form(ITEM_CLASS).bindFromRequest().get()));
	}

	/**
	 * Deletes an {@link Item} with given {@link UUID}.
	 *
	 * @param id the id
	 * @return the {@link Result}
	 */
	@Transactional
	public Result delete(String id) {
		return wrapForAdmin(() -> this.service.delete(UUID.fromString(id)));
	}

	/**
	 * Gets filtered list of {@link Item}.
	 *
	 * @return the {@link Result}
	 */
	@Transactional(readOnly = true)
	public Result filter() {
		return wrapForPublic(() -> this.service.filter(
				ItemFilter.createFilter()
						.setPageNumber(getQueryInt(PAGE_NUMBER, DEFAULT_PAGE_NUMBER))
						.setPageSize(getQueryInt(PAGE_SIZE, DEFAULT_PAGE_SIZE))
						.setNameFilter(getQueryString(NAME_FILTER))
						.setSortKey(getQueryString(SORT_KEY))
						.setSortOrder(getQueryString(SORT_ORDER))
		));
	}

	/**
	 * Gets item with {@link UUID}.
	 *
	 * @param id the id
	 * @return the {@link Result}
	 */
	@Transactional(readOnly = true)
	public Result find(String id) {
		return wrapForPublic(() -> this.service.find(UUID.fromString(id)));
	}


	/**
	 * Post a review.
	 *
	 * @return the {@link Result}
	 */
	@Transactional
	public Result review() {
		return wrapForUser(() -> this.service.review(
				formFactory.form(REVIEW_CLASS).bindFromRequest().get(),
				this.cache.get(session("uid"))
		));
	}
}
