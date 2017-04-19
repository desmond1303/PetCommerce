import Base from '../base';

export default Base.extend({

  model() {
    return this.get('ajax').request('/api/v1/cart', {
      xhrFields: {
        withCredentials: true,
      },
    });
  },

});
