import React, { Component } from "react";

class ValidNumber extends Component {
  render() {
    return (
      <div onChange={this.onChangeValue}>
        <input type="radio" value="true" name="valid" /> Valid Number
        <input type="radio" value="false" name="valid" /> Invalid Number
      </div>
    );
  }
}

export default ValidNumber;