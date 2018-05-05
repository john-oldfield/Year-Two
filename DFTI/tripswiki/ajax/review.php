<?php
    session_start();
    /*** mysql hostname ***/
    $hostname = 'localhost';

    /*** mysql username ***/
    $username = 'root';

    /*** mysql password ***/
    $password = '';

    $placeID = $_GET["ID"];
    $review = $_GET["rev"];
    $user = $_SESSION["username"];
    $date = date('Y/m/d H:i:s');
    
    $conn = new PDO("mysql:host=$hostname;dbname=tripswiki", $username, $password);
 
    $conn->query("INSERT INTO reviews (ID, placeID, username, review, approved, reviewdate) VALUES (null,'$placeID','$user','$review',0,'$date')");

    echo "<p>Thanks for reviewing! Your review has been submitted for approval by a tripsWiki admin.</p>";
?>