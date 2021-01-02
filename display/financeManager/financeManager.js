

var userInfo = JSON.parse(sessionStorage.getItem("userInfo"));


document.getElementById('financeHome').addEventListener('click',financeHome)
document.getElementById('viewReimbursement').addEventListener('click',viewReimbursement)
document.getElementById('viewAllReimbursement').addEventListener('click',viewAllReimbursement)
document.getElementById('approval').addEventListener('click',viewPendingList)
document.getElementById('financeHome').innerHTML = userInfo.firstName + " " + userInfo.lastName;

function financeHome(){
    if(userInfo == null)  location.href = '../login.html'

    let roleName;
    if(userInfo.roleId == 1){
        roleName = "Finance Manager"
    }else{
        roleName = "Employee"
    }
    document.getElementById('yourName').innerHTML = "  "+ userInfo.firstName + " " + userInfo.lastName;
    
}
async function viewReimbursement(){
    if(userInfo == null)  location.href = '../login.html'
    if(userInfo.roleId == 1){
        try{
            //console.log("userInfo.resolver : "  +userInfo.userId)
                fetch("http://localhost:8080/Project1/allReimb/" + userInfo.userId)
                .then(response => {return response.json()})
                .then(data =>{
                //ID/AUTHOR/AMOUNT/TYPE/SUBMIT(date)/APPROVE(date)/DESCRIPTION/STATUS
                if(data.length != 0){
                    var newTable = "<table class=\"table\"><thead  style=\"text-align:center;\"><tr>"
                    //+ "<th scope=\"col\">ID</th>"
                    + "<th scope=\"col\">APPLICANT</th>"
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
    
                        newTable += "<tr>"//<td>" + data[x].id + "</td>
                                + "<td>" + data[x].authorName +"</td>"
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
    }else{
        location.herf = '../login.html'
    }
    
}

    async function viewAllReimbursement(){
    if(userInfo == null)  location.href = '../login.html'

    if(userInfo.roleId != null && userInfo.roleId == 1){
        try{
            //console.log("userInfo.resolver : "  +userInfo.userId)
                fetch("http://localhost:8080/Project1/allReimb")
                .then(response => {return response.json()})
                .then(data =>{
                //ID/AUTHOR/AMOUNT/TYPE/SUBMIT(date)/APPROVE(date)/DESCRIPTION/STATUS
                if(data.length != 0){
                    var newTable = "<table class=\"table\"><thead  style=\"text-align:center;\"><tr>"
                    //+ "<th scope=\"col\">ID</th>"
                    + "<th scope=\"col\">APPLICANT</th>"
                    + "<th scope=\"col\">AMOUNT</th><th scope=\"col\">TYPE</th><th scope=\"col\">DESCRIPTION</th>"
                    + "<th scope=\"col\">RECEIPT</th><th scope=\"col\">SUBMITTED DATE</th>"
                    + "<th scope=\"col\">APPROVED DATE</th><th scope=\"col\">APPROVAL</th>"
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
    
                        newTable += "<tr>"//<td>" + data[x].id + "</td>"
                                + "<td>" + data[x].authorName +"</td>"
                                + "<td>$" + data[x].amount + "</td><td>" + data[x].typeName +"</td>"
                                + "<td>" + data[x].desc + "</td><td>" + data[x].receipt + "</td>"
                                + "<td>" + data[x].submit + "</td><td>" + data[x].approve +"</td>"
                                + "<td>" + data[x].resolverName + "</td><td>" + data[x].statusName +"</td></tr>"
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
    }else{
        location.href = '../login.html'
    }

   
}

async function OnSelectionChange(opt){
    if(userInfo == null)  location.href = '../login.html'

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
                    //+ "<th scope=\"col\">ID</th>"
                    + "<th scope=\"col\">APPLICANT</th>"
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

                        newTable += "<tr>"//<td>" + data[x].id + "</td>"
                                + "<td>" + data[x].authorName +"</td>"
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


async function OnSelectionChange_All(opt){
    if(userInfo == null)  location.href = '../login.html'

    if(userInfo.roleId != null && userInfo.roleId == 1){
        if(opt.value == 0){
            viewAllReimbursement()
        }else{
            try{
                    fetch("http://localhost:8080/Project1/reimb/" +opt.value)
                    .then(response => {return response.json()})
                    .then(data =>{
                        //ID/AUTHOR/AMOUNT/TYPE/SUBMIT(date)/APPROVE(date)/DESCRIPTION/STATUS/RESOLVER
                    if(data.length !=0){
                        var newTable = "<table class=\"table\"><thead  style=\"text-align:center;\"><tr>"
                        //+ "<th scope=\"col\">ID</th>"
                        + "<th scope=\"col\">APPLICANT</th>"
                        + "<th scope=\"col\">AMOUNT</th><th scope=\"col\">TYPE</th><th scope=\"col\">DESCRIPTION</th>"
                        + "<th scope=\"col\">RECEIPT</th><th scope=\"col\">SUBMITTED DATE</th>"
                        + "<th scope=\"col\">APPROVED DATE</th><th scope=\"col\">APPROVAL</th>"
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
    
                            newTable += "<tr>" // <td>" + data[x].id + "</td>"
                                    + "<td>" + data[x].authorName +"</td>"
                                    + "<td>$" + data[x].amount + "</td><td>" + data[x].typeName +"</td>"
                                    + "<td>" + data[x].desc + "</td><td>" + data[x].receipt + "</td>"
                                    + "<td>" + data[x].submit + "</td><td>" + data[x].approve +"</td>"
                                    + "<td>" + data[x].resolverName + "</td><td>" + data[x].statusName +"</td></tr>"
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
    }else{
        location.href = '../login.html'
    }
   
    
}


async function OnChangeAuth(opt){

    if(opt.value == 1){ //All
        viewPendingList(0)
    }else{  //Assigned
        viewPendingList(userInfo.userId)
    }
    
}

async function viewPendingList(id){
    if(userInfo == null)  location.href = '../login.html'
    if(userInfo.roleId != null && userInfo.roleId == 1){
        try{
            if(id == 0) id="";
            if(id == undefined || id == null) id = userInfo.userId;
            //alert("id: " + id);
            fetch("http://localhost:8080/Project1/reimb/" +id +"1")
            .then(response => {return response.json()})
            .then(data =>{
                //ID/AUTHOR/AMOUNT/TYPE/SUBMIT(date)/APPROVE(date)/DESCRIPTION/STATUS/RESOLVER
            
                if(data.length !=0){
                    var newTable = "<table class=\"table\"><thead  style=\"text-align:center;\"><tr>"
                    //+ "<th scope=\"col\">ID</th>"
                    + "<th scope=\"col\">APPLICANT</th>"
                    + "<th scope=\"col\">AMOUNT</th><th scope=\"col\">TYPE</th><th scope=\"col\">DESCRIPTION</th>"
                    + "<th scope=\"col\">RECEIPT</th><th scope=\"col\">SUBMITTED DATE</th>"
                    + "<th scope=\"col\">STATUS</th><th scope=\"col\">APPROVER</th><th scope=\"col\">APPROVAL</th></tr></thead><tbody>"

                    for(let x = 0;x<data.length;x++){
                        if(data[x].receipt == null){  data[x].receipt = '-' }
                        else{ data[x].receipt = data[x].receipt.substring(12,)}
                    
                        if(data[x].submit == null){  data[x].submit = '-' }
                        else{ data[x].submit = data[x].submit.substring(0,19)}
                        
                        newTable += "<tr>"//<td>" + data[x].id + "</td>"
                                + "<td>" + data[x].authorName +"</td>"
                                + "<td>$" + data[x].amount + "</td><td>" + data[x].typeName +"</td>"
                                + "<td>" + data[x].desc + "</td><td>" + data[x].receipt + "</td>"
                                + "<td>" + data[x].submit + "</td><td id=\"row_sts"+ x + "\">" + data[x].statusName +"</td><td>" + data[x].resolverName + "</td>"
                                + "<td id=\"row_apv"+ x + "\"><button onclick=\"approval("+ data[x].id + "," +2+","+x+")\" class=\"btn btn-lg btn-success\" style=\"font-size: 15px; font-weight: bold; margin-right:2vh; height:37px; width:80px; text-align:center; padding:0\">Approve</button>"
                                + "<button onclick=\"approval("+ data[x].id + "," +3+","+x+")\" class=\"btn btn-lg btn-danger\" style=\"font-size: 15px; font-weight: bold; height:37px; width:80px; text-align:center; padding:0\">Reject</button></td></span>"
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
    }else{
        location.href = '../login.html'
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
        document.getElementById('row_apv'+rowNum).innerHTML = "APPROVED";
        document.getElementById('row_sts'+rowNum).innerHTML = "Approved";
    }else{ //Reject
        document.getElementById('row_apv'+rowNum).innerHTML = "REJECTED";
        document.getElementById('row_sts'+rowNum).innerHTML = "Rejected";
    }
}



async function SSclear(){
    sessionStorage.clear();
}