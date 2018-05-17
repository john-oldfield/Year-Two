<?php
    
    /*** mysql hostname ***/
    $hostname = 'localhost';

    /*** mysql username ***/
    $username = 'root';

    /*** mysql password ***/
    $password = '';

    $reviewID = $_GET['reviewID'];
    
    $conn = new PDO("mysql:host=$hostname;dbname=tripswiki", $username, $password);
    
    $conn->query("DELETE FROM reviews WHERE id = $reviewID");
             

?>