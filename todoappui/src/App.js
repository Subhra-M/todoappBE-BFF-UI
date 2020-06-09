import React, { Component } from 'react'
import Todo from './component/Todo'
import TodoApp from './component/todoApp'
//import Test from './component/test'

import store from './store';
import { Provider } from 'react-redux';
function App(){
  return (
    <div>
        <Provider store={store}>
        <div>
        {/* <Todo /> */}
        <TodoApp />
        {/* <Test /> */}
        </div>
        </Provider>
    </div>  
  )
}
export default App;