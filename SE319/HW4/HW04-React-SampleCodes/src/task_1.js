class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            ascending: false,
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
    
    incrementStock(index){
        let list = this.state.cars.slice();
        list[index].stock += 1;
        this.setState({cars: list});
    }

    sortByYear(){
        let list = this.state.cars.slice();
        if(!this.state.ascending){
            list.sort(function(first, second) {
                return second.year - first.year;
            });
        } else {
            list.sort(function(first, second) {
                return first.year - second.year;
            });
        }
        this.setState({cars: list});
        this.setState({ascending: !this.state.ascending})
    }

    renderTableHeader() {
        let header = Object.keys(this.state.cars[0])
        return header.map((key, index) => {
            if(key == "year"){
                return <th key={index} onClick={() => this.sortByYear()}>year</th>
            }
            return <th key={index}>{key}</th>
        })
    }

    renderCars() {
        return this.state.cars.map((car, index) => {
           const { manufacturer, model, year, stock, price } = car //destructuring
           return (
              <tr key={index}>
                 <td>{manufacturer}</td>
                 <td>{model}</td>
                 <td>{year}</td>
                 <td>{stock}</td>
                 <td>${price}.00</td>
                 <td>
                    <button onClick={this.incrementStock.bind(this, index)}>Increment</button>
                 </td>
              </tr>
           )
        })
    }

    render() {
        return (
            <div>
                <table id='cars'>
                    <tr>{this.renderTableHeader()}</tr>
                    <tbody>
                        {this.renderCars()}
                    </tbody>
                </table>
            </div>
        );
    }
}

ReactDOM.render(<App />, document.getElementById("app"));
