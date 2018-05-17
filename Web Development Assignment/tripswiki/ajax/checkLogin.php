<?php
    session_start();
    if(isset($_SESSION['username']) && !empty($_SESSION['username'])) 
    {
        if($_SESSION['isAdmin'] == 1)
        {
            echo "admin";
        }
        else
        {
            echo "user";
        }
    }
    else
    {
        //Not logged in
        echo "false";
    }

    
?>