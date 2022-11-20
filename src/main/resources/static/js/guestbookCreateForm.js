async function createGuestbook() {
  const content = document.querySelector('#content').value;
  const response = await fetch("http://localhost:8080/api/guestbooks/", {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({content})
  });
  if (response.status === 201) {
    const createdGuestbook = await response.json();
    location.href = `http://localhost:8080/guestbook/${createdGuestbook.id}`;
  } else {
    alert("create fail!");
  }
}