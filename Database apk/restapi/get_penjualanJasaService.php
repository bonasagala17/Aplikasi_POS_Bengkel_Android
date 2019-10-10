<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$id_transaksi_pembayaran = isset($_POST['ID_TRANSAKSI_PEMBAYARAN']) ? $_POST['ID_TRANSAKSI_PEMBAYARAN'] : 'empty';

$query = "SELECT tbl_detail_transaksi_jasaservice.ID_DETAIL_TRANSAKSI_JASASERVICE, 
tbl_jasa_service.NAMA_JASASERVICE as ID_JASASERVICE, 
tbl_transaksi_pembayaran.NO_TRANSAKSI as ID_TRANSAKSI_PEMBAYARAN, 
tbl_detail_transaksi_jasaservice.ID_MONTIR, 
tbl_detail_transaksi_jasaservice.JUMLAH, 
tbl_detail_transaksi_jasaservice.SUBTOTAL 

FROM tbl_jasa_service JOIN tbl_detail_transaksi_jasaservice 

ON tbl_jasa_service.ID_JASASERVICE=tbl_detail_transaksi_jasaservice.ID_JASASERVICE 

JOIN tbl_transaksi_pembayaran ON tbl_detail_transaksi_jasaservice.ID_TRANSAKSI_PEMBAYARAN=tbl_transaksi_pembayaran.ID_TRANSAKSI_PEMBAYARAN /*WHERE tbl_transaksi_pembayaran.ID_TRANSAKSI_PEMBAYARAN = '$id_transaksi_pembayaran'*/ ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result)){

    array_push($response, 
    array(
   		'ID_DETAIL_TRANSAKSI_JASASERVICE' =>$row['ID_DETAIL_TRANSAKSI_JASASERVICE'],
        'ID_JASASERVICE'   		=>$row['ID_JASASERVICE'], 
        'ID_TRANSAKSI_PEMBAYARAN'      			=>$row['ID_TRANSAKSI_PEMBAYARAN'],
        'ID_MONTIR'      			=>$row['ID_MONTIR'], 
        'JUMLAH'   					=>$row['JUMLAH'],
    	'SUBTOTAL'   						=>$row['SUBTOTAL']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
