<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    require_once 'credentials.php';
    $id = $_POST['id'];
    $sql = "SELECT calories, burnedCalories , weight, 
        b.date FROM burnedCalories b INNER JOIN weight w ON 
        w.id = b.id inner JOIN calories c ON w.id = c.id AND w.date =
        b.date AND b.date = c.date and c.id = $id group by b.date";
    $result = $con->query($sql);
    $rows = [];
    while ($row = mysqli_fetch_array($result)) {
        $rows[] = $row;
    }
    echo json_encode($rows);
    mysqli_close($con);
}
?>

