import * as React from 'react'
import Answer from './Answer'
import styled from 'styled-components'
import {useState} from "react";
import useQuestions from '../hooks/useQuestions'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Question({ question, setCount }) {



    const notifyCorrect = () => toast.success('Correct!', {
        position: "top-center",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
    });

    const notifyWrong = (data) => toast.error('Wrong, correct answer would have been: ' + data, {
        position: "top-center",
        autoClose: 4300,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
    });

    const [chosenAnswer, setChosenAnswer] = useState({});
    const [disabled, setDisabled] = useState(false)
    const [backgroundColor, setBackgroundColor] = useState("white")
    const { validateAnswer, } = useQuestions();

    function validateUserAnswer() {
        validateAnswer(question.id).then(data => {
            if (data.toString() === chosenAnswer.answerText) {
                setBackgroundColor("lightgreen");
                setCount();
                notifyCorrect()
            } else {
                setBackgroundColor("indianred");
                notifyWrong(data)
            }
            setDisabled(true);
        });
    }

    return (
    <QuestionContainer backgroundColor={backgroundColor}>
      <h3>{question.questionText}</h3>
        <ToastContainer
            position="top-center"
            autoClose={2000}
            hideProgressBar
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
        />
      <AnswerContainer>
        {question.answers.map(answer => (
          <Answer disabled={disabled} setChosenAnswer={setChosenAnswer} answer={answer} key={answer.id} questionId={question.id} />
        ))}
      </AnswerContainer>
      <CheckButton onClick={validateUserAnswer} disabled={disabled} >Check Answer</CheckButton>
    </QuestionContainer>
  )
}
export default Question

const QuestionContainer = styled.section`
  width: 400px;
  border: 1px solid #009fb7;
  border-radius: 20px;
  padding: 20px;
  background-color: ${props => props.backgroundColor};
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
