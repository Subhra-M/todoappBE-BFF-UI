import React from 'react';

import axios from 'axios';

export default class Test extends React.Component {
  state = {
    persons: []
  }

  componentDidMount() {
    axios.get(`http://localhost:8090/todoapp/api/v1/task`)
      .then(res => {
        const persons = res.data;
        this.setState({ persons });
      })
  }

  render() {
    return (
      <ul>
        { this.state.persons.map(person => <li>{person.task_description}</li>)}


        <table class="table table-hover" style={{border: '1px solid #ccc'}}>
                    <thead className="region_table_bg" style={{backgroundColor: 'gray'}}>
                        <tr>
                            <th>task_description</th>
                            <th>userId</th>
                        </tr>
                    </thead>
                    { this.state.persons.map(person => <tr>{person.task_description}</tr>)}
                </table>



      </ul>
    )
  }
}

