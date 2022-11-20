async function deleteGuestbook() {
  const guestbookId = location.href.split('/guestbook/')[1].split('/')[0];

  const response = await fetch(
      `http://localhost:8080/api/guestbooks/${guestbookId}`,
      {
        method: 'DELETE',
      });
  if (response.status === 204) {
    location.href = 'http://localhost:8080/guestbook'
  } else {
    alert('delete error!');
  }
}