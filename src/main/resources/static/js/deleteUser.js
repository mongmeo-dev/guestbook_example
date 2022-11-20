async function deleteUser() {
  const userId = location.href.split('/user/')[1].split('/')[0];
  const response = await fetch(
      `http://localhost:8080/api/users/${userId}`,
      {
        method: 'DELETE',
      });
  if (response.status === 204) {
    location.href = `${location.origin}/login`;
  } else {
    alert('delete error!');
  }
}