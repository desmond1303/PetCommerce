import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL,
});

Router.map(function () {
  this.route('store', function () {
    this.route('item', { path: 'item/:id' });
  });

  this.route('user', function () {
    this.route('orders', function () {
      this.route('order', { path: '/:id' });
    });
    this.route('cart');
  });
  this.route('login');
});

export default Router;
