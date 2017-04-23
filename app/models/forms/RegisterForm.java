package models.forms;

import models.BaseModel;
import models.tables.User;

/**
 * The type Register form.
 */
public class RegisterForm extends BaseModel {

	private String name;
	private String email;
	private String password;

	/**
	 * Instantiates a new Register form.
	 */
	public RegisterForm() { }

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
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
		this.password = password;
	}

	/**
	 * Create user.
	 *
	 * @return the {@link User}
	 */
	public User createUser() {
		return new User(this.name, this.email, this.password);
	}
}
