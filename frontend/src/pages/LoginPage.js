import {useState, useContext} from "react";
import { AuthContext } from '../context/AuthProvider'
import GithubButton from 'react-github-login-button'

const initialState = {
    username: '',
    password: '',
}

export default function LoginPage() {
    const [credentials, setCredentials] = useState(initialState);
    const { login } = useContext(AuthContext)

    const handleChange = event => {
        setCredentials({...credentials, [event.target.name]: event.target.value})
    }

    const handleSubmit = event => {
        event.preventDefault()
        login(credentials)
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
            <div className="container">
                <label htmlFor="username"><b>{' '}Username</b></label>
                <input type="text" placeholder="Enter Username" value={credentials.username} name="username" onChange={handleChange} required/>

                <label htmlFor="password"><b>{' '}Password</b></label>
                <input type="password" placeholder="Enter Password" value={credentials.password} name="password" onChange={handleChange} required/>

                <button type="submit">Login</button>
            </div>
                <GithubButton
                    onClick={ () => window.open("https://github.com/login/oauth/authorize?client_id=be41cda045d6d9ff7970")}
                />
            </form>
        </>
    )

}