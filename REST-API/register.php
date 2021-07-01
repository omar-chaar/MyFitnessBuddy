<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];
    $password = password_hash($password, PASSWORD_DEFAULT);
    require_once 'credentials.php';
    $sql = "INSERT INTO users (username, password) VALUES ('$username', '$password')";
    if (mysqli_query($con, $sql)) {
        $result["success"] = "1";
        $result["message"] = "success";
        echo json_encode($result);
        mysqli_close($con);
    } else {
        $result["success"] = "0";
        $result["message"] = "error";
        echo json_encode($result);
        mysqli_close($con);
    }
}
?>