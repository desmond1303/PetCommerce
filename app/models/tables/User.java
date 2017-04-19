package models.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.BaseModel;
import services.Passwords;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Base64;
import java.util.UUID;

/**
 * The type User.
 */
@Entity
@Table(name = "\"user\"")
public class User extends BaseModel {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@JsonIgnore
	@Column(name = "hash")
	private String hash;

	@JsonIgnore
	@Column(name = "salt")
	private String salt;

	@Column(name = "is_admin")
	private Boolean isAdmin;

	/**
	 * Instantiates a new User.
	 */
	public User() {}

	/**
	 * Instantiates a new User.
	 *
	 * @param name     the first name
	 * @param email    the email
	 * @param password the password
	 */
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.setPassword(password);
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public UUID getId() {
		return this.id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(UUID id) {
		this.id = id;}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
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
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.salt = base64Encode(Passwords.getNextSalt());
		this.hash = base64Encode(Passwords.hash(password.toCharArray(), base64Decode(this.salt)));
	}

	private String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	private byte[] base64Decode(String string) {
		return Base64.getDecoder().decode(string);
	}

	/**
	 * Gets is admin.
	 *
	 * @return the is admin
	 */
	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	/**
	 * Sets is admin.
	 *
	 * @param admin the admin
	 */
	public void setIsAdmin(Boolean admin) {
		this.isAdmin = admin;
	}

}
