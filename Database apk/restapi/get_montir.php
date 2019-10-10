<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_montir ORDER BY id_montir DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_MONTIR'        =>$row['ID_MONTIR'], 
        'ID_PEGAWAI'      =>$row['ID_PEGAWAI'],
        'ID_KENDARAAN_CUSTOMER'      =>$row['ID_KENDARAAN_CUSTOMER']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
