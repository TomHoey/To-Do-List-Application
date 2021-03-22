`use strict`

const getTask = () => {

    fetch("http://localhost:8080/task").then(taskResponse => {
        console.log(taskResponse);

        if(response.status !==200) {
            console.error(`status: ${response.status}`)
            return;
        }

        response.json()
            .then(taskData => {
                console.log(taskData);

                let getTasks = document.querySelector(".getTask");
               
                let currentTask = list.querySelector("usabletask");

                if (currentList !=null) {
                    usableTask.remove();
                }

                let t = document.createElement("task");
                task.innerText = JSON.stringify(taskData);
                list.append(t);

            }).catch(err => console.log("There seems to be a bit of an error here judge" + err));
    })

}

const postTask = () => {

    let toDoListID = document.querySelector("#listIDInput").value;
    console.log(toDoListID); 

    let taskName = document.querySelector("#taskNameInput").value;
    console.log(taskName);

    let taskDescription = document.querySelector("#taskDescriptionInput").value;
    console.log(taskDescription);

    const inputResult = {
        "name": taskName,
        "description": taskDescription,
    } 


    fetch("http://localhost:8080/task/" + toDoListID, {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },

        body: JSON.stringify(inputResult)
    })

    .then(res => res.json())
    .then(data => console.log(data))
    .catch(err => console.error(err))

}

const putTask = () => {

    let id = document.querySelector("#updateTaskIDInput").value;
    let taskName = document.querySelector("#updateTaskNameInput").value;
    let taskDescription = document.querySelector("#updateTaskDescriptionInput").value;

    const updateTask = 
    {
        "name": taskName,
        "description": taskDescription,
        
    }


    fetch("http://localhost:8080/task/" + id, {
        method: 'PUT',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(updateTask)
    })
    .then(res => res.json())
    .then(data => console.log(data))
    .catch(err => console.error(err))

}

const deleteTask = () => {

    let id = document.querySelector("#deleteTaskByIDInput").value;

    fetch("http://localhost:8080/task/" + id, {
        method: 'DELETE'
    })

    .then(response => response.json()
    .then(json => {return json;})
    );
}