import axios from 'axios'

export function getQuestions() {
  return axios
    .get('/api/question')
    .then(response => {
      const questionArray = response.data;
      questionArray.forEach(question => question.answers.map(answer => delete answer.isCorrect))
      return questionArray;
    })
    .catch(err => console.error(err))
}

export function validate(questionId){
    return axios
        .get('/api/question/validate/'+questionId)
        .then(response => {
            return response.data
        })
        .catch(err => console.log(err));
}

export function addQuestion(newQuestion) {
  return axios
    .post('/api/question', newQuestion)
    .then(response => response.data)
    .catch(console.error)
}
