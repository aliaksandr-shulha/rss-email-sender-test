async function save() {
    let link = document.getElementById('rss');
    let list = document.getElementById('rssList');
    let email = document.getElementById('email');
    let response = await fetch("http://localhost:8080/save",
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                "email": email.value,
                "rssLinks": [link.value]
            })
        });
    if (!response.ok) {
        alert("Error HTTP: " + response.status);
    }
    createItem(list, link.value);
}

async function send() {
    let email = document.getElementById('email');
    let content = document.getElementById('rssContent');
    let response = await fetch("http://localhost:8080/send",
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                "email": email.value
            })
        });
    if (response.ok) {
        let text = response.text();
        content.innerHTML = await text;
    } else {
        alert("Error HTTP: " + response.status);
    }
}

async function emailEnter() {
    let email = document.getElementById('email');
    email.setAttribute('readonly', 'true');
    let response = await fetch("http://localhost:8080/saveUser",
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({'email': email.value})
        });
    if (response.ok) {
        let json = await response.json();
    } else {
        alert("Error HTTP: " + response.status);
    }
}

function remove() {
    let list = document.getElementById('rssList');
    list.querySelectorAll('input:checked').forEach(elem => elem.parentElement.remove());
}

function createItem(list, link) {
    let br = document.createElement('br');
    let item = document.createElement('label');
    let checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.name = link;
    checkbox.checked = false;
    item.append(checkbox);
    item.append(link);
    item.appendChild(br);
    list.appendChild(item);
}