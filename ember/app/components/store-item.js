import Ember from 'ember';

const {
  computed,
  String: {
    htmlSafe,
  },
} = Ember;

export default Ember.Component.extend({

  mainPhoto: computed(function () {
    let photo = this.get('item.photos').find(photo => photo.main === true);
    let path = '';
    if (photo) {
      path = photo.path;
    }

    return htmlSafe('background-image: url(' + path + ')');
  }),

});
