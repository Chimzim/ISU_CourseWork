class Header extends React.Component {
    render() {
        return (
            <header>
                <h2>CS 319</h2>
            </header>
        );
    }
}

class Nav extends React.Component {
    render() {
        return (
            <nav>
                <ul>
                    <li>Introduction</li>
                    <li>Basics</li>
                    <li>JavaScript</li>
                </ul>
            </nav>
        );
    }
}

class Article extends React.Component {
    render() {
        return (
            <article>
                <h1>Intro to React</h1>
                <p>This is an intro to React</p>
            </article>
        );
    }
}

class Acomponent extends React.Component {
    render() {
        const divStyle = {
            color: 'green',
            textAlign: 'center',
        };
        return (<div>
            <p style={divStyle}> A simple react component with inline style</p>
        </div>);
    }
}

class Footer extends React.Component {
    render() {
        return (
            <footer>
                <p>Class of 2020</p>
            </footer>
        );
    }
}


// Start Coding here


