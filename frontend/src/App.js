import Header from './components/Header'
import './App.css'
import {Route, Switch, useLocation,} from 'react-router-dom'
import Homepage from './pages/Homepage'
import AddQuestion from './pages/Add-Question'
import LoginPage from './pages/LoginPage'
import useQuestions from './hooks/useQuestions'
import PrivateRoute from "./routing/PrivateRoute";
import Auth from "./pages/Auth";

function App() {

    const {questions, saveQuestion,} = useQuestions();


        const query = new URLSearchParams(useLocation().search);
        const code = query.get("code")

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
                    <Route exact path="/auth">
                        <Auth code={code}/>
                    </Route>

                </Switch>
            </div>
    )
}

export default App
