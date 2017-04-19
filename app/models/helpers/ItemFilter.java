package models.helpers;

import exceptions.ServiceException;

/**
 * The type Item filter.
 */
public class ItemFilter {
	/**
	 * The Page number.
	 */
	public Integer pageNumber = 1;
	/**
	 * The Page size.
	 */
	public Integer pageSize = 9;

	/**
	 * The Name.
	 */
	public String name;

	/**
	 * The Sort key.
	 */
	public String sortKey;

	/**
	 * The Sort order.
	 */
	public String sortOrder;

	private ItemFilter() { }

	/**
	 * Create filter item filter.
	 *
	 * @return the item filter
	 */
	public static ItemFilter createFilter() {
		return new ItemFilter();
	}

	/**
	 * Sets page number.
	 *
	 * @param pageNumber the page number
	 * @return the page number
	 * @throws ServiceException the service exception
	 */
	public ItemFilter setPageNumber(Integer pageNumber) throws ServiceException {
		if (pageNumber <= 0) {
			throw new ServiceException("Format Exception", "Page Number must be a Positive Integer");
		} else {
			this.pageNumber = pageNumber;
		}
		return this;
	}

	/**
	 * Sets page size.
	 *
	 * @param pageSize the page size
	 * @return the page size
	 * @throws ServiceException the service exception
	 */
	public ItemFilter setPageSize(Integer pageSize) throws ServiceException {
		if (pageSize <= 0) {
			throw new ServiceException("Format Exception", "Page Size must be a Positive Integer");
		} else {
			this.pageSize = pageSize;
		}
		return this;
	}

	/**
	 * Sets name filter.
	 *
	 * @param name the name
	 * @return the name filter
	 */
	public ItemFilter setNameFilter(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Sets sort key.
	 *
	 * @param sortKey the sort key
	 * @return the sort key
	 */
	public ItemFilter setSortKey(String sortKey) {
		this.sortKey = sortKey;
		return this;
	}

	/**
	 * Sets sort order.
	 *
	 * @param sortOrder the sort order
	 * @return the sort order
	 */
	public ItemFilter setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}
}
