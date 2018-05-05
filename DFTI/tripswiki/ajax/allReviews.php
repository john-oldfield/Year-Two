<?php
    session_start();
    /*** mysql hostname ***/
    $hostname = 'localhost';

    /*** mysql username ***/
    $username = 'root';

    /*** mysql password ***/
    $password = '';

    $id = $_GET['type'];

    $conn = new PDO("mysql:host=$hostname;dbname=tripswiki", $username, $password);
    
    $results = $conn->query("SELECT * FROM places WHERE ID='$id'");

    $row = $results->fetch();

    if($row == false)
        {
            echo "<p>No Reviews for this place. <a href='#'>Why not make one?</a></p>";
        }
        else
        {
            while($row)
            {
                
                
                $row = $results->fetch();
                die();
            }
        }
?>