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
                        <li id="accountLink"><a href="approvals.php">APPROVALS</a></li>
                        <li id="addLink"><a href="addPoi.php">ADD POI</a></li>
                        <li id="registerLink"><a href="register.php">REGISTER</a></li>
                        <li id="loginLink"><a href="login.php">LOGIN</a></li>
                        <li id="indexLink"><a href="index.php" class="current">SEARCH</a></li>
                    </ul>
                    
                </nav>
            </header>
            <div id="reviewSearch">
                <h1>Search & Review a Point of Interest</h1>
                <input type="radio" name="select" id="radiolocation" onclick="changeView()" checked><label>Search by location</label>
                <input type="radio" name="select" id="radiotype" onclick="changeView()"><label>Search by type</label><br>
                <input type="text" placeholder="Enter region or country..." id="locationsearch" onkeyup="ajaxSearch()">    
                <select id="typesearch" onchange="ajaxTypeSearch()">
                    <option value="" disabled selected>Select POI Type:</option>
                    <option value="Hotel" class="selectable">Hotel</option>
                    <option value="Historical Site" class="selectable">Historical Site</option>
                    <option value="Restaurant" class="selectable">Restaurant</option>
                    <option value="Bar" class="selectable">Bar</option>
                    <option value="Nightclub" class="selectable">Nightclub</option>
                    <option value="Landmark" class="selectable">Landmark</option>
                    <option value="Sporting Venue" class="selectable">Sporting Venue</option>
                </select>
            </div>
            <div id="reviewresults"></div>
        </div>
    </body>
</html>