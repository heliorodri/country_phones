import React, { useState } from 'react';
import Select from 'react-select'
import Table from './table/Table';

export default function App() {
    const options = [
        { value: 'CAMEROON', label: 'Cameroon' },
        { value: 'ETHIOPIA', label: 'Ethiopia' },
        { value: 'MOROCCO', label: 'Morocco' },
        { value: 'MOZAMBIQUE', label: 'Mozambique' },
        { value: 'UGANDA', label: 'Uganda' }
    ]

    const [customers, setCustomers] = useState([]);
    const [validNumber, setValidNumber] = useState('');
    const [country, setCountry] = useState('');

    function search() {
        console.log(validNumber);
        console.log(country);

        var url = "http://localhost:8080/v1/customers";
    
        if (country){
            url = url + "?country=" + country;
        }
    
        if (validNumber){
          if (country){
            url = url + "&validNumber=" + validNumber;
          } else {
            url = url + "?validNumber=" + validNumber;
          }
        }
    
        fetch(url)
        .then(res => res.json())
        .then(json => {
            console.log(json);
            setCustomers(json);
        })
      }

    return (
        <div className="table">
            <nav className="navbar navbar-light bg-light">
                <div>
                    <input type="radio" value="true" name="valid" onClick={(e) => setValidNumber(e.target.value)}/> Valid Number
                    <input type="radio" value="false" name="valid" onClick={(e) => setValidNumber(e.target.value)}/> Invalid Number
                </div>

                <Select options={options} onChange={(e) => setCountry(e.value)}/>
            </nav>
            <button onClick={() => search()}>Search</button>;
            <Table customers={ customers }/>
        </div>
    );
}