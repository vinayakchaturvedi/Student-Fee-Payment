let bill_show = document.getElementById('show-bills');
let queryString = decodeURIComponent(window.location.search).substring(1);
let queries = queryString.split('&');
let userName = queries[0].substring(10);
let firstName = queries[1].substring(6);
console.log(firstName + " " + userName);

document.getElementById("welcomeMessage").innerHTML = "Welcome " + firstName +" To IIIT Bangalore"
let tableBody = document.getElementById('bills');
tableBody.innerHTML = "";

let response = await fetch('api/bills/show', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json;charset=utf-8'
    },
    body: JSON.stringify({
        userName: document.getElementById('userName').value
    })
});

bills = await response.json();


for (let i = 0; i < 1; i++) {
    tableBody.innerHTML += '<tr>';
    let temp = "";
    temp += '<td>' + 'Registration Fee' + '</td>';
    temp += '<td>' + '10-Dec-2020' + '</td>';
    temp += '<td>' + '15-Dec-2020' + '</td>';
    temp += '<td>' + '1000' + '</td>';
    temp += '<td>' + '0' + '</td>';
    temp += '<td>' + '1000' + '</td>';
    temp += '<td>' + '1000' + '</td>';
    tableBody.innerHTML += temp + '</tr>';
}


