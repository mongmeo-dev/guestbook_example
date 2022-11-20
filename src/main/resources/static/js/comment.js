function clear() {
  const commentContainer = document.querySelector('#commentContainer');
  const commentTextArea = document.querySelector('#comment');
  commentContainer.innerHTML = '';
  commentTextArea.value = '';
}

async function getAllComments() {
  const guestbookId = location.href.split('/').reverse()[0];
  const response = await fetch(
      `http://localhost:8080/api/guestbooks/${guestbookId}/comments`);
  return await response.json();
}

async function getUserInfo(userId) {
  const response = await fetch(`http://localhost:8080/api/users/${userId}`);
  return await response.json();
}

function createCommentElement(comment, user) {
  const commentDiv = document.createElement('div');
  const authorNicknameSpan = document.createElement('span');
  const writeTimeSpan = document.createElement('span');
  const deleteA = document.createElement('a');
  const contentP = document.createElement('p');
  commentDiv.style.border = 'black solid 0.5px';
  commentDiv.classList.add('p-3', 'mb-3')
  authorNicknameSpan.textContent = user.nickname;
  writeTimeSpan.textContent = ` | 작성시간 : ${comment.writeTime} | `;
  deleteA.textContent = '삭제'
  deleteA.href = `${location.href}/comment/${comment.id}/delete`;
  contentP.textContent = comment.content;

  commentDiv.append(authorNicknameSpan, writeTimeSpan, deleteA,
      document.createElement('hr'), contentP);

  return commentDiv;
}

async function printCommentToHtml(comment) {
  const user = await getUserInfo(comment.authorId);
  const commentContainer = document.querySelector('#commentContainer');
  commentContainer.appendChild(createCommentElement(comment, user));
}

async function printAllComment() {
  const comments = await getAllComments();
  comments.forEach(comment => printCommentToHtml(comment));
}

async function registerComment() {
  const guestbookId = location.href.split('/').reverse()[0];
  const commentContent = document.querySelector('#comment').value;
  const response = await fetch(
      `http://localhost:8080/api/guestbooks/${guestbookId}/comments`,
      {
        method: 'POST',
        body: JSON.stringify({content: commentContent}),
        headers: {'Content-Type': 'application/json'}
      });

  if (response.status === 201) {
    clear();
    await printAllComment();
  } else {
    alert('댓글 등록 실패');
  }

}