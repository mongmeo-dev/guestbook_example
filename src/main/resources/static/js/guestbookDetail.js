async function getGuestbook() {
  const id = location.href.split("/").reverse()[0];
  const response = await fetch(`http://localhost:8080/api/guestbooks/${id}`);
  return await response.json();
}

async function getUserInfo(userId) {
  const response = await fetch(`http://localhost:8080/api/users/${userId}`);
  return await response.json();
}

async function init() {
  const guestbook = await getGuestbook();
  const user = await getUserInfo(guestbook.authorId);
  document.querySelector("#content").textContent = guestbook.content;
  document.querySelector("#writeTime").textContent = guestbook.writeTime;
  document.querySelector("#authorNickname").textContent = user.nickname;
}
