package services;

import exceptions.ServiceException;
import models.forms.LoginForm;
import models.forms.RegisterForm;
import models.tables.User;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

/**
 * The type User service.
 */
@Singleton
public class UserService extends BaseService {

	@Inject
	private UserService() { }

	/**
	 * Authenticate user.
	 *
	 * @param loginForm the login form
	 * @return the {@link User}
	 * @throws ServiceException the service exception
	 */
	public User authenticate(final LoginForm loginForm) throws ServiceException {
		try {
			if (checkCredentials(
					loginForm.getEmail(),
					base64Encode(
							Passwords.hash(
									loginForm.getPassword().toCharArray(),
									getSalt(loginForm.getEmail())
							)
					)
			)) {
				return find(loginForm.getEmail());
			} else {
				throw new ServiceException("Login Error", "Invalid Password");
			}
		} catch (NullPointerException e) {
			throw new ServiceException("Login Error", "Invalid Email");
		}
	}

	/**
	 * Register user.
	 *
	 * @param registerForm the register form
	 * @return the {@link User}
	 */
	public User register(final RegisterForm registerForm) {
		try {
			User newUser = registerForm.createUser();
			getSession().save(newUser);
			return newUser;
		} catch (Exception e) {
			throw e;
		}
	}

	private byte[] getSalt(final String email) {
		String userSalt = (String) getSession().createCriteria(User.class)
				.add(Restrictions.eq("email", email))
				.setProjection(Projections.property("salt"))
				.uniqueResult();

		if (userSalt != null) {
			return base64Decode(userSalt);
		} else {
			return null;
		}
	}

	private Boolean checkCredentials(final String email, final String hash) {
		User user = (User) getSession().createCriteria(User.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("hash", hash))
				.uniqueResult();

		return user != null;
	}

	/**
	 * Gets user.
	 *
	 * @param userId the user id
	 * @return the {@link User}
	 */
	public User find(final UUID userId) {
		return (User) getSession().createCriteria(User.class)
				.add(Restrictions.eq("id", userId))
				.uniqueResult();
	}

	/**
	 * Get user.
	 *
	 * @param email the email
	 * @return the {@link User}
	 */
	public User find(final String email) {
		return (User) getSession().createCriteria(User.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult();
	}

	/**
	 * Gets all users.
	 *
	 * @return the {@link List} of {@link User}
	 */
	@SuppressWarnings("unchecked")
	public List<User> all() {
		return (List<User>) getSession().createCriteria(User.class).list();
	}

	/**
	 * Edit user boolean.
	 *
	 * @param user the user
	 * @return the {@link Boolean}
	 */
	public Boolean edit(User user) {
		if (user.getId() != null) {
			User dbUser = this.find(user.getId());
			dbUser.setIsAdmin(user.getIsAdmin());

			getSession().update(dbUser);
			return true;
		}
		return false;
	}


	/**
	 * Delete user boolean.
	 *
	 * @param id the id
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean delete(final UUID id) throws Exception {
		getSession().delete(find(id));
		return true;
	}
}
