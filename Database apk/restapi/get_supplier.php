<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_supplier ORDER BY id_supplier DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_SUPPLIER'       =>$row['ID_SUPPLIER'], 
        'NAMA_SUPPLIER'     =>$row['NAMA_SUPPLIER'], 
        'ALAMAT_SUPPLIER'   =>$row['ALAMAT_SUPPLIER'],
    	'TELP_SUPPLIER'   	=>$row['TELP_SUPPLIER'],
    	'NAMA_SALES'   		=>$row['NAMA_SALES'],
    	'TELP_SALES'   		=>$row['TELP_SALES'],) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
