import Base from '../base';

export default Base.extend({
  queryParams: {
    page: {
      refreshModel: true,
    },
    sortKey: {
      refreshModel: true,
    },
    sortOrder: {
      refreshModel: true,
    },
    name: {
      refreshModel: true,
    },
  },

  model(params) {
    return this.get('ajax').request('/api/v1/item/filter', {
      data: params,
    });
  },

});
