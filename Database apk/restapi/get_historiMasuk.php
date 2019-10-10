<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT tbl_sparepart.NAMA_SPAREPART, 
tbl_pembelian_sparepart.TANGGAL_PEMBELIAN 

FROM tbl_sparepart, tbl_pembelian_sparepart 
WHERE tbl_sparepart.ID_SPAREPART = tbl_pembelian_sparepart.ID_PEMBELIAN";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
   		'NAMA_SPAREPART' =>$row['NAMA_SPAREPART'],
        'TANGGAL_PEMBELIAN'   		=>$row['TANGGAL_PEMBELIAN']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
