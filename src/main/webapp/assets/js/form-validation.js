let login_form = document.getElementById('login-validation');
//window.onload = printWelcomeMessage;

login_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (login_form.checkValidity() === true) {
        let response = await fetch('api/login/validate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                userName: document.getElementById('userName').value,
                password: document.getElementById('password').value,
            })
        });
        let result = await response;
        console.log(result);
        let myStatus = response.status;
        if (myStatus === 404) {
            document.getElementById("incorrectUser").innerHTML = "Incorrect User Name/Password !!!";
        } else {
            login_form.classList.add('was-validated');
            window.location.href = "Bills.html";
        }
    }
});


