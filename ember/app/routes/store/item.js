import Base from '../base';

export default Base.extend({

  model(params) {
    return this.get('ajax').request('/api/v1/item/find/' + params.id);
  },

});
