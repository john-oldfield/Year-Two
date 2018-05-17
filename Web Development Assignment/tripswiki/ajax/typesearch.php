<?php
    session_start();
    /*** mysql hostname ***/
    $hostname = 'localhost';

    /*** mysql username ***/
    $username = 'root';

    /*** mysql password ***/
    $password = '';

    $type = $_GET['type'];

    $conn = new PDO("mysql:host=$hostname;dbname=tripswiki", $username, $password);
    
    $results = $conn->query("SELECT * FROM places WHERE type='$type'");

    $row = $results->fetch();

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
                echo "<p>";
                    echo "<h3>$row[name]</h3> <br>";
                    echo "<label class='resultLabel'>Type:</label><br> $row[type] <br>";
                    echo "<label class='resultLabel'>Description: </label><br>$row[description] <br>";
                    echo "<label class='resultLabel'>Added by: </label><br>$row[username] <br>";
                    echo "<label class='resultLabel'>Country: </label><br>$row[country] <br>";
                    echo "<label class='resultLabel'>Region: </label><br>$row[region] <br>";
                    echo "<label class='resultLabel'>Rate this place: </label><br>";
                echo"</p>";
                
                echo "<a class='star'><img src='img/star.png'></a>";
                echo "<a class='star'><img src='img/star.png'></a>";
                echo "<a class='star'><img src='img/star.png'></a>";
                echo "<a class='star'><img src='img/star.png'></a>";
                echo "<a class='star'><img src='img/star.png'></a>";
                echo "<a class='star'><img src='img/star.png'></a>";
                echo "<a class='star'><img src='img/star.png'></a>";
                echo "<a class='star'><img src='img/star.png'></a>";
                echo "<a class='star'><img src='img/star.png'></a><br>";
                
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
?>