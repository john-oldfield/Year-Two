<!DOCTYPE html>
<html>
    <head>
        <title>TripsWiki - The #1 Holiday Planner</title> 
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
    </head>
   <body onload="checkLoginStatus()">
        <div id="wrapper">
            <header>
                <nav>
                    <a href="index.php"><img src="img/logo.png" id="logo" alt="logo"></a>
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
                        <li id="accountLink"><a href="approvals.php">APPROVALS</a></li>
                        <li id="addLink"><a href="addPoi.php" class="current">ADD POI</a></li>
                        <li id="registerLink"><a href="register.php">REGISTER</a></li>
                        <li id="loginLink"><a href="login.php">LOGIN</a></li>
                        <li id="indexLink"><a href="index.php">SEARCH</a></li>
                    </ul>
                </nav>
            </header>
            <?php
                if(!isset($_SESSION['username']))
                {
                    echo "<p id='addError'>Here at tripsWiki we need to know who is adding places of interest therefore you must be logged in to add places of interest.<br><a href='register.php' id='noAccountLink'>Don't have an account?</a></p>";
                    echo "</div>";
                }
                else
                {
                    echo "<div id='add'>";
                    echo    "<h1>Add a Point of Interest!</h1>";
                    echo    "<input name='name' type='text' class='jtxt' placeholder='POI Name' id='name' onfocus='hideConfirmationMsg()'><br><br>";
                    echo    "<select id='select' name='type' onchange='change()'>";
                    echo    "<option value='1' class='unselectable'disabled selected>Select POI Type:</option>";
                    echo    "<option value='Hotel' class='selectable'>Hotel</option>";
                    echo    "<option value='Historical Site' class='selectable'>Historical Site</option>";
                    echo    "<option value='Resteraunt' class='selectable'>Restaurant</option>";
                    echo    "<option value='Bar' class='selectable'>Bar</option>";
                    echo    "<option value='Nightclub' class='selectable'>Nightclub</option>";
                    echo    "<option value='Landmark' class='selectable'>Landmark</option>";
                    echo    "<option value='Sporting Venue' class='selectable'>Sporting Venue</option>";
                    echo    "</select><br><br>";
                    echo    "<input type='text' class='jtxt' placeholder='Country' name='Country' id='country'><br><br>";
                    echo    "<input type='text' class='jtxt' placeholder='Region' name='region' id='region'><br><br>";
                    echo    "<textarea placeholder='Enter your POI description here...' name='desc' id='desc' maxlength='200'></textarea>";
                    echo "<br><br>";
                    echo "<div id='error'></div>";
                    echo "<input type='submit' value='Add!' class='btn' onclick='validateAddPageFields()'>";
                    echo "<div id='content1'></div>";
                    echo "</div>";
                }
            ?>
            
        </div>
    </body>
</html>