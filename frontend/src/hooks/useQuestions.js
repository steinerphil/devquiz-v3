import {getQuestions, postQuestion, getValidate} from '../service/devQuizApiService'
import { useEffect, useState } from 'react'


export default function useQuestions(token) {
  const [questions, setQuestions] = useState([])
  const [correctAnswer, setCorrectAnswer] = useState("")

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
  }
}
