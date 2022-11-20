async function login() {
  const emailField = document.querySelector('#email');
  const passwordField = document.querySelector('#password');
  const email = emailField.value;
  const password = passwordField.value;
  const response = await fetch("http://localhost:8080/api/login/", {
    method: 'POST',
    body: JSON.stringify({email, password}),
    headers: {'Content-Type': 'application/json'}
  });
  if (response.status === 200) {
    location.href = 'http://localhost:8080/guestbook'
  } else {
    passwordField.value = '';
  }
}