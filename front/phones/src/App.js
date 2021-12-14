import React from "react";
import './App.css';
class App extends React.Component {

	// Constructor
	constructor(props) {
		super(props);

		this.state = {
			customers: [],
			DataisLoaded: false
		};
	}

	// ComponentDidMount is used to
	// execute the code
	componentDidMount() {
    fetch("http://localhost:8080/v1/customers")
    .then((res) => res.json())
    .then((json) => {
      this.setState({
        customers: json,
        DataisLoaded: true
      });
    })
  }

	render() {
		const { DataisLoaded, customers } = this.state;
		if (!DataisLoaded) return <div>
			<h1> Pleses wait some time.... </h1> </div> ;

		return (
		<div className = "App">
			<h1> Fetch data from an api in react </h1> {
				customers.map((customer) => (
        <ol key = { customer.id } >
          Name: { customer.name },
          Country: { customer.country },
          Number: { customer.number },
          Valid Number: { customer.validNumber.toString() },
          Country Code: { customer.countryCode },
          </ol>
				))
			}
		</div>
	);
}
}

export default App;
