import * as React from 'react'
import Question from '../components/Question'
import Counter from "../components/Counter";
import styled from 'styled-components'
import {useState} from "react";

function Homepage({ questions }) {


    const [count, setCount] = useState(0);

    function handleSetCount() {
        setCount(count + 1)
    }

  return (
    <QuestionsContainer>
        <Counter questions={questions} count={count}/>
      {questions.map(question => (
        <Question question={question} key={question.id} setCount={handleSetCount} />
      ))}
    </QuestionsContainer>
  )
}
export default Homepage

const QuestionsContainer = styled.section`
  display: flex;
  gap: 15px;
  justify-content: center;
  flex-wrap: wrap;
  background-color: #424B54;
  padding: 50px;
  flex-direction: column;
  text-align: center;
  align-items: center;
`
