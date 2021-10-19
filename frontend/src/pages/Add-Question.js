import * as React from 'react'
import styled from 'styled-components'
import { useState } from 'react'
import NewQuestion from '../components/NewQuestion'
import { v4 as uuidv4 } from 'uuid';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


export default function AddQuestion({ saveQuestion }) {
  const [question, setQuestions] = useState({
    questionText: '',
    answers: [
      {
        answerText: '',
        isCorrect: true,
        id: uuidv4()
      },
      {
        answerText: '',
        isCorrect: false,
        id: uuidv4()
      },
    ],
  })

  const handleAnswerTextInput = (e, index) => {
    const newQuestionObject = { ...question }
    newQuestionObject.answers[index].answerText = e.target.value
    setQuestions(newQuestionObject)
  }

  const handleAnswerCorrectStatusChange = (index) => {
    const newQuestionObject = { ...question }

    for (let i = 0; i < newQuestionObject.answers.length; i++) {
      newQuestionObject.answers[i].isCorrect = i === index
    }

    setQuestions(newQuestionObject)
  }

  const createNewAnswerOption = () => {
    const newQuestionObject = { ...question }
    newQuestionObject.answers.push({
      answerText: '',
      isCorrect: false,
      id: uuidv4()
    })
    setQuestions(newQuestionObject)
  }

  const handleQuestionTextInput = e => {
    setQuestions({ ...question, questionText: e.target.value })
  }

  const notifySuccess = () => toast.success("Successfully added.")

  return (
    <section>
      <Heading>Add Question Page</Heading>
      <ToastContainer
          autoClose={2000}
          hideProgressBar
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
      />
      <FormContainer>
        <NewQuestion
          question={question}
          answers={question.answers}
          handleAnswerTextInput={handleAnswerTextInput}
          handleAnswerCorrectStatusChange={handleAnswerCorrectStatusChange}
          handleQuestionTextInput={handleQuestionTextInput}
        />
      </FormContainer>
      <ButtonContainer>
        <Button onClick={createNewAnswerOption}>Create Answeroption</Button>
        <Button
          onClick={() => {
            saveQuestion(question)
            notifySuccess()
          }}
        >
          Save Question
        </Button>
      </ButtonContainer>
    </section>
  )
}

const Heading = styled.h2`
  font-family: 'Montserrat', sans-serif;
  text-align: center;
`
const FormContainer = styled.section`
  display: flex;
  justify-content: center;
  font-family: 'Montserrat', sans-serif;
`
const ButtonContainer = styled.section`
  display: flex;
  justify-content: center;
  gap: 10px;
  margin: 5px;
  align-content: center;
`
const Button = styled.button`
  box-shadow: inset 0 1px 0 0 #ffffff;
  background-color: #757780;
  border-radius: 6px;
  border: 1px solid #dcdcdc;
  display: inline-block;
  cursor: pointer;
  color: white;
  font-family: 'Montserrat', sans-serif;
  font-size: 15px;
  font-weight: bold;
  padding: 6px 24px;
  text-decoration: none;

  &:hover {
    background: linear-gradient(to bottom, #dfdfdf 5%, #ededed 100%);
    background-color: #dfdfdf;
    color: #757780;
  }
  &:active {
    position: relative;
    top: 1px;
  }
`
