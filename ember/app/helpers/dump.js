import Ember from 'ember';

export function dump(params, hash) {
  return Object.keys(hash || {}).map(function (key) {
    return key + '=' + JSON.stringify(hash[key]);
  }).join(' ');
}

export default Ember.Helper.helper(dump);
