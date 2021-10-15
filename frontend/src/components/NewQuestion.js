import * as React from 'react'
import NewQuestionAnswerItem from "./NewQuestionAnswerItem";
import styled from "styled-components";

export default function NewQuestion({
                                        answers,
                                        handleAnswerTextInput,
                                        handleAnswerCorrectStatusChange,
                                        handleQuestionTextInput,
                                        question,
                                    }) {
    return (
        <form className="question">
      <Textarea
          placeholder="Insert your Question here...."
          onChange={e => {
              handleQuestionTextInput(e)
          }}
          value={question.questionText}
      />
            <section>
                {answers.map((answer, index) => (
                    <NewQuestionAnswerItem answer={answer}
                                           index={index}
                                           handleAnswerTextInput={handleAnswerTextInput}
                                           handleAnswerCorrectStatusChange={handleAnswerCorrectStatusChange}
                                           key={index}/>
                ))}
            </section>
        </form>
    )
}

const Textarea = styled.textarea`
height: 100px;
  width: 340px;
`
