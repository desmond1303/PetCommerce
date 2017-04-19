import Base from './base';

export default Base.extend({
  actions: {

    logout() {
      this.get('ajax').post('/api/v1/logout', {
        xhrFields: {
          withCredentials: true,
        },
      })
      .then(() => {
        this.set('application.user', null);
        this.transitionToRoute('index');
      });
    },

  },
});
