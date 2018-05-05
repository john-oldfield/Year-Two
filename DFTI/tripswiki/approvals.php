<!DOCTYPE html>
<html>
    <head>
        <title>TripsWiki - The #1 Holiday Planner</title> 
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <script type="text/javascript" src="js/main.js"></script>
    </head>
   <body onload="checkLoginStatus()">
        <div id="wrapper">
            <header>
                <nav>
                    <a href="index.php"><img src="img/logo.png" id="logo"></a>
                    <div id="logoutSection">
                        <?php
                        session_start();
                            if (isset($_SESSION["username"]))
							{
								echo "<p>Logged in as ".$_SESSION['username']."</p><a href='ajax/logout.php'>Logout?</a>";
							}
                        
							else
							{
								echo "<p>You are not logged in.</p> <a href='login.php'>Login?</a>";
							}
                        ?>
                    </div>
                    <ul>
                        <li id="accountLink"><a href="approvals.php" class="current">APPROVALS</a></li>
                        <li id="addLink"><a href="addPoi.php">ADD POI</a></li>
                        <li id="registerLink"><a href="register.php">REGISTER</a></li>
                        <li id="loginLink"><a href="login.php">LOGIN</a></li>
                        <li id="indexLink"><a href="index.php">SEARCH</a></li>
                    </ul>
                </nav>
            </header>
            <div id="approvalList">
                <?php
                    /*** mysql hostname ***/
                    $hostname = 'localhost';

                    /*** mysql username ***/
                    $username = 'root';

                    /*** mysql password ***/
                    $password = '';

                    $conn = new PDO("mysql:host=$hostname;dbname=tripswiki", $username, $password);

                    $results = $conn->query("SELECT name, review, reviews.username, approved, reviews.id FROM reviews, places WHERE reviews.placeID = places.ID AND approved = 0");

                    $row = $results->fetch();

                    if($row == false)
                    {
                        echo "<p id='noReviewsMsg'>There are currently no outstanding reviews to approve.</p>";
                    }
                    else
                    {
                        echo "<table>";
                        echo "<tr>";
                        echo "<th class='placeCol'>Place</th>";
                        echo "<th class='reviewCol'>Review</th>";
                        echo "<th class='submittedCol'>Submitted By</th>";
                        echo "<th class='approveCol'>Approve?</th>";
                        echo "</tr>";
                        while($row)
                            {
                               
                                echo "<tr>";
                                echo "<td>$row[name]</td>";
                                echo "<td>$row[review]</td>";
                                echo "<td><div id='approvedBy'>$row[username]</div></td>";
                                echo "<td><div id='approveOptions'><input type='hidden' value='$row[id]'><a href='' class='tick' onclick='approve(event)'>✔</a><a href='' class='cross' onclick='deleteReview(event)'>✘</a></div></td>";
                                echo "</tr>";

                                echo "<br>";
                                $row = $results->fetch();
                            }
                        echo "</table>";
                    }
                ?>
            </div>
        </div>
    </body>
</html>