<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_jasa_service ORDER BY id_jasaservice DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_JASASERVICE'        =>$row['ID_JASASERVICE'], 
        'NAMA_JASASERVICE'      =>$row['NAMA_JASASERVICE'],
        'HARGA_JASASERVICE'      =>$row['HARGA_JASASERVICE']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
