<?php
    session_start();

    /*** mysql hostname ***/
    $hostname = 'localhost';

    /*** mysql username ***/
    $username = 'root';

    /*** mysql password ***/
    $password = '';

    $name = addslashes($_GET["name"]);
    $type = $_GET['type'];
    $description = $_GET['desc'];
    $country = $_GET['country'];
    $region = $_GET['region'];

    $conn = new PDO("mysql:host=$hostname;dbname=tripswiki", $username, $password);

    $conn->query("INSERT INTO places (ID, name, type, country, region, username, description) VALUES (null,'$name','$type','$country','$region', '{$_SESSION['username']}', '$description')");

    echo "<p>";
    echo stripslashes($name);
    echo " added to TripsWiki!";
    echo "</p>";

?>