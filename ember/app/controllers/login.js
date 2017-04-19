import Base from './base';

export default Base.extend({
  form: 'login',

  _sendRequest(url, data) {
    this.get('ajax').post(url, {
      xhrFields: {
        withCredentials: true,
      },
      data: data,
    })
    .then(
      (user) => {
        this.set('application.user', { isLoggedIn: true, object: user });
        this.transitionToRoute('user');
      }, (error) => {
        this.set('hasError', true);
        this.set('errorMessage', error.errors[0].title);
      }
    );
  },

  actions: {
    setForm(form) {
      this.set('form', form);
    },

    authenticate() {
      this._sendRequest('/api/v1/login', {
        email: this.get('email'),
        password: this.get('password'),
      });
    },

    register() {
      this._sendRequest('/api/v1/register', {
        name: this.get('name'),
        email: this.get('email'),
        password: this.get('password'),
      });
    },
  },
});
