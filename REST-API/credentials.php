<?php
define('DB_SERVER', 'localhost');
define('DB_USERNAME', ''); //Omitted
define('DB_PASSWORD', ''); //Omitted
define('DB_NAME', 'id15060887_myfitnessbuddy');
$con = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_NAME);
$con->set_charset('utf8mb4');
if ($con === false) {
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
?>
