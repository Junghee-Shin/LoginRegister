<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("users", $_POST['username'], $_POST['password'])) {
            //echo "Login Success";
			echo json_encode(array(
				"info" => "Login Success",
				"status" => 200,
			));
        } else {
			//echo "Username or Password wrong";
			echo json_encode(array(
				"info" => "Username or Password wrong",
				"status" => 500,
			));
		}
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
