import Ember from 'ember';

const {
  inject: {
    service,
  },
} = Ember;

export default Ember.Route.extend({
  ajax: service('ajax'),

  beforeModel()  {
    this.controllerFor('application').set('isLoading', true);
  },

  afterModel() {
    this.controllerFor('application').set('isLoading', false);
  },

});
