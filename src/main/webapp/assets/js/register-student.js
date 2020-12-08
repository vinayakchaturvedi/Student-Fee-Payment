let login_form = document.getElementById('register-student');
//window.onload = printWelcomeMessage;

login_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (login_form.checkValidity() === true) {
        let response = await fetch('api/register/student', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                firstName: document.getElementById('firstName').value,
                lastName: document.getElementById('lastName').value,
                password: document.getElementById('password').value,
                email: document.getElementById('email').value,
                graduationYear: document.getElementById('graduationYear').value,
            })
        });
        let result = await response;
        console.log(result);
    }
    login_form.classList.add('was-validated');
    window.location.href = "Bills.html";
});


