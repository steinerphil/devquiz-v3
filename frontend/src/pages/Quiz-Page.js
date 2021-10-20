import * as React from 'react'
import {useState} from "react";
import Counter from "../components/Counter";
import Question from "../components/Question";
import styled from "styled-components";

export default function QuizPage({questions}) {

    const [nextQuestion, setNextQuestion] = useState({})

    // function getNextQuestion(questions) {
    //     let nextNumber = 0;
    //     if(nextNumber <= questions.length){
    //         setNextQuestion(questions[nextNumber])
    //     }
    //     nextNumber++;
    // }

    const [count, setCount] = useState(0);

    function handleSetCount() {
        setCount(count + 1)
    }


    return(
        <QuestionsContainer>
            <Counter questions={questions} count={count}/>
                <Question question={questions[0]} key={questions[0].id} setCount={handleSetCount} />
        </QuestionsContainer>
    )
}

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