import Ember from 'ember';

const {
  computed: {
    alias,
  },
  inject: {
    controller,
    service,
  },
} = Ember;

export default Ember.Controller.extend({
  application: controller('application'),

  isLoggedIn: alias('application.user.isLoggedIn'),
  user: alias('application.user.object'),
  ajax: service('ajax'),

  setError(hasError, message) {
    this.set('hasError', hasError);
    this.set('errorMessage', hasError ? message : null);
  },
});
