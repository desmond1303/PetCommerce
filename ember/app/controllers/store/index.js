import Ember from 'ember';
import Base from '../base';
import $ from 'jquery';

const {
  computed,
  computed: {
    alias,
  },
  run: {
    debounce,
  },
} = Ember;

export default Base.extend({
  queryParams: ['page', 'sortKey', 'sortOrder', 'name'],
  page: 1,
  sortKey: alias('sortBy.key'),
  sortOrder: alias('sortBy.order'),
  name: null,

  searchName: null,

  sortIcons: [
    { id: 0, name: 'Name Ascending', icon: 'sort-alpha-asc', key: 'name', order: 'asc' },
    { id: 1, name: 'Name Descending', icon: 'sort-alpha-desc', key: 'name', order: 'desc' },
    { id: 2, name: 'Price Ascending', icon: 'sort-numeric-asc', key: 'price', order: 'asc' },
    { id: 3, name: 'Price Descending', icon: 'sort-numeric-desc', key: 'price', order: 'desc' },
  ],

  sortBy: computed(function () {
    return this.get('sortIcons')[0];
  }),

  _findSortOption(id) {
    return this.get('sortIcons').find(icon => icon.id === id);
  },

  _setPopupVisibility(visible) {
    $('.sort-button').toggleClass('active', visible);
    $('.sort-popup').toggleClass('active', visible);
  },

  onSearchNameChanged: function () {
    if (this.get('searchName') === '') {
      this.set('searchName', null);
    }

    debounce(this, this._refreshFilter, 1000, this.get('searchName') === null);
  }.observes('searchName'),

  _refreshFilter() {
    this.transitionToRoute('store', { queryParams: {
      sortKey: this.get('sortKey'),
      sortOrder: this.get('sortOrder'),
      name: this.get('searchName'),
    }, });
  },

  actions: {
    toggleSort() {
      this._setPopupVisibility();
    },

    setSort(id) {
      this.set('sortBy', this._findSortOption(id));
      this._setPopupVisibility(false);
    },

    focusSearchBox() {
      $('#search-box').focus();
    },

    clearSearch() {
      this.set('searchName', null);
    },
  },

});
