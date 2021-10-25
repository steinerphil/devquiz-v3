import {getQuestions, postQuestion, getValidate} from '../service/devQuizApiService'
import { useEffect, useState, useContext } from 'react'
import { AuthContext } from '../context/AuthProvider'


export default function useQuestions() {
  const [questions, setQuestions] = useState([])
  const { token } = useContext(AuthContext)

  const getAllQuestions = () => {
    getQuestions(token).then(questions => setQuestions(questions)).catch(err => console.error(err))
  }

  useEffect(() => {
   getAllQuestions()
  }, [token])

  const saveQuestion = newQuestion => {
    postQuestion(newQuestion, token).then(getAllQuestions)
  }

  const validateAnswer = (questionId) => {
   return getValidate(questionId, token).then(response=> response.data)
  }

  return {
    getAllQuestions,
    saveQuestion,
    questions,
    validateAnswer,
  }
}
