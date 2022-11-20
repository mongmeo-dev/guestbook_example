async function deleteComment() {
  const guestbookId = location.href.split('/guestbook/')[1].split('/')[0];
  const commentId = location.href.split("/comment/")[1].split('/')[0];

  const response = await fetch(
      `http://localhost:8080/api/guestbooks/${guestbookId}/comments/${commentId}`,
      {
        method: 'DELETE',
      });
  if (response.status === 204) {
    location.href = `${location.host}/guestbook/${guestbookId}`;
  } else {
    alert('delete error!');
  }
}