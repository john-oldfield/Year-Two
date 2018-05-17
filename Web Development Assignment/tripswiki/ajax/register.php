<?php

    /*** mysql hostname ***/
    $hostname = 'localhost';

    /*** mysql username ***/
    $username = 'root';

    /*** mysql password ***/
    $password = '';

    $user = $_GET["username"];
    $pass = $_GET["password"];

    if($user == "" || $pass == "")
    {
        echo "<p>All fields are required.</p>";
    }
    else
    {
        $conn = new PDO("mysql:host=$hostname;dbname=tripswiki", $username, $password);
        $results = $conn->query("SELECT * FROM users WHERE username = '$user'");
        $row = $results->fetch();

        if($row == false)
        {
            session_start();
            $_SESSION["username"] = $user;
            
            $conn->query("INSERT INTO users (ID, username, password, isadmin) VALUES(null, '$user', '$pass', 0)");
            
            echo "<p>Welcome to tripsWiki $user! Redirecting...</p>";
        }
        else
        {
            echo "<p>That username is already in use.</p>";
        }
    }    
?>