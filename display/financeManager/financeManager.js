

var userInfo = JSON.parse(sessionStorage.getItem("userInfo"));

document.getElementById('financeHome').addEventListener('click',financeHome)
document.getElementById('viewReimbursement').addEventListener('click',viewReimbursement)
document.getElementById('approval').addEventListener('click',viewPendingList)

function financeHome(){
    let roleName;
    if(userInfo.roleId == 1){
        roleName = "Finance Manager"
    }else{
        roleName = "Employee"
    }
    var fmInfo  = " User ID: " + userInfo.username + "<br>"
                 + " First Name: " + userInfo.firstName + "<br>"
                 + " Last Name: " + userInfo.lastName + "<br>"
                 + " Email: " + userInfo.email + "<br>"
                 + " Position: " + roleName + "<br>";
    
    document.getElementById('fmBody').innerHTML = fmInfo;
}
async function viewReimbursement(){
    //alert("viewReimbursement")
    try{
        //console.log("userInfo.resolver : "  +userInfo.userId)
         fetch("http://localhost:8080/Project1/allReimb/" + userInfo.userId)
         .then(response => {return response.json()})
         .then(data =>{
            //ID/AUTHOR/AMOUNT/TYPE/SUBMIT(date)/APPROVE(date)/DESCRIPTION/STATUS
            if(data.length != 0){
                var newTable = "<table class=\"table\"><thead  style=\"text-align:center;\"><tr>"
                + "<th scope=\"col\">ID</th><th scope=\"col\">APPLICANT</th>"
                + "<th scope=\"col\">AMOUNT</th><th scope=\"col\">TYPE</th><th scope=\"col\">DESCRIPTION</th>"
                + "<th scope=\"col\">RECEIPT</th><th scope=\"col\">SUBMITTED DATE</th>"
                + "<th scope=\"col\">APPROVED DATE</th>"
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

                    newTable += "<tr><td>" + data[x].id + "</td><td>" + data[x].authorName +"</td>"
                            + "<td>$" + data[x].amount + "</td><td>" + data[x].typeName +"</td>"
                            + "<td>" + data[x].desc + "</td><td>" + data[x].receipt + "</td>"
                            + "<td>" + data[x].submit + "</td><td>" + data[x].approve +"</td>"
                            + "<td>" + data[x].statusName +"</td></tr>"
                }
                newTable += "</tbody></table>"
                document.getElementById('fmBody').innerHTML = newTable;
            }else{
                document.getElementById('fmBody').innerHTML = "NO DATA EXIST";
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
             fetch("http://localhost:8080/Project1/reimb/" +userInfo.userId +opt.value)
             .then(response => {return response.json()})
             .then(data =>{
                 //ID/AUTHOR/AMOUNT/TYPE/SUBMIT(date)/APPROVE(date)/DESCRIPTION/STATUS/RESOLVER
                if(data.length !=0){
                    var newTable = "<table class=\"table\"><thead  style=\"text-align:center;\"><tr>"
                    + "<th scope=\"col\">ID</th><th scope=\"col\">APPLICANT</th>"
                    + "<th scope=\"col\">AMOUNT</th><th scope=\"col\">TYPE</th><th scope=\"col\">DESCRIPTION</th>"
                    + "<th scope=\"col\">RECEIPT</th><th scope=\"col\">SUBMITTED DATE</th>"
                    + "<th scope=\"col\">APPROVED DATE</th>"
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

                        newTable += "<tr><td>" + data[x].id + "</td><td>" + data[x].authorName +"</td>"
                                + "<td>$" + data[x].amount + "</td><td>" + data[x].typeName +"</td>"
                                + "<td>" + data[x].desc + "</td><td>" + data[x].receipt + "</td>"
                                + "<td>" + data[x].submit + "</td><td>" + data[x].approve +"</td>"
                                + "<td>" + data[x].statusName +"</td></tr>"
                    }
                    newTable += "</tbody></table>"
                    document.getElementById('fmBody').innerHTML = newTable;
                }else{
                    document.getElementById('fmBody').innerHTML = "NO DATA EXIST";
                }
             })
        }catch(e){
             console.log(e);
        }
    }
    
}

async function viewPendingList(){
    //alert("viewPendingList")
    try{
            fetch("http://localhost:8080/Project1/reimb/" +userInfo.userId +"1")
            .then(response => {return response.json()})
            .then(data =>{
                //ID/AUTHOR/AMOUNT/TYPE/SUBMIT(date)/APPROVE(date)/DESCRIPTION/STATUS/RESOLVER
            
                if(data.length !=0){
                    var newTable = "<table class=\"table\"><thead  style=\"text-align:center;\"><tr>"
                    + "<th scope=\"col\">ID</th><th scope=\"col\">APPLICANT</th>"
                    + "<th scope=\"col\">AMOUNT</th><th scope=\"col\">TYPE</th><th scope=\"col\">DESCRIPTION</th>"
                    + "<th scope=\"col\">RECEIPT</th><th scope=\"col\">SUBMITTED DATE</th>"
                    + "<th scope=\"col\">APPROVED DATE</th>"
                    + "<th scope=\"col\">STATUS</th><th scope=\"col\">APPROVAL</th></tr></thead><tbody>"

                    for(let x = 0;x<data.length;x++){
                        if(data[x].receipt == null){  data[x].receipt = '-' }
                        else{ data[x].receipt = data[x].receipt.substring(12,)}
                    
                        if(data[x].submit == null){  data[x].submit = '-' }
                        else{ data[x].submit = data[x].submit.substring(0,19)}
                        
                        if(data[x].approve == null){  data[x].approve = '-' }
                        else{ data[x].approve = data[x].approve.substring(0,19)}

                        newTable += "<tr><td>" + data[x].id + "</td><td>" + data[x].authorName +"</td>"
                                + "<td>$" + data[x].amount + "</td><td>" + data[x].typeName +"</td>"
                                + "<td>" + data[x].desc + "</td><td>" + data[x].receipt + "</td>"
                                + "<td>" + data[x].submit + "</td><td>" + data[x].approve +"</td>"
                                + "<td>" + data[x].statusName +"</td>"
                                + "<td id=\"row_"+ x + "\"><button onclick=\"approval("+ data[x].id + "," +2+","+x+")\" class=\"btn btn-lg btn-success\" style=\"margin-right:2vh\">Approve</button>"
                                + "<button onclick=\"approval("+ data[x].id + "," +3+","+x+")\" class=\"btn btn-lg btn-danger\" >Reject</button></td></span>"
                                + "</tr>"
                    }
                    newTable += "</tbody></table>"
                    document.getElementById('fmBody').innerHTML = newTable;
                }else{
                    document.getElementById('fmBody').innerHTML = "NEW REIMBURSEMENT DOES NOT EXIST";
                }
            })
    }catch(e){
            console.log(e);
    }
}

function approval(reimbId, statusId, rowNum){
    console.log("reimbId: " + reimbId + ", userId: " + userInfo.userId + ", rowNum: " + rowNum);
    fetch("http://localhost:8080/Project1/approval/"+statusId,{
            method:"PUT",
            body: JSON.stringify({'resolver' : userInfo.userId, 'id': reimbId}),
            headers:{
                 "Content-Type" : "application/json"
            }
    })
    if(statusId == 2){ //Approve
        document.getElementById('row_'+rowNum).innerHTML = "APPROVED";
    }else{ //Reject
        document.getElementById('row_'+rowNum).innerHTML = "REJECTED";
    }
}


/*
async function userList(){
    try{
        
        var newTable = "<table><tr><th>User ID</th><th>First Name</th><th>Last Name</th><th>Email</th></tr>"
         fetch("http://localhost:8080/Project1/users")
         .then(response => {return response.json()})
         .then(user =>{
             
            for(let x = 0;x<user.length;x++){
                newTable += "<tr><td>" + user[x].username + "</td><td>" + user[x].firstName + "</td><td>" + user[x].lastName + "</td><td>" + user[x].email + "</td></tr>"
            }
            newTable += "</table>"
            document.getElementById('fmBody').innerHTML = newTable;
         })
         
        
    }catch(e){
         console.log(e);
    }
}

*/
