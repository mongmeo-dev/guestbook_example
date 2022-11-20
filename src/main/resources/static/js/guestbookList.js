async function getParsedResponse() {
  const response = await fetch("http://localhost:8080/api/guestbooks/");
  return await response.json();
}

function printGuestbooks(guestbooks) {
  const tableBody = document.querySelector("tbody");
  guestbooks.forEach(guestbook => {
    const tr = document.createElement("tr");
    const idTd = document.createElement("td");
    const contentTd = document.createElement("td");
    const contentA = document.createElement("a");
    const authorTd = document.createElement("td");
    const writeTimeTd = document.createElement("td");
    idTd.textContent = guestbook.id;
    contentTd.appendChild(contentA)
    contentA.textContent = guestbook.content;
    contentA.href = `http://localhost:8080/guestbook/${guestbook.id}`
    authorTd.textContent = guestbook.authorId;
    writeTimeTd.textContent = guestbook.writeTime;
    tr.append(idTd, contentTd, authorTd, writeTimeTd);
    tableBody.append(tr);
  })
}

async function init() {
  const guestbooks = await getParsedResponse();
  printGuestbooks(guestbooks);
}
