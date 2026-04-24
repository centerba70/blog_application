function PostDTO(username, title, text) {
    this.username = username;
    this.title = title;
    this.text = text;
}

async function sendPost() {
    let username = document.getElementById("username").value;
    let title = document.getElementById("title").value;
    let text = document.getElementById("postContent").value;
    let postDTO = new PostDTO(username, title, text);
    let url = "http://localhost:8081/post";

    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(postDTO)
    });
}

function showAllPosts() {
    let url = "http://localhost:8081/posts"
    fetch(url).then(function (response){
        response.json().then(function (data) {
            console.log(data);
            return data;
        })
            .then((data) => {
                data.forEach((item) => addPostToHtml(item))
            })

    })
}

function addPostToHtml(item) {
    let posts = document.getElementById("posts-container");
    let div = document.createElement("div");
    div.className = "post";
    div.innerHTML = `<p>Post ##</p>
        <p>Title: ${item.title}</p>
    <p>Content: ${item.text}</p>
    <p>Author: ${item.username}</p>`;
    posts.appendChild(div);
}