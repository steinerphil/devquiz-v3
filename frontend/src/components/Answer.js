import * as React from 'react'
import styled from 'styled-components'

function Answer({setChosenAnswer, answer, questionId, disabled }) {

    function handleRadioButtonClick() {
        setChosenAnswer(answer);
    }

    return (
    <AnswerContainer>
      <input onChange={handleRadioButtonClick} type="radio" name={questionId} disabled={disabled}/>
      <h4>{answer.answerText}</h4>
    </AnswerContainer>
  )
}

export default Answer;

const AnswerContainer = styled.section`
  display: flex;
  align-items: center;
  gap: 5px;
`
