
window.onload = function() {
    displayData();
    parsingGraphData();
};

let Chart1;
let Chart2;

let xhr = new XMLHttpRequest();
// 예시 데이터
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
                clearCharts();
                drawGraph(response, 1); // temp
                drawGraph(response, 2); // do
            } else if(xhr.status >= 400) {
                console.error("XMLHttpRequest error:", xhr.status);
            }
        }
    };

    xhr.send();
}

function clearCharts() {
    let chart1 = Chart.getChart("myChart1");
    let chart2 = Chart.getChart("myChart2");

    if (chart1) {
        chart1.clear();
    }

    if (chart2) {
        chart2.clear();
    }
}

// 그래프를 그리는 함수
function drawGraph(jsonFile, value) {

    let times = jsonFile.map(item => item.dateTime.toString().slice(item.dateTime.toString().indexOf('T') + 1, item.dateTime.toString().indexOf('T') + 6));
    times.reverse();
    if(value == 1) {
        if(Chart1 != null)
            Chart1.destroy();
        let values = jsonFile.map(item => item.temp);
        values.reverse();

        const ctx = document.getElementById('myChart1').getContext('2d');

        Chart1 = new Chart(ctx, {
            type: 'line',
            data: {
                labels: times,
                datasets: [
                    {
                        label: 'Temperature',
                        data: values,
                        borderColor: 'rgba(255, 99, 132, 1)',
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                        fill: false,
                        width: 100,
                    },
                ],
            },
            options: {
                responsive: true,
            },
        });
    } else {
        if(Chart2 != null)
            Chart2.destroy();

        let values = jsonFile.map(item => item.do);
        values.reverse();

        const ctx = document.getElementById('myChart2').getContext('2d');

        Chart2 = new Chart(ctx, {
            type: 'line',
            data: {
                labels: times,
                datasets: [
                    {
                        label: 'DO',
                        data: values,
                        borderColor: 'rgba(54, 162, 235, 1)',
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        fill: false,
                        width: 100,
                    },
                ],
            },
            options: {
                responsive: true,
            },
        });
    }
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


