class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            cars: [
                {
                    "manufacturer": "Toyota",
                    "model": "Rav4",
                    "year": 2008,
                    "stock": 3,
                    "price": 8500
                },

                {
                    "manufacturer": "Toyota",
                    "model": "Camry",
                    "year": 2009,
                    "stock": 2,
                    "price": 6500
                },

                {
                    "manufacturer": "Toyota",
                    "model": "Tacoma",
                    "year": 2016,
                    "stock": 1,
                    "price": 22000
                },

                {
                    "manufacturer": "BMW",
                    "model": "i3",
                    "year": 2012,
                    "stock": 5,
                    "price": 12000
                },

                {
                    "manufacturer": "Chevy",
                    "model": "Malibu",
                    "year": 2015,
                    "stock": 2,
                    "price": 10000
                },

                {
                    "manufacturer": "Honda",
                    "model": "Accord",
                    "year": 2013,
                    "stock": 1,
                    "price": 9000
                },

                {
                    "manufacturer": "Hyundai",
                    "model": "Elantra",
                    "year": 2013,
                    "stock": 2,
                    "price": 7000
                },

                {
                    "manufacturer": "Chevy",
                    "model": "Cruze",
                    "year": 2012,
                    "stock": 2,
                    "price": 5500
                },

                {
                    "manufacturer": "Dodge",
                    "model": "Charger",
                    "year": 2013,
                    "stock": 2,
                    "price": 16000
                },

                {
                    "manufacturer": "Ford",
                    "model": "Mustang",
                    "year": 2009,
                    "stock": 1,
                    "price": 8000
                },

            ]
        };
    }
	
	createTableBody() {
		  return this.state.cars.map((car, rowCount) => {
		  const  { manufacturer, model, year, stock, price}  = car //uses destructing to go through the array 
           return ( //created the table row for each item in car 
              <tr key={rowCount}>
                 <td>{manufacturer}</td>
                 <td>{model}</td>
                 <td>{year}</td>
                 <td>{stock}</td>
                 <td>${price}.00</td>
                 <td>
                    <button onClick = {this.addOneToStock.bind(this, rowCount)}>Increment</button> 
                 </td>
              </tr>
           )
        })
	}
	
	addOneToStock(rowCount) { //passes in a value so the correct row is selected to increment the stock
		let position = this.state.cars.slice(); //this splits up the array into each row
		position[rowCount].stock = position[rowCount].stock + 1; //adds one to the stock amount
		this.setState({cars: position}); //updates the state so when it is displayed it is the right amount
	}

    render() {
        return (
            <div>
				<table id="Vehicle">
					<tr>
						<td>Manufacturer</td>
						<td>Model</td>
						<td>Year</td>
						<td>Stock</td>
						<td>Price</td>
						<td>Option</td>
					</tr>
					<tbody>
						{this.createTableBody()}
					</tbody>
				</table>
			</div>

        );
    };
}

ReactDOM.render(<App />, document.getElementById("app"));
