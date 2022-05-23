"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var App = function (_React$Component) {
    _inherits(App, _React$Component);

    function App(props) {
        _classCallCheck(this, App);

        var _this = _possibleConstructorReturn(this, (App.__proto__ || Object.getPrototypeOf(App)).call(this, props));

        _this.state = {
            ascending: false,
            cars: [{
                "manufacturer": "Toyota",
                "model": "Rav4",
                "year": 2008,
                "stock": 3,
                "price": 8500
            }, {
                "manufacturer": "Toyota",
                "model": "Camry",
                "year": 2009,
                "stock": 2,
                "price": 6500
            }, {
                "manufacturer": "Toyota",
                "model": "Tacoma",
                "year": 2016,
                "stock": 1,
                "price": 22000
            }, {
                "manufacturer": "BMW",
                "model": "i3",
                "year": 2012,
                "stock": 5,
                "price": 12000
            }, {
                "manufacturer": "Chevy",
                "model": "Malibu",
                "year": 2015,
                "stock": 2,
                "price": 10000
            }, {
                "manufacturer": "Honda",
                "model": "Accord",
                "year": 2013,
                "stock": 1,
                "price": 9000
            }, {
                "manufacturer": "Hyundai",
                "model": "Elantra",
                "year": 2013,
                "stock": 2,
                "price": 7000
            }, {
                "manufacturer": "Chevy",
                "model": "Cruze",
                "year": 2012,
                "stock": 2,
                "price": 5500
            }, {
                "manufacturer": "Dodge",
                "model": "Charger",
                "year": 2013,
                "stock": 2,
                "price": 16000
            }, {
                "manufacturer": "Ford",
                "model": "Mustang",
                "year": 2009,
                "stock": 1,
                "price": 8000
            }]
        };
        return _this;
    }

    _createClass(App, [{
        key: "incrementStock",
        value: function incrementStock(index) {
            var list = this.state.cars.slice();
            list[index].stock += 1;
            this.setState({ cars: list });
        }
    }, {
        key: "sortByYear",
        value: function sortByYear() {
            var list = this.state.cars.slice();
            if (!this.state.ascending) {
                list.sort(function (first, second) {
                    return second.year - first.year;
                });
            } else {
                list.sort(function (first, second) {
                    return first.year - second.year;
                });
            }
            this.setState({ cars: list });
            this.setState({ ascending: !this.state.ascending });
        }
    }, {
        key: "renderTableHeader",
        value: function renderTableHeader() {
            var _this2 = this;

            var header = Object.keys(this.state.cars[0]);
            return header.map(function (key, index) {
                if (key == "year") {
                    return React.createElement(
                        "th",
                        { key: index, onClick: function onClick() {
                                return _this2.sortByYear();
                            } },
                        "year"
                    );
                }
                return React.createElement(
                    "th",
                    { key: index },
                    key
                );
            });
        }
    }, {
        key: "renderCars",
        value: function renderCars() {
            var _this3 = this;

            return this.state.cars.map(function (car, index) {
                var manufacturer = car.manufacturer,
                    model = car.model,
                    year = car.year,
                    stock = car.stock,
                    price = car.price; //destructuring

                return React.createElement(
                    "tr",
                    { key: index },
                    React.createElement(
                        "td",
                        null,
                        manufacturer
                    ),
                    React.createElement(
                        "td",
                        null,
                        model
                    ),
                    React.createElement(
                        "td",
                        null,
                        year
                    ),
                    React.createElement(
                        "td",
                        null,
                        stock
                    ),
                    React.createElement(
                        "td",
                        null,
                        "$",
                        price,
                        ".00"
                    ),
                    React.createElement(
                        "td",
                        null,
                        React.createElement(
                            "button",
                            { onClick: _this3.incrementStock.bind(_this3, index) },
                            "Increment"
                        )
                    )
                );
            });
        }
    }, {
        key: "render",
        value: function render() {
            return React.createElement(
                "div",
                null,
                React.createElement(
                    "table",
                    { id: "cars" },
                    React.createElement(
                        "tr",
                        null,
                        this.renderTableHeader()
                    ),
                    React.createElement(
                        "tbody",
                        null,
                        this.renderCars()
                    )
                )
            );
        }
    }]);

    return App;
}(React.Component);

ReactDOM.render(React.createElement(App, null), document.getElementById("app"));
