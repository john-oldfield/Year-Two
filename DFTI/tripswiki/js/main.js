// ########## General Javascript ########## //
function logout()
{
    var xhr2 = new XMLHttpRequest();
    xhr2.addEventListener("load", receiveData);
    
    xhr2.open("GET", "ajaxPhp/logout.php?");
    xhr2.send();
    
    document.getElementById("loginLink").style.display = "inline";
    document.getElementById("registerLink").style.display = "inline";
    document.getElementById("accountLink").style.display = "none";  
    document.getElementById("indexLink").style.display = "inline";  
    document.getElementById("addLink").style.display = "none";  
}

function checkLoginStatus()
{
    var xhr2 = new XMLHttpRequest();
    xhr2.addEventListener("load", changeLayout);
    
    xhr2.open("GET", "ajax/checkLogin.php?");
    xhr2.send();
}

//Change Page Links//
function changeLayout(e)
{
    
    if(e.target.responseText === "false")
    {
        console.log("Not Logged In");
        document.getElementById("loginLink").style.display = "inline";
        document.getElementById("registerLink").style.display = "inline";
        document.getElementById("accountLink").style.display = "none";  
        document.getElementById("indexLink").style.display = "inline";  
        document.getElementById("addLink").style.display = "inline"; 

    }
    if(e.target.responseText === 'user')
        {
            console.log("User");
            document.getElementById("loginLink").style.display = "none";
            document.getElementById("registerLink").style.display = "none";
            document.getElementById("accountLink").style.display = "none";  
            document.getElementById("indexLink").style.display = "inline";  
            document.getElementById("addLink").style.display = "inline"; 
        }
    if(e.target.responseText === 'admin')
        {
            console.log("Admin");
            document.getElementById("loginLink").style.display = "none";
            document.getElementById("registerLink").style.display = "none";
            document.getElementById("accountLink").style.display = "inline";  
            document.getElementById("indexLink").style.display = "inline";  
            document.getElementById("addLink").style.display = "inline"; 
        }
}
// ########## Index Page Javascript ########## //
function changeView()
{
    if(document.getElementById('radiolocation').checked)
    {
        document.getElementById("typesearch").style.display="none";
        document.getElementById("locationsearch").style.display="inline";
        document.getElementById("reviewresults").innerHTML = "";
        document.getElementById("typesearch").value = "";
    }
    
    if(document.getElementById('radiotype').checked)
    {
        document.getElementById("reviewresults").innerHTML = "";
        document.getElementById("locationsearch").value = "";
        document.getElementById("locationsearch").style.display="none";
        document.getElementById("typesearch").style.display="inline";
    }
}

function ajaxSearch()
{
    var xhr2 = new XMLHttpRequest();
    xhr2.addEventListener("load", receiveResults);

    var a = document.getElementById("locationsearch").value;

    xhr2.open("GET", "ajax/search.php?location="+a);
    xhr2.send();
}

function ajaxTypeSearch()
{
    var xhr2 = new XMLHttpRequest();
    xhr2.addEventListener("load", receiveResults);

    var b = document.getElementById("typesearch").value;

    xhr2.open("GET", "ajax/typesearch.php?type="+b);
    xhr2.send();
}

//Callback function//
function receiveResults(e)
{
    document.getElementById('reviewresults').innerHTML = e.target.responseText;
} 

function handleReviewDisplay(event) 
{ 
    //Get Div for corresponding button click.
    var x = event.target.parentElement.nextElementSibling;
    
    var divs = document.getElementsByClassName("reviewDiv");
    
    //Hides review sections for other locations.
    for(i = 0; i < divs.length; i++)
    {
        if(divs[i].hasAttribute("style"))
        {
            divs[i].removeAttribute("style");
        }
    }
    
    //Displays Corresponding Div
    x.style.visibility = "visible";
}

function ajaxReview(event)
{
    var a = event.target.previousElementSibling.previousElementSibling.previousElementSibling.value;
    var b = event.target.previousElementSibling.previousElementSibling.value;
    var c = event.target.previousElementSibling;
    var d = event.target.nextElementSibling;
    
    if(b.trim() == "")
    {
        console.log("Textarea is blank.");
        d.style.display = "block";
    }
    else
    {
        var xhr2 = new XMLHttpRequest();
        xhr2.addEventListener("load", showConfirmation(event));

        xhr2.open("GET", "ajax/review.php?ID="+a+"&rev=" + b);
        xhr2.send();
    }    
}

function showConfirmation(event)
{
    event.target.previousElementSibling.previousElementSibling.style.display = "none";
    event.target.style.display = "none";
    event.target.nextElementSibling.style.display = "none";
    event.target.nextElementSibling.nextElementSibling.style.display = "block";
}

//Callback function//
function receiveReviewResults(e)
{
    document.getElementById('reviewMsg').innerHTML = e.target.responseText;
}

function hideConfirmationMsg()
{
    //Reset message if function ran again//   
    document.getElementById("content1").innerHTML = "";
}                          

// ########## Add Page Javascript ########## //
function change()
{
    document.getElementById("select").style.color = "black";
}

function validateAddPageFields()
{
    //Variables to get values//
    var a = document.getElementById("name").value;
    var b = document.getElementById("select").selectedIndex;
    var c = document.getElementById("country").value;
    var d = document.getElementById("region").value;
    var ef = document.getElementById("desc").value;

    //Variables to check that all fields are valid//
    var aok= "false";
    var bok= "false";
    var cok= "false";
    var dok= "false";
    var eok= "false";

    if(a.trim() == "")
    {
        document.getElementById('error').innerHTML = "All Fields Required.";
        document.getElementById("name").style.border = " solid red 1px";
        var aok = "false";
        console.log("1: "+aok);
    }
    else
    {
        var aok = "true";
        document.getElementById("name").style.border = "";
        console.log("1: "+aok);
    }

    if(b == 0)
    {
        document.getElementById('error').innerHTML = "All Fields Required.";
        document.getElementById("select").style.border = " solid red 1px";
        var bok = "false";
        console.log("2: "+bok);
    }
    else
    {
        var bok = "true";
        document.getElementById("name").style.border = "";
        console.log("2: "+bok);
    }

    if(c.trim() == "")
    {
        document.getElementById('error').innerHTML = "All Fields Required.";
        document.getElementById("desc").style.border = " solid red 1px";
        var cok = "false";
        console.log("3: "+cok);
    }
    else
    {
        var cok = "true";
        document.getElementById("desc").style.border = "";
        console.log("3: "+cok);
    }

    if(d.trim() == "")
    {
        document.getElementById('error').innerHTML = "All Fields Required.";
        document.getElementById("country").style.border = " solid red 1px";
        var dok = "false";
        console.log("4: "+dok);
    }
    else
    {
        var dok = "true";
        document.getElementById("name").style.border = "";
        console.log("4: "+dok);
    }

    if(ef.trim() == "")
    {
        document.getElementById('error').innerHTML = "All Fields Required.";
        document.getElementById("region").style.border = " solid red 1px";
        var eok = "false";
        console.log("5: "+eok);
    }
    else
    {
        var eok = "true";
        document.getElementById("name").style.border = "";
        console.log("5: "+eok);
    }

    if(aok == "true" && bok == "true" && cok == "true" && dok == "true" && eok == "true")
    {
        ajaxAddRequest();
        document.getElementById('error').innerHTML = "";
        console.log("Validated");
    }
    else
    {
        console.log("Not validated.");
    }
}

function ajaxAddRequest()
{
    var xhr2 = new XMLHttpRequest();
    xhr2.addEventListener("load", receiveAddData);

    var a = document.getElementById("name").value;
    var b = document.getElementById("select").value;
    var c = document.getElementById("desc").value;
    var d = document.getElementById("country").value;
    var ef = document.getElementById("region").value;

    xhr2.open("GET", "ajax/add.php?name=" + a + "&type=" + b + "&desc=" + c + "&country=" + d + "&region=" + ef);
    xhr2.send();
}

//Callback function//
function receiveAddData(e)
{
    document.getElementById('content1').innerHTML = e.target.responseText;
}

// ########## Login Page Javascript ########## //
function validateLogin()
{
    var a = document.getElementById("username").value;
    var b = document.getElementById("password").value;
    
    if(a.trim() == "")
    {
        document.getElementById('error').innerHTML = "All Fields Required.";
        document.getElementById("username").style.border = " solid red 1px";           
    }
    else
    {
        document.getElementById("username").style.border = "";
        
        if(b.trim() == "")
        {
            document.getElementById('error').innerHTML = "All Fields Required.";
            document.getElementById('password').style.border = "solid red 1px";           
        }
        else
        {
            document.getElementById("password").style.border = "";
            document.getElementById('error').innerHTML = "";
            //Runs Login Function//
            login();
        }
    }
}

function login()
{
    var xhr2 = new XMLHttpRequest();
    xhr2.addEventListener("load", receiveLoginData);

    var a = document.getElementById("username").value;
    var b = document.getElementById("password").value;

    xhr2.open("GET", "ajax/handleLogin.php?username=" + a + "&password=" + b);
    xhr2.send();
}

//Callback function//
function receiveLoginData(e)
{
    if(e.target.responseText == 1)
        {
            document.getElementById('successmsg').innerHTML = "Invalid Username or Password";
        }
    else
        {
            document.getElementById('successmsg').innerHTML = e.target.responseText;
            setTimeout('redirectIndex()', 3000);
        }
}

function redirectIndex()
{
     window.location = "index.php";  
}

// ########## Register Page Javascript ########## //
function ajaxRegister()
{
    var xhr2 = new XMLHttpRequest();
    xhr2.addEventListener("load", receiveRegisterData);

    var a = document.getElementById("username").value;
    var b = document.getElementById("password").value;

    xhr2.open("GET", "ajax/register.php?username=" + a + "&password=" + b);
    xhr2.send();
}

function receiveRegisterData(e)
{
    document.getElementById('successmsg').innerHTML = e.target.responseText;
    setTimeout('redirectIndex()', 3000);
}
// ########## Approval Page Javascript ########## //
function approve(event)
{    
    var xhr2 = new XMLHttpRequest();
    xhr2.addEventListener("load", receiveApproveData(event));
    
    var a = event.target.previousSibling.value;
    
    xhr2.open("GET", "ajax/approve.php?reviewID=" + a);
    xhr2.send();   
}

function deleteReview(event)
{
    var a = event.target.previousSibling.previousSibling.value;
    var c = event.target.previousSibling.previousSibling;
    var b = c.parentElement.parentElement.parentElement;
    var xhr2 = new XMLHttpRequest();
    xhr2.addEventListener("load", receiveApproveData(event, b));
    
    xhr2.open("GET", "ajax/deleteReview.php?reviewID=" + a);
    xhr2.send(); 
}

function receiveApproveData(event, b)
{
    b.parentElement.removeChild(b);
}
