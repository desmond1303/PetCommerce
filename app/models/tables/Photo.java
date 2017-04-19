package models.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

/**
 * The type Photo.
 */
@Entity
@Table(name = "photo")
public class Photo extends BaseModel {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@Column(name = "path")
	private String path;

	@Column(name = "main")
	private Boolean main;


	/**
	 * Instantiates a new Photo.
	 */
	public Photo() {

	}

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
	 * Gets item.
	 *
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Sets item.
	 *
	 * @param item the item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Gets path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets path.
	 *
	 * @param path the path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets main.
	 *
	 * @return the main
	 */
	public Boolean getMain() {
		return main;
	}

	/**
	 * Sets main.
	 *
	 * @param main the main
	 */
	public void setMain(Boolean main) {
		this.main = main;
	}
}