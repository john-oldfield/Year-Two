<?php
    session_start();

    /*** mysql hostname ***/
    $hostname = 'localhost';

    /*** mysql username ***/
    $username = 'root';

    /*** mysql password ***/
    $password = '';

    $location = $_GET['location'];

    $conn = new PDO("mysql:host=$hostname;dbname=tripswiki", $username, $password);
    
    $results = $conn->query("SELECT * FROM places WHERE region LIKE '$location%' OR country LIKE '$location%'");

    $row = $results->fetch();

    if($location=="")
    {
        echo "";
    }

    else
    {
        if($row == false)
        {
            echo "<p>No Results Found.</p>";
        }
        else
        {
            while($row)
            {
                echo "<div id='main'>";
                echo "<div id='resultInfo'>";
                    echo "<h3>$row[name]</h3>";
                    echo "<p><label class='resultLabel'>Type:</label> $row[type] </p>";
                    echo "<p><label class='resultLabel'>Description: </label><br>$row[description] </p>";
                    echo "<p><label class='resultLabel'>Added by: </label>$row[username] </p>";
                    echo "<p><label class='resultLabel'>Country: </label>$row[country] </p>";
                    echo "<p><label class='resultLabel'>Region: </label>$row[region] </p>";
                    echo "<p><label class='resultLabel'>Rate this place: </label></p>";
                    
                    echo "<div id='starContainer'>";
                    echo "<img src=img/star.png value= '1' id='star'>";
                    echo "<img src=img/star.png value= '2'>";
                    echo "<img src=img/star.png value= '3'>";
                    echo "</div>";
                    echo "<p>Rated By: $row[num_raters] Average Rating: $row[av_rating]</p>";
                                
                echo "<input type='button' id='reviewBtn' value='Review this Place' onclick='handleReviewDisplay(event)'>";
                echo "<input type='button' id='viewReviewsBtn' value='View All Reviews'><br>";
                echo "</div>";
                
                echo "<div class='reviewDiv'>";
                if (isset($_SESSION["username"]))
                {
                    echo "<input type='hidden' value='$row[ID]'>";
                    echo "<textarea id='reviewArea' placeholder='Write your review here!' maxlength='500'></textarea><br>";
                    echo "<input type='button' id='submitReviewBtn' value='Submit Review' onclick='ajaxReview(event)'>";
                    echo "<p id='blankReview'>Description cannot be blank.</p>";
                    echo "<p id='reviewMsg'>Thank you for reviewing $row[name]. Your review has been submitted for approval by a tripsWiki admin.</p>";
                    
                }
                else
                {
                    echo "<div id='notLoggedInMsg'><p>You must be logged in to submit a review.</p><a href='login.php'>Login?</a></div>";
                }
                echo "</div>";
                echo "</div>";
                
                $row = $results->fetch();
            }
        }
    }   
?>