import { createContext, useState } from 'react'
import axios from 'axios'
import { useHistory } from 'react-router-dom'

export const AuthContext = createContext({})


export default function AuthProvider({ children }) {

    const [token, setToken] = useState()
    const history = useHistory()

    // const login = credentials => {
    //     axios
    //         .post('/auth/login', credentials)
    //         .then(res => res.data)
    //         .then(setToken)
    //         .then(() => history.push('/'))
    //         .catch(error => console.error(error.message))
    // }

    function login(credentials) {
        return axios
            .post("/auth/login", credentials)
            .then((r) => {

                setToken(r.data)
            })
            // .then(() => history.push("/"))
            .catch(err => console.error(err))
    }

    return (
        <AuthContext.Provider value={{ token, login }}>
            {children}
        </AuthContext.Provider>
    )
}