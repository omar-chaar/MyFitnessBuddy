<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id =  $_POST['id'];
    $caloriesin = filter_input(INPUT_POST, "caloriesin");
    $caloriesout = filter_input(INPUT_POST, "caloriesout");
    $weight =  filter_input(INPUT_POST, "weight");
    require_once 'credentials.php';
    $sql = "INSERT INTO weight (id, weight) VALUES (?,?)";
    if ($stmt = mysqli_prepare($con, $sql)) {
        mysqli_stmt_bind_param($stmt, "id", $param_id, $param_weight);
        $param_id = $id;
        $param_weight = $weight;
        if (mysqli_stmt_execute($stmt)) {
           
        }
        else {
            $result["success"] = "0";
        $result["message"] = "error";
        echo json_encode($result);
        mysqli_close($con);
        }
        mysqli_stmt_close($stmt);
    }
    
    
    $sql = "INSERT INTO calories (id, calories) VALUES (?,?)";
    if ($stmt = mysqli_prepare($con, $sql)) {
        mysqli_stmt_bind_param($stmt, "ii", $param_id, $param_eaten);
        $param_id = $id;
        $param_eaten = $caloriesin;
        
        if (mysqli_stmt_execute($stmt)) {
            
        }
        else {
            $result["success"] = "0";
        $result["message"] = "error";
        echo json_encode($result);
        mysqli_close($con);
        }
        mysqli_stmt_close($stmt);
    }
   
    $sql = "INSERT INTO burnedCalories (id, burnedCalories) VALUES (?,?)";
    if ($stmt = mysqli_prepare($con, $sql)) {
        mysqli_stmt_bind_param($stmt, "ii", $param_id, $param_burned);
        $param_id = $id;
        $param_burned = $caloriesout;
          if (mysqli_stmt_execute($stmt)) {
            
        }
        else {
            $result["success"] = "0";
        $result["message"] = "error";
        echo json_encode($result);
        mysqli_close($con);
        }
        mysqli_stmt_close($stmt);
    }


    if (!isset($result["sucess"])){
    $result["success"] = "1";
    $result["message"] = "success";
    echo json_encode($result);
    mysqli_close($con);
    }            
}
    
?>