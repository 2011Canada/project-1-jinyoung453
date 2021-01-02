function redirect(user){
   alert("redirect")
   
    sessionStorage.setItem("userInfo",JSON.stringify(user));
    var role=user.role;

    if(role==1){
        location.href='../employee/empMain.html'
    }
    else{
        location.href='../financeManager/fmMain.html'
    }
}

async function loadDoc(e) {
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
         console.log(user)
        // if(username=='aharris'&&password=='12345'){
        //     location.href='../employeePage/employee.html';
        // }
         redirect(user);
         
    }catch(e){
         alert("USER DOES NOT EXIST OR PASSWORD IS NOT CORRECT")
         location.href='login.html'
         console.log(e);
    }
}
   

// function loadDoc(){
//     alert("loadDOc")
//     //console.log("First inside");
//     var xhttp=new XMLHttpRequest();
//     xhttp.onreadystatechange=function(){
//         alert("here")
//     let res = xhttp.open('POST','http://localhost:8080/Project1/login')
        
//         alert("Second inside");
//         //if(this.readyState==4&&this.status==200){
//            //alert("Third inside");
//             var user= res.JSON();
//             redirect(user);
//        // }
//     };
    
// xhttp.send();
// }
document.getElementsByTagName("form")[0].addEventListener('submit',loadDoc);
