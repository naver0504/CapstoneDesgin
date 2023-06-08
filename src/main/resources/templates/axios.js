import Axios from 'axios';

export function SendText() {
    let textareaValue = document.getElementById("myTextarea").value;
    console.log(textareaValue);

    Axios.post('http://localhost:8080/userQuestion', {
        text: textareaValue
    })
        .then(function(response) {
            console.log("axios then");
            var outputDiv = document.getElementById("output");
            outputDiv.textContent = JSON.stringify(response.data, null, 2) ? textareaValue : textareaValue;
        })
        .catch(function(error) {
            console.error(error);
        });
}