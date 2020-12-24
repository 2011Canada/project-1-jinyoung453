
// document.getElementById('logbtn').addEventListener("click",() => {
//      console.log("logbtn");
//     var userId = document.getElementById('userId').value;
//     if(userId == 'AB'){
//         location.href='employee/empDisp.html'
//    } else if(userId == 'f'){
//         location.href='financeManager/fmDisp.html'
//    };
// });



function redirectPage(user){
     var roldId = user.roleId;
     if(roldId == 1){
          location.href='employee/empDisp.html'
     } else{
          location.href='financeManager/fmDisp.html'
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
          let res = await fetch("http://localhost:8080/Servlet/login",{
               method:"POST",
               body: JSON.stringify(credentials),
               headers:{
                    "Content-Type" : "application/json"
               }
          })

          let user = await res.json()
          console.log(user);
          /*
               email: "ba@aa.aa"
               firstName: "alec"
               lastName: "Batson"
               password: "a"
               roleId: 1
               userId: 1
               username: "AB"
          */
          redirectPage(user);
     }catch(e){
          console.log(e);
     }
}

document.getElementsByTagName("form")[0].addEventListener('submit',loginSubmit)