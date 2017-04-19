import Base from './base';

export default Base.extend({

  add(item, quantity) {
    return this.get('ajax').post('/api/v1/cart/add', {
      xhrFields: {
        withCredentials: true,
      },
      data: {
        id: item.id,
        quantity: quantity,
      },
    });
  },

  createOrder() {
    return this.get('ajax').post('/api/v1/order/fromCart', {
      xhrFields: {
        withCredentials: true,
      },
    });
  },

});
