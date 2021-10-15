import * as React from 'react'
import Answer from './Answer'
import styled from 'styled-components'
import {useState} from "react";

function Question({ question, setCount }) {

    const [chosenAnswer, setChosenAnswer] = useState({});
    const [disabled, setDisabled] = useState(false)

    function validateAnswer() {
        console.log(chosenAnswer.isCorrect)
        if (chosenAnswer.isCorrect) {
            alert("CORRECT!")
            setCount();
            setDisabled(true)
        } else {
            const correctAnswer = question.answers.find(answer => answer.isCorrect ? answer : "");
            alert("WROONG! Correct answer would have been: " + correctAnswer.answerText)

             }
    }

    return (
    <QuestionContainer>
      <h3>{question.questionText}</h3>
      <AnswerContainer>
        {question.answers.map(answer => (
          <Answer setChosenAnswer={setChosenAnswer} answer={answer} key={answer.id} questionId={question.id} />
        ))}
      </AnswerContainer>
      <CheckButton onClick={validateAnswer} disabled={disabled}>Check Answer</CheckButton>
    </QuestionContainer>
  )
}
export default Question

const QuestionContainer = styled.section`
  width: 400px;
  border: 1px solid #009fb7;
  border-radius: 20px;
  padding: 20px;
  background-color: #EAF6FF;
  font-family: 'Montserrat', sans-serif;;
`

const AnswerContainer = styled.section`
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  gap: 0px 0px;
  grid-template-areas:
    '. .'
    '. .';
`
const CheckButton = styled.button`
  box-shadow: inset 0px 1px 0px 0px #ffffff;
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

  &:hover:enabled {
    background: linear-gradient(to bottom, #dfdfdf 5%, #ededed 100%);
    background-color: #dfdfdf;
    color: #757780;
  }
  &:active {
    position: relative;
    top: 1px;
  }

  &:disabled{
    background-color: #cccccc;
    color: #666666;
  }

`
