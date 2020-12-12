let queryString = decodeURIComponent(window.location.search).substring(1);
let promiseResponse = start();

async function start() {
    let queries = queryString.split('&');
    let userName = queries[0].substring(9);
    let firstName = queries[1].substring(5);
    console.log(firstName + " " + userName);

    document.getElementById("welcomeMessage").innerHTML = "Welcome " + firstName + " To IIIT Bangalore"


    let response = await fetch('api/bills/show', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            userName: userName
        })
    });

    let billsList = await response.json();
    if (billsList.length === 0) {
        document.getElementById('heading').innerHTML = "Great !!! No Pending bills for your account";
        document.getElementById('show-bills').remove();
    } else {
        document.getElementById('heading').innerHTML = "Following are the bills related to your account";

        let tableBody = document.getElementById('bills');
        tableBody.innerHTML = "";
        for (let i = 0; i < billsList.length; i++) {
            tableBody.innerHTML += '<tr>';
            let temp = "";
            temp += '<td>' + billsList[i].description + '</td>';
            temp += '<td>' + billsList[i].billDate + '</td>';
            temp += '<td>' + billsList[i].deadline + '</td>';
            temp += '<td>' + billsList[i].totalAmount + '</td>';
            temp += '<td>' + billsList[i].paidAmount + '</td>';
            temp += '<td>' + billsList[i].remainingAmount + '</td>';
            temp += '<td>' + billsList[i].remainingAmount + '</td>';
            temp += '<td><input class=\'myclass\' type=\'button\' value=\'Pay Now\'/></td>'

            tableBody.innerHTML += temp + '</tr>';
        }
    }
}
