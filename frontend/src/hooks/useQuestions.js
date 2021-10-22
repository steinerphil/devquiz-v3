import {getQuestions, postQuestion, getValidate} from '../service/devQuizApiService'
import { useEffect, useState, useContext } from 'react'
import { AuthContext } from '../context/AuthProvider'


export default function useQuestions() {
  const [questions, setQuestions] = useState([])
  const { token } = useContext(AuthContext)

  useEffect(() => {
   getQuestions(token).then(questions => setQuestions(questions)).catch(err => console.error(err))
  }, [token])

  const saveQuestion = newQuestion => {
    postQuestion(newQuestion, token).then(data => console.log(data))
  }

  const validateAnswer = (questionId) => {
   return getValidate(questionId, token).then(response=> response.data)
  }

  return {
    saveQuestion,
    questions,
    validateAnswer,
    token
  }
}
