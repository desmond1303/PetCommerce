import Ember from 'ember';

export function millisToTime(params) {
  return new Date(params[0]).toLocaleTimeString();
}

export default Ember.Helper.helper(millisToTime);
