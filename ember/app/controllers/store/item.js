import Ember from 'ember';
import Base from '../base';
import $ from 'jquery';

const {
  computed,
  computed: {
    alias,
  },
  inject: {
    service,
  },
  String: {
    htmlSafe,
  },
} = Ember;

export default Base.extend({
  item: alias('model'),
  cartService: service('cart-service'),
  quantityToBuy: 1,

  photos: computed('item.photos', function () {
    return this.get('item.photos').sort((a, b) => {
      if (a.main === true && b.main === false) {
        return -1;
      }

      if (a.main === false && b.main === true) {
        return 1;
      }

      return 0;
    }).map(photo => htmlSafe('background-image: url(' + photo.path + ')'));
  }),

  photoCount: computed('photos', function () {
    return this.get('photos').length;
  }),

  currentPhotoNumber: computed('currentPhotoIndex', function () {
    return this.get('currentPhotoIndex') + 1;
  }),

  currentPhotoIndex: computed('photos', function () {
    if (this.get('photoCount') > 0) {
      return 0;
    } else {
      return -1;
    }
  }),

  currentPhoto: computed('currentPhotoIndex', 'photos', function () {
    return this.get('photos')[this.get('currentPhotoIndex')];
  }),

  hasNextPhoto: computed('currentPhotoIndex', 'photos', function () {
    return this.get('currentPhotoIndex') < this.get('photoCount') - 1;
  }),

  hasPreviousPhoto: computed('currentPhotoIndex', 'photos', function () {
    return this.get('photoCount') > 0 && this.get('currentPhotoIndex') > 0;
  }),

  actions: {

    nextPhoto() {
      if (this.get('hasNextPhoto')) {
        this.set('currentPhotoIndex', this.get('currentPhotoIndex') + 1);
      }
    },

    previousPhoto() {
      if (this.get('hasPreviousPhoto')) {
        this.set('currentPhotoIndex', this.get('currentPhotoIndex') - 1);
      }
    },

    addToCart() {
      let $button = $('#add-to-cart-button');
      let previousMessage = $button.html();
      $button.html($('#adding-to-cart').html());

      this.get('cartService').add(this.get('item'), this.get('quantityToBuy'))
        .then(() => {
          $button.html($('#added-to-cart').html());
          setTimeout(() => {
            $button.html(previousMessage);
          }, 2000);
        }, (response) => {
          $button.html(previousMessage);
          let error = response.errors[0];
          alert(error.status + ': ' + error.title);
        });
    },

  },

});
