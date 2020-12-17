let queryString = decodeURIComponent(window.location.search).substring(1);
let billsList = null,billsList_alert = null, userName, firstName;
let promiseResponse = start();

async function start() {
    let queries = queryString.split('&');
    userName = queries[0].substring(9);
    firstName = queries[1].substring(5);
    console.log(firstName + " " + userName);

    document.getElementById("welcomeMessage").innerHTML = "Welcome " + firstName + " To IIIT Bangalore";
    document.getElementById("rollNumber").innerHTML = "Your Roll Number & User Name is " + userName;
    let response2 = await fetch('api/bills/show', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            userName: userName
        })
    });

    billsList_alert = await response2.json();

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
function functionNotify()
{   let tableBody = document.getElementById('notification-drop');
    tableBody.innerHTML = "";
    var date= new Date();
    var today=date.getDate();
    if (billsList_alert.length === 0) {
        tableBody.innerHTML += '<li><a>';
        let temp = "";
        temp += 'All bills are PAID' ;
        tableBody.innerHTML += temp + '</a></li>';
    }
    else
    {for (let i = 0; i < billsList_alert.length; i++) {
        tableBody.innerHTML += '<li><a>';
        var day=parseInt((billsList_alert[i].deadline).substring(0,2));
        let temp = "";
        if ((day-today)<5) {
            temp += billsList_alert[i].description+' Deadline overs in '+ (day-today) +' Days' ;
        }
        tableBody.innerHTML += temp + '</a></li>';
        }
    }
}
async function gotoshowPayment() {
    window.location.href="BillPayment.html?"+queryString;
}

async function goToHome() {
    window.location.href = "Bills.html?" + queryString;
}