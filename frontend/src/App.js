import Header from './components/Header'
import './App.css'
import {Route, Switch,} from 'react-router-dom'
import Homepage from './pages/Homepage'
import AddQuestion from './pages/Add-Question'
import LoginPage from './pages/LoginPage'
import useQuestions from './hooks/useQuestions'
import AuthProvider from './context/AuthProvider'
import PrivateRoute from "./routing/PrivateRoute";

function App() {

    const {questions, saveQuestion,} = useQuestions();

    return (

            <div className="App">
                <Header/>
                <Switch>
                    <Route exact path="/login">
                        <LoginPage/>
                    </Route>
                    <PrivateRoute exact path="/">
                        <Homepage questions={questions}/>
                    </PrivateRoute>
                    <PrivateRoute exact path="/add-question">
                        <AddQuestion saveQuestion={saveQuestion}/>
                    </PrivateRoute>

                </Switch>
            </div>
    )
}

export default App
