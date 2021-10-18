import * as React from 'react'
import Question from '../components/Question'
import styled from 'styled-components'
import {useState} from "react";

function Homepage({ questions }) {

  return (
    <QuestionsContainer>
      {questions.map(question => (
        <Question question={question} key={question.id} />
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
`
