import { TODO_REQUEST, TODO_SUCCESS, TODO_FAILURE} from '../action/types';

const initialState = {
    flights : [],
    searchPlan : [],
    isFetching : false,
    errors : null,
    data: []
}

export default function(state = initialState, action ) {
    switch(action.type) {
        case TODO_REQUEST:
            return {
                ...state,
                isFetching: true,
            }
            case TODO_SUCCESS:
            return {
                ...state,
                isFetching: false,
                flights: action.response,
            }
            case TODO_FAILURE:
            return {
                ...state,
                isFetching: false,
                errors : action.errors,
            }
        default: 
            return state;
    }
}
