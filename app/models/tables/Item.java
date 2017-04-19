package models.tables;

import models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

/**
 * The type Item.
 */
@Entity
@Table(name = "item")
public class Item extends BaseModel {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Double price;

	@Column(name = "quantity")
	private Integer quantity;

	@OneToMany(mappedBy = "item")
	private List<ItemReview> reviews;

	@OneToMany(mappedBy = "item")
	private List<Photo> photos;

	/**
	 * Instantiates a new Item.
	 */
	public Item() {}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets price.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Sets price.
	 *
	 * @param price the price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Gets quantity.
	 *
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Sets quantity.
	 *
	 * @param quantity the quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets reviews.
	 *
	 * @return the reviews
	 */
	public List<ItemReview> getReviews() {
		return reviews;
	}

	/**
	 * Sets reviews.
	 *
	 * @param reviews the reviews
	 */
	public void setReviews(List<ItemReview> reviews) {
		this.reviews = reviews;
	}

	/**
	 * Gets photos.
	 *
	 * @return the photos
	 */
	public List<Photo> getPhotos() {
		return photos;
	}

	/**
	 * Sets photos.
	 *
	 * @param photos the photos
	 */
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
}