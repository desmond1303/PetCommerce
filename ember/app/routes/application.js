import Base from './base';

export default Base.extend({

  model() {

    return this.get('ajax').request('/api/v1/user/getCurrent', {
      xhrFields: {
        withCredentials: true,
      },
    });

  },

});
