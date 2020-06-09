import axios from 'axios';
import { TODO_REQUEST, TODO_SUCCESS, TODO_FAILURE} from './types';

export const loadFlights = (user) => dispatch => {
    dispatch(getFlights());
    console.log("loading flight")
    axios({
        method: 'get',
        // url: 'http://mat-api-dev.mybluemix.net/admin/flight',
        url: 'http://localhost:8090/todoapp/api/v1/task'
      }).then(res => {
                if(res.data.status === 'SUCCESS'){
                    const flights = res.data
                dispatch(getFlightsSuccess(flights));
                }
                else{
                    const {errors} = res.data
                    dispatch(getFlightsFailure(errors));
                    errors.map(error =>
                        alert("Failed to authenticate the user", error.message)
                    )
                }
            })
            .catch(err => {
                dispatch(getFlightsFailure(err));
            });
}


export const getFlights = () => {
    return {
        type: TODO_REQUEST
    }
}


export const getFlightsSuccess = (response) => {
    return {
        type: TODO_SUCCESS,
        response

    }
}

export const getFlightsFailure = (errors) => {
    return {
        type: TODO_FAILURE,
        errors: errors
    }
}

