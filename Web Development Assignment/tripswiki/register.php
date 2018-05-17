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
                        <li id="accountLink"><a href="approvals.php">APPROVALS</a></li>
                        <li id="addLink"><a href="addPoi.php">ADD POI</a></li>
                        <li id="registerLink"><a href="register.php" class="current">REGISTER</a></li>
                        <li id="loginLink"><a href="login.php">LOGIN</a></li>
                        <li id="indexLink"><a href="index.php">SEARCH</a></li>
                    </ul>
                </nav>
            </header>
                <div id="register">
                    <h1>Register with tripswiki!</h1>
                    <input type="text" name="user" placeholder="Username" class="jtxt" id="username"><br>
                    <input type="password" name="user" placeholder="Password" class="jtxt" id="password"><br>
                    <input type="submit" value="Register!" class="btn" onclick="ajaxRegister()">
                </div>
                <div id="successmsg"></div>
            </div>
    </body>
</html>