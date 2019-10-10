<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_kendaraan_customer WHERE ID_KENDARAAN_CUSTOMER != '1' ORDER BY id_kendaraan_customer DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_KENDARAAN_CUSTOMER'  =>$row['ID_KENDARAAN_CUSTOMER'], 
        'ID_KENDARAAN'      	 =>$row['ID_KENDARAAN'],
        'PLAT_NOMOR'     		 =>$row['PLAT_NOMOR']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
