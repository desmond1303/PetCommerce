import Ember from 'ember';
import Base from '../base';

const {
  inject: {
    service,
  },
} = Ember;

export default Base.extend({
  cartService: service('cart-service'),

  actions: {

    order() {
      this.get('cartService').createOrder()
        .then(() => {
          this.transitionToRoute('user.orders');
        });
    },

  },

});
