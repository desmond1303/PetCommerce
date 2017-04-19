package controllers;

import actions.LogActivity;
import models.helpers.SessionUser;
import models.helpers.forms.LoginForm;
import models.helpers.forms.RegisterForm;
import models.tables.User;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.UserService;

import javax.inject.Inject;
import java.util.UUID;

/**
 * The type User controller.
 */
public class UserController extends BaseController {
	private static final Class<User> USER_CLASS = User.class;
	private static final Class<LoginForm> LOGIN_FORM_CLASS = LoginForm.class;
	private static final Class<RegisterForm> REGISTER_FORM_CLASS = RegisterForm.class;

	private UserService service;

	/**
	 * Sets service.
	 *
	 * @param service the service
	 */
	@Inject
	public void setService(UserService service) {
		this.service = service;
	}

	/**
	 * Login result.
	 *
	 * return the {@link Result}
	 */
	@Transactional
	@LogActivity(message = "User Logged In")
	public Result login() {
		return wrapForPublic(() -> {
			User user = this.service.authenticate(formFactory.form(LOGIN_FORM_CLASS).bindFromRequest().get());

			UUID id = UUID.randomUUID();
			session().clear();
			session("uid", id.toString());
			this.cache.set(id.toString(), user);

			return user;
		});
	}

	/**
	 * Logout result.
	 *
	 * return the {@link Result}
	 */
	@Transactional(readOnly = true)
	public Result logout() {
		return wrapForPublic(() -> {
			this.cache.remove(session("uid"));
			session().clear();
			return "{}";
		});
	}

	/**
	 * Register result.
	 *
	 * return the {@link Result}
	 */
	@Transactional
	public Result register() {
		return wrapForPublic(() -> {
			User user = this.service.register(formFactory.form(REGISTER_FORM_CLASS).bindFromRequest().get());
			UUID id = UUID.randomUUID();
			session().clear();
			session("uid", id.toString());
			this.cache.set(id.toString(), user);
			return user;
		});
	}

	/**
	 * Gets current user.
	 *
	 * @return the current user
	 * @throws InterruptedException the interrupted exception
	 */
	@Transactional(readOnly = true)
	public Result getCurrent() throws InterruptedException {
		return wrapForPublic(() -> {
			User user = this.cache.get(session("uid"));
			if (user != null) {
				return new SessionUser(user);
			} else {
				return new SessionUser();
			}
		});
	}

	/**
	 * Gets all users.
	 *
	 * @return the all users
	 */
	@Transactional(readOnly = true)
	public Result all() {
		return wrapForAdmin(() -> this.service.findAll());
	}

	/**
	 * Gets user.
	 *
	 * @param userId the user id
	 * @return the user
	 */
	@Transactional(readOnly = true)
	public Result find(String userId) {
		return wrapForAdmin(() -> this.service.find(UUID.fromString(userId)));
	}

	/**
	 * Edit user result.
	 *
	 * return the {@link Result}
	 */
	@Transactional
	public Result edit() {
		return wrapForAdmin(() -> this.service.edit(formFactory.form(USER_CLASS).bindFromRequest().get()));
	}

	/**
	 * Delete user result.
	 *
	 * @param id the id
	 * return the {@link Result}
	 */
	@Transactional
	public Result delete(String id) {
		return wrapForAdmin(() -> this.service.delete(UUID.fromString(id)));
	}
}
