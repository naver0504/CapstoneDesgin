// import axios from 'axios'
// import Axios from "axios";
// 5분마다 IotPlatformController url 2개 요청

let _isRequestPending = false;

window.onload = function() {
    displayData();
    parsingGraphData();
};


let xhr = new XMLHttpRequest();
// 예시 데이터
const jsonString = '{"data1": 10, "data2": 20, "data3": 30}';
const jsonGraphFile = [
    {"time": "2023-06-01", "temp": 25, "do": 7},
    {"time": "2023-06-02", "temp": 26, "do": 6},
    {"time": "2023-06-03", "temp": 27, "do": 5},
    {"time": "2023-06-04", "temp": 28, "do": 4},
    {"time": "2023-06-05", "temp": 29, "do": 3},
    {"time": "2023-06-06", "temp": 30, "do": 2},
    {"time": "2023-06-07", "temp": 31, "do": 1},
    {"time": "2023-06-08", "temp": 32, "do": 2},
    {"time": "2023-06-09", "temp": 33, "do": 3},
    {"time": "2023-06-10", "temp": 34, "do": 4}
];

// 문자열 값을 표시하는 함수
function displayData() {
    let url = 'http://localhost:8080/iot';
    xhr.open('GET', url, true);

    xhr.onload = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 400) {
                let response = JSON.parse(xhr.responseText);

                let time = response.dateTime.toString().slice(response.dateTime.toString().indexOf('T') + 1, response.dateTime.toString().indexOf('T') + 9);
                print(time);
                let data1 = response.temp.toString();
                let data2 = response.do.toString();
                let data3 = time;

                let data1div = document.getElementById("data1");
                let data2div = document.getElementById("data2");
                let data3div = document.getElementById("data3");

                data1div.textContent = "온도 : " + data1;
                data2div.textContent = "용존산소 : " + data2;
                data3div.textContent = "시간 : " + data3;
            } else if(xhr.status >= 400) {
                console.error("XMLHttpRequest error:", xhr.status);
            }
        }
    };

    xhr.send();
}

function parsingGraphData() {
    let url = 'http://localhost:8080/graph';
    xhr.open('GET', url, true);

    xhr.onload = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 400) {
                let response = JSON.parse(xhr.responseText);

                //let time = response.dateTime.toString().slice(response.dateTime.toString().indexOf('T') + 1, response.dateTime.toString().indexOf('T') + 9);

                drawGraph(response, 1); // temp
                drawGraph(response, 2); // do
            } else if(xhr.status >= 400) {
                console.error("XMLHttpRequest error:", xhr.status);
            }
        }
    };

    xhr.send();
}
// 그래프를 그리는 함수
function drawGraph(jsonFile, value) {
    let times = jsonFile.map(item => item.dateTime.toString().slice(item.dateTime.toString().indexOf('T') + 1, item.dateTime.toString().indexOf('T') + 9));
    let values = (value == 1) ? jsonFile.map(item => item.temp) : jsonFile.map(item => item.do) ;

    const ctx = (value == 1) ? document.getElementById('myChart').getContext('2d') : document.getElementById('myChart2').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: times,
            datasets: [
                {
                    label: value==1 ? 'Temperature' : 'DO',
                    data: values,
                    borderColor: value==1 ? 'rgba(255, 99, 132, 1)' : 'rgba(54, 162, 235, 1)',
                    backgroundColor: value==1 ? 'rgba(255, 99, 132, 0.2)' : 'rgba(54, 162, 235, 0.2)',
                    fill: false,
                },
            ],
        },
        options: {
            responsive: true,
        },
    });
}

function SendText() {
    let textareaValue = document.getElementById("myTextarea").value;
    let url = 'http://localhost:8080/userQuestion';
    let data = JSON.stringify({
        userQuestion: textareaValue
    });

    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 500) {
                console.log("XMLHttpRequest success");
                let response = JSON.parse(xhr.responseText);
                let outputDiv = document.getElementById("output");
                outputDiv.textContent = JSON.stringify(response, null, 2) ? textareaValue : textareaValue;
            } else {
                console.error("XMLHttpRequest error:", xhr.status);
            }
        }
    };

    xhr.send(data);
}


