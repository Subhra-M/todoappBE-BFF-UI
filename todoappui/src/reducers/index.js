import { combineReducers } from 'redux';
import flightReducer from './todoReducer';

export default combineReducers({
    flight: flightReducer
});