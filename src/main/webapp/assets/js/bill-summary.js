let queryString = decodeURIComponent(window.location.search).substring(1);
let dict = {}, userName, firstName;
let promiseResponse = start();
let counter=0;

async function start() {
    let queries = queryString.split('&');
    userName = queries[0].substring(9);
    firstName = queries[1].substring(5);

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

    let table = document.getElementById('notification-drop');
    let today = new Date();
    table.innerHTML = "";
    table.innerHTML += '<li><a>';

    if (billsList.length === 0) {
        let temp = "";
        temp += 'All bills are paid.';
        table.innerHTML += temp + '</a></li>';
    } else {

        for (let i = 0; i < billsList.length; i++) {
            let billdate = new Date((billsList[i].deadline));
            let diff = billdate.getTime() - today.getTime();
            let msInDay = 1000 * 3600 * 24;
            let remain = Math.ceil(diff / msInDay)
            if (remain < 5) {
                let temp = "";
                temp += billsList[i].description + ' Deadline overs in ' + remain + ' Days';
                table.innerHTML += temp + '</a></li>';
                counter++;
            }
        }
        if(counter === 0)
        {   let temp = "";
            temp += "None  of the bill's deadline overs in 5 days";
            table.innerHTML += temp + '</a></li>';
        }
    }
    let alertshow = document.getElementById('deadlineAlert');
    if (counter === 0) {
        alertshow.innerHTML = 'Alerts';
    } else {
        alertshow.innerHTML = 'Alerts (' + counter + ')';
    }

    for (let i = 2; i < queries.length; i++) {
        let key = queries[i].split('=')[0];
        dict[key] = queries[i].split('=')[1];
        console.log(key + ": " + dict[key] + '\n');
    }

    document.getElementById("welcomeMessage").innerHTML = "Welcome " + firstName + " To IIIT Bangalore";

    document.getElementById('heading').innerHTML = "Please find below your payment summary, please confirm";

    let tableBody = document.getElementById('show-bills-summary');
    tableBody.innerHTML = "";
    let sum = 0;
    for (let key in dict) {
        tableBody.innerHTML += '<tr>';
        let temp = "";
        temp += '<td>' + key + '</td>';
        temp += '<td>' + dict[key] + '</td>';
        tableBody.innerHTML += temp + '</tr>';
        sum += parseInt(dict[key]);
    }

    tableBody.innerHTML += '<tr>';
    let temp = "";
    temp += '<td><h2 class="total">' + "Total Amount" + '</h2></td>';
    temp += '<td><h2 class="total">' + sum + '</h2></td>';
    tableBody.innerHTML += temp + '</tr>';
}

async function payment() {
    console.log("Query String: " + queryString);

    let response = await fetch('api/bills/pay', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            queryString
        })
    });

    let result = await response;
    console.log(response)

    document.getElementById("heading").remove();
    document.getElementById("summaryTable").remove();
    document.getElementById("paymentButton").remove();



    document.getElementById("paymentDone").innerHTML = "Thanks for paying you bills please click on the below button to redirect to your Home page";
    let Homebutton = document.getElementById('payment');
    Homebutton.innerHTML = '<div id="homeButton">\n' +
        '        <button class="btn paymentButton" type="submit" onclick="goToHome()">Home</button>\n' +
        '    </div>';
}
function functionNotify()
{   let alertshow = document.getElementById('deadlineAlert');
    alertshow.innerHTML='Alerts';
}
async function goToHome() {
    let querystr = "userName=" + userName + "&name=" + firstName;
    window.location.href = "Bills.html?" + querystr;
}
async function gotoshowPayment()
{   let querystr = "userName=" + userName + "&name=" + firstName;
    window.location.href="BillPayment.html?"+querystr;
}