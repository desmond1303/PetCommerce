package models.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.BaseModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * The type Order.
 */
@Entity
@Table(name = "\"order\"")
public class Order extends BaseModel {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;

	@Column(name = "date")
	private Timestamp date = new Timestamp(System.currentTimeMillis());

	/**
	 * Instantiates a new Item.
	 */
	public Order() {
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
	 * Gets user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets user.
	 *
	 * @param user the user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets items.
	 *
	 * @return the items
	 */
	public List<OrderItem> getItems() {
		return items;
	}

	/**
	 * Sets items.
	 *
	 * @param items the items
	 */
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	/**
	 * Gets date.
	 *
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * Sets date.
	 *
	 * @param date the date
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
}