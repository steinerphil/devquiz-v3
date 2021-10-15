import styled from "styled-components";

export default function Counter(props) {

    return(
        <Section>

            <p>Your score: {props.count} / {props.questions.length} </p>
            <hr/>

        </Section>
    )
}

const Section = styled.section`
  width: 100%;
  color: #EAF6FF;
  font-family: 'Montserrat', sans-serif;;
`