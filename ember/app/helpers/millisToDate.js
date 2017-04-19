import Ember from 'ember';

export function millisToDate(params) {
  return new Date(params[0]).toLocaleDateString();
}

export default Ember.Helper.helper(millisToDate);
