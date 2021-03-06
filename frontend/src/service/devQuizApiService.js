import axios from 'axios';


const getHeader = (token) => {
    return {
        headers: {
            Authorization: `Bearer ${token}`
        },
    }
}

export function getQuestions(token) {
    return axios.get('/api/question', getHeader(token))
    .then(response => {
      const questionArray = response.data;
      questionArray.forEach(question => question.answers.map(answer => delete answer.isCorrect))
      return questionArray;
    })
    // .catch(err => console.error(err))
}

export function getValidate(questionId, token){
    return axios.get('/api/question/validate/'+questionId, getHeader(token))
}

export function postQuestion(newQuestion, token) {
  return axios
    .post('/api/question', newQuestion, getHeader(token))
    .then(response => response.data)
    .catch(console.error)
}

export function postLogin(credentials) {
    return axios
        .post('auth/login', credentials)
}

export function postGithubLogin(code) {
    return axios
        .post('auth/github', code)
}

export function getConfig() {
    return axios.get("auth/config").then(response => response.data)
}