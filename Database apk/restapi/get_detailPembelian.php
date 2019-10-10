<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_detail_pembelian_sparepart ORDER BY id_detail_pembelian DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_DETAIL_PEMBELIAN'   =>$row['ID_DETAIL_PEMBELIAN'], 
        'ID_PEMBELIAN'      	=>$row['ID_PEMBELIAN'],
        'ID_SPAREPART'      	=>$row['ID_SPAREPART'], 
        'HARGABELI'   			=>$row['HARGABELI'],
    	'JUMLAH'   				=>$row['JUMLAH']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
