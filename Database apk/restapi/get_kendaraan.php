<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_kendaraan ORDER BY id_kendaraan DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_KENDARAAN'        =>$row['ID_KENDARAAN'], 
        'MERK_KENDARAAN'      =>$row['MERK_KENDARAAN'],
        'TIPE_KENDARAAN'      =>$row['TIPE_KENDARAAN']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
