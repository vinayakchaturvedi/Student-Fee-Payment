let queryString = decodeURIComponent(window.location.search).substring(1);
let billsList = null, userName, firstName;
let dict = {};
let promiseResponse = start();

/*
$(document).ready(function () {
    $('#icon').click(function () {
        $('ul').toggleClass('show');
    });
});
*/

async function start() {
    let queries = queryString.split('&');
    userName = queries[0].substring(9);
    firstName = queries[1].substring(5);
    console.log(firstName + " " + userName);

    document.getElementById("welcomeMessage").innerHTML = "Welcome " + firstName + " To IIIT Bangalore";
    document.getElementById("rollNumber").innerHTML = "Your Roll Number & User Name is " + userName;


    let response = await fetch('api/bills/show', {
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
        document.getElementById('heading').innerHTML = "Great !!! No Pending bills for your account";
        document.getElementById('show-bills').remove();
        document.getElementById('payment').remove();
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
            temp += '<td> <input type="number" value=0 oninput="calculate()" min=0 onfocusout="calculate()" id="payment' + i + '" class="myclass" name="payment"/>' + '</td>';

            tableBody.innerHTML += temp + '</tr>';
        }
    }
}

let finalSum = 0;

function calculate() {
    for (let i = 0; i < billsList.length; i++) {
        if ((billsList[i].remainingAmount) - (document.getElementById("payment" + i).value) < 0) {
            alert("Please enter the  value less then Remaining amount.")
            document.getElementById("payment" + i).value = 0;
        }
        if((document.getElementById("payment" + i).value).startsWith("-")){
            alert("Please enter positive amount.")
            document.getElementById("payment" + i).value = 0;
        }
    }
    let sum = 0;

    for (let i = 0; i < billsList.length; i++) {
        let val = document.getElementById("payment" + i).value;
        const x = parseInt(val === "" ? "0" : val);
        sum += x;
        document.getElementById('totalAmount').value = sum;
        dict[billsList[i].description] = x;
    }
    finalSum = sum;
}


function payment() {
    if (finalSum === 0) {
        alert("Your Total Payment Amount is 0")
    } else {
        let queryString = "?userName=" + userName + "&name=" + firstName;
        for (let key in dict) {
            if (parseInt(dict[key]) !== 0)
                queryString += "&" + key + "=" + dict[key];
        }
        console.log("Query String: " + queryString);
        window.location.href = "BillSummary.html" + queryString;
    }
}
function functionNotify()
{   let tableBody = document.getElementById('notification-drop');
    tableBody.innerHTML = "";
    var date= new Date();
    var today=date.getDate();
    if (billsList.length === 0) {
        tableBody.innerHTML += '<li><a>';
        let temp = "";
        temp += 'All bills are PAID' ;
        tableBody.innerHTML += temp + '</a></li>';
    }
    else
    {for (let i = 0; i < billsList.length; i++) {
        tableBody.innerHTML += '<li><a>';
        var day=parseInt((billsList[i].deadline).substring(0,2));
        let temp = "";
        if ((day-today)<5) {
            temp += billsList[i].description+' Deadline overs in '+ (day-today) +' Days' ;
        }
        tableBody.innerHTML += temp + '</a></li>';
    }}
}

async function gotoshowPayment()
{   window.location.href="BillPayment.html?"+queryString;
}

async function goToHome() {
        window.location.href = "Bills.html?" + queryString;
}