

function redirectPage(user){
    
     sessionStorage.setItem("userInfo", JSON.stringify(user))

     var roldId = user.roleId;
     if(roldId == 1){
          location.href='financeManager/fmMain.html'
     } else{
          location.href='employee/empMain.html'
     }
}

async function loginSubmit(e) {
     e.preventDefault();
     
     let username = document.getElementById("userId").value;
     let password = document.getElementById("password").value;

     const credentials = {
          username,
          password:password
     }

     try{
          let res = await fetch("http://localhost:8080/Project1/login",{
               method:"POST",
               body: JSON.stringify(credentials),
               headers:{
                    "Content-Type" : "application/json"
               }
          })

          let user = await res.json()
          redirectPage(user);
          
     }catch(e){
          alert("USER DOES NOT EXIST OR PASSWORD IS NOT CORRECT")
          location.href='login.html'
          console.log(e);
     }
}

document.getElementsByTagName("form")[0].addEventListener('submit',loginSubmit)