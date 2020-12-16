let queryString = decodeURIComponent(window.location.search).substring(1);
let billsList = null, userName, firstName;
let promiseResponse = start();

async function start() {
    let queries = queryString.split('&');
    userName = queries[0].substring(9);
    firstName = queries[1].substring(5);
    console.log(firstName + " " + userName);

    document.getElementById("welcomeMessage").innerHTML = "Welcome " + firstName + " To IIIT Bangalore";
    document.getElementById("rollNumber").innerHTML = "Your Roll Number & User Name is " + userName;


    let response = await fetch('api/bills/paid', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            userName: userName
        })
    });

    billsList = await response.json();
    if (billsList.length === 0) {
        document.getElementById('heading').innerHTML = "You didn't paid any of the bills";
        document.getElementById('show-payments').remove();

    } else {
        document.getElementById('heading').innerHTML = "Following are the Paid bills related to your account";

        let tableBody = document.getElementById('paidbills');
        tableBody.innerHTML = "";
        for (let i = 0; i < billsList.length; i++) {
            tableBody.innerHTML += '<tr>';
            let temp = "";
            temp += '<td>' + billsList[i].description + '</td>';
            temp += '<td>' + billsList[i].paymentDate + '</td>';
            temp += '<td>' + billsList[i].amount + '</td>';
            tableBody.innerHTML += temp + '</tr>';
        }
    }
}

async function gotoshowPayment()
{   window.location.href="BillPayment.html?"+queryString;
}

async function goToHome() {
    window.location.href = "Bills.html?" + queryString;
}