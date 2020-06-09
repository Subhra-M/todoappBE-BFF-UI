import React, { Component } from 'react';
import { Header, HeaderName } from "carbon-components-react";
import { FaEdit, FaTrash } from 'react-icons/fa';
import axios from 'axios';

class TodoApp extends Component {
  state = {
    tasks: [],
    task_description: '',
    user_name: '',
    todoItem: '',
    errorInLoading: null,
    isDiscountDeleted: null,
    errorInDeleting: null,

    taskdescription: '',
    edit_taskdescription:'',
    edit_id:'',
    toggle_edit_inputbox: false
  }
  
  componentDidMount() {
    this.getTodoList();
  }


  getTodoList(){
    console.log("api_url: " + process.env.REACT_APP_API_URL);
    //axios.get(`http://localhost:8090/todoapp/api/v1/task`) process.env.REACT_APP_SPECIAL_FEATURE
    axios.get(process.env.REACT_APP_API_URL)
    .then(res => {
      const tasks = res.data;
      this.setState({ tasks });
    })
  }


  updateTodoName = (e) => {
    this.setState({
      todoItem: e.target.value
    })
    //console.log(this.state.todoItem);
  }

  showTodoName = (task) => {
    this.state.toggle_edit_inputbox = true;
    this.setState({
      todoItem: task.task_description,
      edit_id: task.id
    });
    //console.log("id_editTodoName():" + this.state.edit_id);
    //console.log("person_editTodoName():" + person.id);
    //console.log("taskdescription'_editTodoName():" + this.state.edit_taskdescription);
  }

  editTodoName = (e) => {
    this.setState({
      edit_taskdescription: e.target.value,
    });
  }

  //updat the task
  todoUpdateTask = () => {
    console.log("id_todoUpdateTask():" + this.state.todoItem);
    axios({
      method: 'put',
      url: process.env.REACT_APP_API_URL,
      data: {
        "id": this.state.edit_id,
        "task_description": this.state.todoItem,
        "user_name": "khalda31"
      }
    }).then(res => {
      console.log("resp", res)
      if (res.status === 200) {
        this.setState({
          task_description: '',
          user_name: '',
          toggle_edit_inputbox: false
        })
      }
      else {
        const { errors } = res.data
        this.setState({
          errorInLoading: errors
        })
      }
      this.getTodoList();
      this.state.todoItem = '';
      this.state.edit_id = '';
    })
      .catch(err => {
        this.setState({
          errorInLoading: err
        })
      });
  }


  // Delete
  todoDelete = (data) => {
    const confirmDeletion = window.confirm("Are you Sure you want to Delete?");
    console.log("todoDelete(): " + data);
    if (confirmDeletion) {
      this.setState({
        isDiscountDeleted: null,
        errorInDeleting: null
      })
      const id = data.id
      axios({
        method: 'DELETE',
        url: process.env.REACT_APP_API_URL + id,
        data: {
          data: {
            id: id
          }
        }
      }).then(res => {
        if (res.status === 200) {
          this.getTodoList();
          console.log("in if");
          console.log(res)
          this.setState({
            isDiscountDeleted: true,
          })
        }
        else {
          const { errors } = res.data.errors
          console.log("in else");
          alert("todo item cannot be deleted at the moment, please try again!")
          this.setState({
            errorInDeleting: errors
          })
        }
      })
        .catch(err => {
          console.log("in catch");
          console.log(JSON.stringify(err));
          alert("todo item cannot be deleted at the moment, please try again!")
          this.setState({
            errorInDeleting: err
          })
        });

    }
  }

  addTodo() {
    console.log("task_description", this.state.taskdescription);
    axios({
      method: 'post',
      url: process.env.REACT_APP_API_URL,
      data: {
        "task_description": this.state.todoItem,
        "user_name": "khalda31"
      }
    }).then(res => {
      console.log("resp", res)
      if (res.status === 200) {
        this.setState({
          task_description: '',
          user_name: ''
        })
      }
      else {
        const { errors } = res.data
        this.setState({
          errorInLoading: errors
        })
      }
      this.getTodoList();
      this.state.todoItem='';
    })
      .catch(err => {
        this.setState({
          errorInLoading: err
        })
      });

  }


  render() {
    const is_edit = this.state.toggle_edit_inputbox;
    let button;
    if(!is_edit){
      button = <button className="button" toggle_edit_inputbox={false}
      onClick={() => this.addTodo()}
    >ADD</button>
    }
    else {
      button =   <button className="button" toggle_edit_inputbox={true}
      onClick={() => this.todoUpdateTask()}
    >UPDATE</button>
    }
  
    return (
      <div>
        <Header aria-label="MSS ToDo">
          <HeaderName
            href="#"
            prefix=""
          >
            ToDo App
                </HeaderName>
        </Header>
        <div className="mtop"></div>
        <div className="boxfram">
        {/* Add new Task / Update Task*/}
          <input
            type="text"
            className="inputbox"
            name=""
            value={this.state.todoItem}
            placeholder=" "
            onChange={(e) => this.updateTodoName(e)}
          />
          {button} 
          <p style={{margin: '30px'}}></p>
          <table class="table table-hover" style={{ border: '1px solid #ccc'}}>
            <thead className="region_table_bg" style={{ backgroundColor: 'lightgrey' }}>
              <tr>
                <th>Task Description</th>
                <th>Edit</th>
                <th>Delete</th>
              </tr>
            </thead>

            <tbody>
              {this.state.tasks.map(task => {
                return (<tr>
                  <td>{task.task_description}</td>
                  <td><FaEdit onClick={() => this.showTodoName(task)}/></td>
                  <td><FaTrash onClick={() => this.todoDelete(task)}/></td>
                </tr>)
              })}
            </tbody>

          </table>
        </div>
      </div>
    )
  }
}


export default (TodoApp)
