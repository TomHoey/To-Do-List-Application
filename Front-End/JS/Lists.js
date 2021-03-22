`use strict`

const getList = () => {

    fetch("http://localhost:8080/list").then(response => {
        console.log(response);

        if(response.status !== 200) {
            console.error(`status: ${response.status}`)
            return;
        }

        response.json()
        .then(listData => {
            console.log(listData);

            let list = document.querySelector(".getList");

            let currentL = list.querySelector("usableList");

            if (currentL != null) {
                usableList.remove();
            }

            let l = document.createElement("list");
            list.innerText = JSON.stringify(listData);
            list.append(l);
        
        }).catch(err => console.log("Uh-oh, fella you need to fix something!" + err));
    })

}

const postList = () => {

    let listName = document.querySelector("#listNameInput").value;
    console.log(listName);

    const listObject = {

        "listName": listName,
        
    }

    fetch("http://localhost:8080/list", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body:JSON.stringify(listObject)
    })

    .then(res => res.json())
    .then(data => console.log(data))
    .catch(err => console.log(err))

}

const putList = () => {

    let toDoListID = document.querySelector("#updateListIDInput").value;
    let listName = document.querySelector("#updateListNameInput").value;

    const updateObject = {

        "listName": listName,
        "toDoListID": 
        {
            "ID": toDoListID
        }
    }

    fetch("http://localhost:8080/list/" + toDoListID, {
        method: 'PUT',
        headers: {
            "Content-type": "application/json"
        },
        body:JSON.stringify(updateObject)
    })
    .then(res => res.json())
    .then(data => console.log(data))
    .catch(err => console.log(err))

}

const deleteList = () => {

    let toDoListID = document.querySelector("#deleteListIDInput").value;

    fetch("http://localhost:8080/list/" + toDoListID, {
        method: 'DELETE',

    })
    .then(response => response.json()
        .then(json => { return json; })
    );
}