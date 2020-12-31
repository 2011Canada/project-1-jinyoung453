
async function register(e){
    e.preventDefault();

    let username = document.getElementById("userId").value;
    let password = document.getElementById("password").value;
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let email = document.getElementById("email").value;
    let roleId = document.getElementById("position").value;
    let departmentId = document.getElementById("department").value;

    const user = {
        username, password, 
        firstName, lastName,
        email, roleId, departmentId
   }

    try{
        await fetch("http://localhost:8080/Project1/register",{
            method:"PUT",
            body: JSON.stringify(user),
            headers:{
                 "Content-Type" : "application/json"
            }
       })
    alert("YOUR ACCOUNT WAS SUCCESSFULLY CREATED")    
    location.href='../login.html'

    }catch(e){
         console.log(e);
    }
}

document.getElementsByTagName("form")[0].addEventListener('submit',register)
