import {createContext, useState} from 'react'
import {useHistory} from 'react-router-dom'

import {postGithubLogin, postLogin} from "../service/devQuizApiService";


export const AuthContext = createContext({})

export default function AuthProvider({children}) {

    const [token, setToken] = useState()
    const history = useHistory()

    const login = (credentials) => {
        postLogin(credentials)
            .then(response => response.data)
            .then(token => {
                setToken(token)
            })
            .then(() => history.push("/"))
            .catch(err => console.log(err))
    }

    const loginWithGithub = (code) =>{
        postGithubLogin(code)
            .then(response => response.data)
            .then(token => {
                console.log(token)
                setToken(token)})
            .then(() => history.push("/"))
            .catch(err => console.log(err))
    }

    return (
        <AuthContext.Provider value={{token, login, loginWithGithub}}>
            {children}
        </AuthContext.Provider>
    )
}