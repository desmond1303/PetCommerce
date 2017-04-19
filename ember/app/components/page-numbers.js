import Ember from 'ember';

const {
  computed,
} = Ember;

export default Ember.Component.extend({
  canGoBack: computed('current', function () {
    return this.get('current') > 1;
  }),

  canGoFoward: computed('current', 'max', function () {
    return this.get('current') < this.get('max');
  }),

  previousPage: computed('current', function () {
    return this.get('current') - 1;
  }),

  nextPage: computed('current', function () {
    return this.get('current') + 1;
  }),

  previousPages: computed('current', function () {
    let current = this.get('current');
    let secondToFirst = current - 4 === 2 ? { pageNumber: 2 } : { isDots: true };
    switch (current) {
      case 2:
        return [
          { pageNumber: 1 },
        ];
      case 3:
        return [
          { pageNumber: 1 },
          { pageNumber: 2 },
        ];
      case 4:
        return [
          { pageNumber: 1 },
          { pageNumber: 2 },
          { pageNumber: 3 },
        ];
      case 5:
        return [
          { pageNumber: 1 },
          { pageNumber: 2 },
          { pageNumber: 3 },
          { pageNumber: 4 },
        ];
      default:
        return [
          { pageNumber: 1 },
          secondToFirst,
          { pageNumber: current - 3 },
          { pageNumber: current - 2 },
          { pageNumber: current - 1 },
        ];
    }
  }),

  nextPages: computed('current', 'max', function () {
    let current = this.get('current');
    let lastPage = this.get('max');
    let secondToLast = current + 4 === lastPage - 1 ? { pageNumber: current + 4 } : { isDots: true };
    switch (lastPage - current) {
      case 1:
        return [
          { pageNumber: lastPage },
        ];
      case 2:
        return [
          { pageNumber: lastPage - 1 },
          { pageNumber: lastPage },
        ];
      case 3:
        return [
          { pageNumber: lastPage - 2 },
          { pageNumber: lastPage - 1 },
          { pageNumber: lastPage },
        ];
      case 4:
        return [
          { pageNumber: lastPage - 3 },
          { pageNumber: lastPage - 2 },
          { pageNumber: lastPage - 1 },
          { pageNumber: lastPage },
        ];
      default:
        return [
          { pageNumber: current + 1 },
          { pageNumber: current + 2 },
          { pageNumber: current + 3 },
          secondToLast,
          { pageNumber: lastPage },
        ];
    }
  }),
});
