<?php 
/*
	define('host', 'localhost');
	define('user', 'root');
	define('pass', '');
	define('db', 'baru');

	$conn = mysqli_connect(host, user, pass, db) or die('Unable to Connect');
	
	*/
	define('DB_SERVER','192.168.19.140');
	define('DB_USER','pp8720');
	define('DB_PASS','8720');
	define('DB_NAME','8720');

	$conn = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME); 
	if(mysqli_connect_errno())
	{
		echo "Failed to connect to MySQL: " .mysqli_connect_error();
	}
	
?>