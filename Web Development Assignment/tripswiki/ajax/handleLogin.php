<?php

    /*** mysql hostname ***/
    $hostname = 'localhost';

    /*** mysql username ***/
    $username = 'root';

    /*** mysql password ***/
    $password = '';

    $conn = new PDO("mysql:host=$hostname;dbname=tripswiki", $username, $password);

    $user = $_GET['username'];
    $pass = $_GET['password'];
    
    $results = $conn->query("SELECT * FROM users WHERE username='$user' AND password='$pass'");

    $row = $results->fetch();
    
    if($row == false)
    {
        echo 1;
    }
    else
    {
        session_start();
        $_SESSION["username"] = $user;
        $_SESSION["isAdmin"] = $row['isadmin'];
        
        if(isset($_SESSION['username']) && !empty($_SESSION['username'])) 
        {
            echo "Logged in as ".$_SESSION['username'].". Redirecting...";
        }
        else
        {
            echo false;
        }
    }
?>