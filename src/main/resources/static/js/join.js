async function join() {
  const emailField = document.querySelector('#email');
  const passwordField = document.querySelector('#password');
  const nicknameField = document.querySelector('#nickname');
  const email = emailField.value;
  const password = passwordField.value;
  const nickname = nicknameField.value;
  const response = await fetch("http://localhost:8080/api/users/", {
    method: 'POST',
    body: JSON.stringify({email, password, nickname}),
    headers: {'Content-Type': 'application/json'}
  });
  if (response.status === 201) {
    location.href = 'http://localhost:8080/login'
  } else {
    passwordField.value = '';
  }
}