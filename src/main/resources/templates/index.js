// import axios from 'axios'
// import Axios from "axios";
// 5분마다 IotPlatformController url 2개 요청

window.onload = function() {
    displayData();
    drawGraph(jsonGraphFile);
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
    const jsonString = '{"data1": 10, "data2": 20, "data3": 30}';
    let jsonData = JSON.parse(jsonString);

    let data1 = jsonData.data1.toString();
    let data2 = jsonData.data2.toString();
    let data3 = jsonData.data3.toString();
    
    let data1div = document.getElementById("data1")
    let data2div = document.getElementById("data2");
    let data3div = document.getElementById("data3");
    
    data1div.textContent = "온도 : " + data1;
    data2div.textContent = "용존산소 : " + data2;
    data3div.textContent = "시간 : " + data3;
}

// 그래프를 그리는 함수
function drawGraph(jsonFile) {
    const times = jsonFile.map(item => item.time);
    const temps = jsonFile.map(item => item.temp);
    const dos = jsonFile.map(item => item.do);

    const ctx = document.getElementById('myChart').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: times,
            datasets: [
                {
                    label: 'Temperature',
                    data: temps,
                    borderColor: 'rgba(255, 99, 132, 1)',
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    fill: false,
                },
                {
                    label: 'DO',
                    data: dos,
                    borderColor: 'rgba(54, 162, 235, 1)',
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
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


