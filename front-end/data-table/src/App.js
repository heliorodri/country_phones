import React, { Component } from 'react';
import Table from './table/Table';
import 'bootstrap/dist/css/bootstrap.min.css';
import Dropdown from './dropdown/Dropdown';
import ValidNumber from './valid-number/validNumber';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      customers: [],
      is_valid_number: '',
      selected_country: ''
    }
  }

  search() {

    var url = "http://localhost:8080/v1/customers";
    console.log("teste reload")
    console.log(this.state)

    if (this.state.selected_country){
        url = url + "?country=" + this.state.selected_country;
    }

    if (this.state.is_valid_number){
      if (this.state.selected_country){
        url = url + "&validNumber=" + this.state.item[1].value;
      } else {
        url = url + "?validNumber=" + this.state.is_valid_number;
      }
    }

    console.log(url)

    
    fetch(url)
    .then(res => res.json())
    .then(json => {
      this.setState({ 'customers': json })
    })
  }

  render() {
    return (
      <div className="table">
        <nav className="navbar navbar-light bg-light">
          <Dropdown 
            id="countries_dropdown"
            //onChange={(e) => {this.state.selected_country = e.target.value }}
            onChange={(e) => {this.setState({ 'is_valid_number': e.target.value })}}
          />
          
          <ValidNumber 
            id="valid_number_radio"
            onChange={(e) => {this.state.is_valid_number = e.target.value }}
          />

          <a className="navbar-brand" href="./">
          </a>
        </nav>
        <button onClick={() => this.search()}>Search</button>;
        <Table customers={ this.state.customers }/>
      </div>
    );
  }
}

export default App;