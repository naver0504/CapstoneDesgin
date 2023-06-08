/**
import Axios from 'axios';

export default class axios {

    static SendText() {
        let textareaValue = document.getElementById("myTextarea").value;

        Axios.post('http://localhost:8080/userQuestion', {
            userQuestion: textareaValue
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
}

**/