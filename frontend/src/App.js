import Header from './components/Header'
import './App.css'
import {Route, Switch, useHistory} from 'react-router-dom'
import Homepage from './pages/Homepage'
import AddQuestion from './pages/Add-Question'
import LoginPage from './pages/LoginPage'
import useQuestions from './hooks/useQuestions'
import {useState} from "react";
import axios from "axios";

function App() {


    const [token, setToken] = useState()

    // const history = useHistory();

    // const login = (credentials) => {
    //     axios.post("/auth/login", credentials)
    //         .then(res => res.data)
    //         .then(setToken)
    //         .then(() => history.push("/"))
    //         .catch(error => console.error(error.message))
    // }

    function authenticate(credentials) {
        return axios
            .post("/auth/login", credentials)
            .then((r) => {

                setToken(r.data)
            })
            // .then(() => history.push("/"))
            .catch(err => console.error(err))
    }

    const { questions, saveQuestion, } = useQuestions(token);

  return (
    <div className="App">
      <Header />
      <Switch>
        <Route exact path="/">
          <Homepage questions={questions} token={token}/>
        </Route>
        <Route exact path="/add-question">
          <AddQuestion saveQuestion={saveQuestion}/>
        </Route>
          <Route exact path="/login">
              <LoginPage login={authenticate}/>
          </Route>
      </Switch>
    </div>
  )
}

export default App
