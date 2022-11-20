async function logout() {
  const response = await fetch("http://localhost:8080/api/logout/", {
    method: 'POST',
  });
  if (response.status === 200) {
    location.href = 'http://localhost:8080/login'
  } else {
    alert('logout error!');
  }
}