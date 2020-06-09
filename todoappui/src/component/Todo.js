import React, { Component } from 'react';
import {Header, HeaderName} from "carbon-components-react";
import '../scss/todo.scss';
import axios from 'axios';
import ReactTable from "react-table"
import "react-table/react-table.css";
// import TodoData from '../component/api/todoData.json'

export default class Todo extends Component {
  state = {
    todoItem: '',
     regions: [],
  }
  updateTodoName = (e) => {
    this.setState({
      todoItem: e.target.value
    })
  }
  createTodo(){
    if(this.state.todoItem.length > 0 ){
      this.createTodoTable(this.state.todoItem)
      this.setState({todoItem: ''})
    }
  }
  createTodoTable(newRegionName) {
    this.setState({
      regions: [...this.state.regions, {
        regions: newRegionName
      }]
    })
  }
  deleteRegion = (props) => {
    var array = this.state.regions
    var index = props.index
    if (index > -1) {
      array.splice(index, 1);
    }
    this.setState({
      regions: array
    })
  }

  render() {
    return (
      <div>
        <Header aria-label="IBM Platform Name">
          <HeaderName
            href="#"
            prefix="MSS ToDo APP"
          >
            [Todo App]
          </HeaderName>
        </Header>
        <div className="mtop"></div>
        <div className="boxfram">
        <input
          type="text"
          className="inputbox"
          name=""
          value={this.state.todoItem}
          placeholder=" "
          onChange={(e) => this.updateTodoName(e)}
        />
        <button className="button" onClick={()=>this.createTodo()}>ADD</button>
        <br /><br />

        <ReactTable
        data={this.state.regions}
        defaultPageSize={5}
        columns={[
          {
            Header: 'Description',
            accessor: 'regions'
          },
          {
            Header: "Edit",
            accessor: "edit",
            sortable: false,
            filterable: false,
            Cell: props => (
              <div style={{ textAlign: 'center' }}>
                EDIT
              </div>
            )
          },
          {
            Header: "Delete",
            accessor: "delete",
            sortable: false,
            filterable: false,
            Cell: props => (
              <div style={{ textAlign: 'center' }} onClick={() => this.deleteRegion(props)}>
                DELETE
              </div>
            )
          }
        ]}       
        />
        </div>         
      </div>
    )
  }
}

