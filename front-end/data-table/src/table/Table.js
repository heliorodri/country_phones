import React from 'react';

const Table = ({ customers }) => {
  return (
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Country</th>
          <th>Valid Number</th>
          <th>Country Code</th>
          <th>Number</th>
        </tr>
      </thead>
      <tbody>
      { (customers.length > 0) ? customers.map( (customer, index) => {
           return (
            <tr key={ index }>
              <td>{ customer.name }</td>
              <td>{ customer.country }</td>
              <td>{ customer.validNumber }</td>
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