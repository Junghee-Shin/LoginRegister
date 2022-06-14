<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['fullname']) && isset($_POST['email']) && isset($_POST['username']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("users", $_POST['fullname'], $_POST['email'], $_POST['username'], $_POST['password'])) {
            //echo "Sign Up Success";
			echo json_encode(array(
				"info" => "Sign Up Success",
				"status" => 200,
			));
        } else {
			//echo "Sign up Failed";
			echo json_encode(array(
				"info" => "Sign up Failed",
				"status" => 200,
			));
		}
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
