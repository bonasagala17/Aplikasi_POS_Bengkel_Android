<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_pembelian_sparepart ORDER BY id_pembelian DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_PEMBELIAN'        	=>$row['ID_PEMBELIAN'], 
        'ID_SUPPLIER'      		=>$row['ID_SUPPLIER'],
        'TANGGAL_PEMBELIAN'     =>date('d M Y', strtotime($row['TANGGAL_PEMBELIAN'])), 
        'TOTAL_PEMBAYARAN'   	=>$row['TOTAL_PEMBAYARAN'],
    	'STATUS'   				=>$row['STATUS']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
