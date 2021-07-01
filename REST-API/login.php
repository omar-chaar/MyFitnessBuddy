<?php
require_once 'credentials.php';
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = filter_input(INPUT_POST, "username");
    $password = filter_input(INPUT_POST, "password");
    $sql = "SELECT id, username, password FROM users WHERE username = ?";
    if ($stmt = mysqli_prepare($con, $sql)) {
        mysqli_stmt_bind_param($stmt, "s", $param_username);
        $param_username = $username;
        if (mysqli_stmt_execute($stmt)) {
            mysqli_stmt_store_result($stmt);
            if (mysqli_stmt_num_rows($stmt) == 1) {
                mysqli_stmt_bind_result($stmt, $id, $username, $hashed_password);
                if (mysqli_stmt_fetch($stmt)) {
                    if (password_verify($password, $hashed_password)) {
                        $result["success"] = "1";
                        $result["message"] = "success";
                        $result["id"] = $id;
                        echo json_encode($result);
                        mysqli_close($con);
                    } else {
                        $result["success"] = "01";
                        $result["message"] = "error";
                        echo json_encode($result);
                        mysqli_close($con);
                    }
                }
            } else {
                $result["success"] = "02";
                $result["message"] = "error";
                echo json_encode($result);
                mysqli_close($con);
            }
        } else {
            $result["success"] = "03";
            $result["message"] = "error";
            echo json_encode($result);
            mysqli_close($con);
        }
        mysqli_stmt_close($stmt);
    }
}
mysqli_close($con);
?>
