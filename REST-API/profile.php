<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    require_once 'credentials.php';
    $id = $_POST["id"];
    $sql = "SELECT created_at FROM users WHERE id = ?";
    if ($stmt = mysqli_prepare($con, $sql)) {
        mysqli_stmt_bind_param($stmt, 'i', $param_id);
        $param_id = $id;
        if (mysqli_stmt_execute($stmt)) {
            mysqli_stmt_store_result($stmt);
            mysqli_stmt_bind_result($stmt, $created_at);
            if (mysqli_stmt_fetch($stmt)) {
                $result["created_at"] = $created_at;
                mysqli_stmt_close($stmt);
            }
        }
    }
    $sql = "SELECT age, sex, height FROM profile WHERE id = ?";
    if ($stmt = mysqli_prepare($con, $sql)) {
        mysqli_stmt_bind_param($stmt, 'i', $param_id);
        $param_id = $id;
        if (mysqli_stmt_execute($stmt)) {
            mysqli_stmt_store_result($stmt);
            mysqli_stmt_bind_result($stmt, $age, $sex, $height);
            if (mysqli_stmt_fetch($stmt)) {
                $result["age"] = $age;
                $result["sex"] = $sex;
                $result["height"] = $height;
                $result["success"] = "1";
                $result["message"] = "success";
                echo json_encode($result);
                mysqli_close($con);
            }
        }
    } else {
       
    }
} 
$result["success"] = "0";
$result["message"] = "error";
echo json_encode($result);
mysqli_close($con);
?>