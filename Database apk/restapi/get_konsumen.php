<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_konsumen ORDER BY id_konsumen DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_KONSUMEN'        =>$row['ID_KONSUMEN'], 
        'NAMA_KONSUMEN'      =>$row['NAMA_KONSUMEN'],
        'TELP_KONSUMEN'      =>$row['TELP_KONSUMEN'], 
        'ALAMAT_KONSUMEN'   =>$row['ALAMAT_KONSUMEN']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
