
var userInfo = JSON.parse(sessionStorage.getItem("userInfo"));

document.getElementById('empHome').addEventListener('click',empHome)
document.getElementById('history').addEventListener('click',viewHistory)

function empHome(){
    let roleName;
    if(userInfo.roleId == 1){
        roleName = "Finance Manager"
    }else{
        roleName = "Employee"
    }
    var empInfo  = " User ID: " + userInfo.username + "<br>"
                 + " First Name: " + userInfo.firstName + "<br>"
                 + " Last Name: " + userInfo.lastName + "<br>"
                 + " Email: " + userInfo.email + "<br>"
                 + " Position: " + roleName + "<br>";
    
    document.getElementById('empBody').innerHTML = empInfo;
}

async function viewHistory(){
    //alert("history")
    try{
        var newTable = "<table class=\"table\"><thead  style=\"text-align:center;\"><tr>"
                    + "<th scope=\"col\">ID</th>"
                    + "<th scope=\"col\">AMOUNT</th><th scope=\"col\">TYPE</th>"
                    + "<th scope=\"col\">DESCRIPTION</th>"
                    + "<th scope=\"col\">RECEIPT</th>"
                    + "<th scope=\"col\">SUBMITTED DATE</th>"
                    + "<th scope=\"col\">APPROVED DATE</th>"
                    + "<th scope=\"col\">APPROVER</th>"
                    + "<th scope=\"col\">STATUS</th></tr></thead><tbody>"

            fetch("http://localhost:8080/Project1/viewHistory/"+userInfo.userId)
            .then(response => {return response.json()})
            .then(data =>{
                if(data.length !=0){
                    for(let x = 0;x<data.length;x++){
                        if(data[x].approve == null){
                            data[x].approve = '-'
                        }
                        if(data[x].receipt == null){  data[x].receipt = '-' }
                        else{ data[x].receipt = data[x].receipt.substring(12,)}
                   
                        if(data[x].submit == null){  data[x].submit = '-' }
                        else{ data[x].submit = data[x].submit.substring(0,19)}
                        
                        if(data[x].approve == null){  data[x].approve = '-' }
                        else{ data[x].approve = data[x].approve.substring(0,19)}

                        newTable += "<tr><td>" + data[x].id + "</td>"
                                + "<td> $" + data[x].amount + "</td><td>" + data[x].typeName +"</td>"
                                + "<td>" + data[x].desc + "</td><td>"+ data[x].receipt +"</td>"
                                + "<td>" + data[x].submit + "</td><td>" + data[x].approve +"</td>"
                                + "<td>" + data[x].resolverName + "</td>"
                                + "<td>" + data[x].statusName +"</td></tr>"
                    }
                    newTable += "</tbody></table>"
                    document.getElementById('empBody').innerHTML = newTable;
                }else{
                    document.getElementById('empBody').innerHTML = "NO DATA EXIST";
                }
                
            })
    }catch(e){
        console.log(e);
    }
}


async function OnSelectionChange(opt){

    if(opt.value == 0){
        viewReimbursement()
    }else{
        try{
             fetch("http://localhost:8080/Project1/viewHistoryBy/" +userInfo.userId +opt.value)
             .then(response => {return response.json()})
             .then(data =>{
                if(data.length !=0){
                    var newTable = "<table class=\"table\"><thead  style=\"text-align:center;\"><tr>"
                    + "<th scope=\"col\">ID</th>"
                    + "<th scope=\"col\">AMOUNT</th><th scope=\"col\">TYPE</th>"
                    + "<th scope=\"col\">DESCRIPTION</th>"
                    + "<th scope=\"col\">RECEIPT</th>"
                    + "<th scope=\"col\">SUBMITTED DATE</th>"
                    + "<th scope=\"col\">APPROVED DATE</th>"
                    + "<th scope=\"col\">APPROVER</th>"
                    + "<th scope=\"col\">STATUS</th></tr></thead><tbody>"
                    
                    for(let x = 0;x<data.length;x++){
                        if(data[x].approve == null){
                            data[x].approve = '-'
                        }
                        if(data[x].receipt == null){  data[x].receipt = '-' }
                        else{ data[x].receipt = data[x].receipt.substring(12,)}
                   
                        if(data[x].submit == null){  data[x].submit = '-' }
                        else{ data[x].submit = data[x].submit.substring(0,19)}
                        
                        if(data[x].approve == null){  data[x].approve = '-' }
                        else{ data[x].approve = data[x].approve.substring(0,19)}

                        newTable += "<tr><td>" + data[x].id + "</td>"
                        + "<td> $" + data[x].amount + "</td><td>" + data[x].typeName +"</td>"
                        + "<td>" + data[x].desc + "</td><td>"+ data[x].receipt +"</td>"
                        + "<td>" + data[x].submit + "</td><td>" + data[x].approve +"</td>"
                        + "<td>" + data[x].resolverName + "</td>"
                        + "<td>" + data[x].statusName +"</td></tr>"
                    }
                    newTable += "</tbody></table>"
                    document.getElementById('empBody').innerHTML = newTable;
                }else{
                    document.getElementById('empBody').innerHTML = "NO DATA EXIST";
                }
             })
        }catch(e){
             console.log(e);
        }
    }
    
}


function newReimbursement(e){
    //alert("newReimbursement")
    //preventDefault();

    let amount = document.getElementById("amount").value;
    let desc = document.getElementById("description").value;
    let author = userInfo.userId; //UserId
    let resolver = document.getElementById("approver").value;
    let type = document.getElementById("type").value;
    let receipt= document.getElementById("file").value;
    
    const reimb = {
        amount, desc, author, resolver, type, receipt
   }

    try{
        fetch("http://localhost:8080/Project1/newReimb",{
            method:"PUT",
            body: JSON.stringify(reimb),
            headers:{
                 "Content-Type" : "application/json"
            }
       })
    alert("NEW REIMBURSEMENT WAS SUCCESSFULLY SUBMITTED")    
    location.href='history.html'

    }catch(e){
         console.log(e);
    }
}

function approverList(){
    try{
        var optionList = ""
        fetch("http://localhost:8080/Project1/approver")
            .then(response => {return response.json()})
            .then(data =>{
                for(let x = 0;x<data.length;x++){
                    optionList += "<option value=\'"+ data[x].userId +"\'>"+ data[x].userName+"</option>"
                }
                document.getElementById('approver').innerHTML = optionList;
            })
    }catch(e){
        console.log(e)
    }
}

