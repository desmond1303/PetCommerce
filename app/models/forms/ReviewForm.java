package models.forms;

import models.BaseModel;

import java.util.UUID;

/**
 * The type Review form.
 */
public class ReviewForm extends BaseModel {

	private UUID itemId;
	private Integer stars;

	/**
	 * Instantiates a new Review form.
	 */
	public ReviewForm() { }

	/**
	 * Gets item id.
	 *
	 * @return the item id
	 */
	public UUID getItemId() {
		return this.itemId;
	}

	/**
	 * Sets item id.
	 *
	 * @param itemId the item id
	 */
	public void setItemId(UUID itemId) {
		this.itemId = itemId;
	}

	/**
	 * Gets stars.
	 *
	 * @return the stars
	 */
	public Integer getStars() {
		return stars;
	}

	/**
	 * Sets stars.
	 *
	 * @param stars the stars
	 */
	public void setStars(Integer stars) {
		this.stars = stars;
	}
}
