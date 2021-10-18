import * as React from "react";
import styled from "styled-components";

export default function NewQuestionAnswerItem({answer, index, handleAnswerTextInput, handleAnswerCorrectStatusChange}) {
    return (
        <Section key={index}>
            <h4>Answer {index + 1}</h4>
            <input
                onChange={e => {
                    handleAnswerTextInput(e, index)
                }}
                className="answer-1-text"
                value={answer.answerText}
                required={true}
            />
            <p>Mark this answer as correct: <input
                type="radio"
                name={'new-question'}
                checked={answer.isCorrect}
                onChange={() => handleAnswerCorrectStatusChange(index)}
            /></p>

        </Section>
    )
}

const Section = styled.section`
  width: 300px;
  border: 1px solid #424B54;
  border-radius: 20px;
  padding: 10px;
  margin: 9px;
  font-family: 'Montserrat', sans-serif;;
`