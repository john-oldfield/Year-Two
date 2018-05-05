<?php
    header( 'Location: http://localhost/tripswiki/login.php' ) ;
    session_start();
    session_unset();
    session_destroy();
?>