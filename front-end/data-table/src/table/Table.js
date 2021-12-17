import React from 'react';

const Table = ({ customers }) => {
  return (
    <table>
      <thead>
        <tr>
          <th style={{textAlign:"left"}}>Name</th>
          <th>Country</th>
          <th>Valid</th>
          <th>Country Code</th>
          <th>Number</th>
        </tr>
      </thead>
      <tbody>
      { (customers.length > 0) ? customers.map( (customer, index) => {
           return (
            <tr key={ index } style={{textAlign:"center"}}>
              <td style={{textAlign:"left"}}>{ customer.name }</td>
              <td>{ customer.country }</td>
              <td>{ customer.validNumber.toString() }</td>
              <td>{ customer.countryCode }</td>
              <td>{ customer.number }</td>
            </tr>
          )
         }) : <tr><td colSpan="5">Loading...</td></tr> }
      </tbody>
    </table>
  );
}

export default Table