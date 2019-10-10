<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_cabang ORDER BY id_cabang DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_CABANG'        =>$row['ID_CABANG'], 
        'NAMA_CABANG'      =>$row['NAMA_CABANG'], 
        'ALAMAT_CABANG'   =>$row['ALAMAT_CABANG']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
