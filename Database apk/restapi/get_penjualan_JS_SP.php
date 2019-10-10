<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT tbl_detail_transaksi_jasaservice.ID_DETAIL_TRANSAKSI_JASASERVICE, tbl_detail_transaksi_jasaservice.ID_JASASERVICE, tbl_detail_transaksi_jasaservice.ID_TRANSAKSI_PEMBAYARAN, tbl_detail_transaksi_jasaservice.ID_MONTIR, tbl_detail_transaksi_jasaservice.JUMLAH, tbl_detail_transaksi_jasaservice.SUBTOTAL, tbl_detail_transaksi_sparepart.ID_DETAIL_TRANSAKSI_SPAREPART, tbl_detail_transaksi_sparepart.ID_SPAREPART FROM tbl_detail_transaksi_jasaservice INNER JOIN tbl_detail_transaksi_sparepart ON tbl_detail_transaksi_jasaservice.ID_DETAIL_TRANSAKSI_JASASERVICE = tbl_detail_transaksi_sparepart.ID_DETAIL_TRANSAKSI_SPAREPART
 ";

$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
   		'ID_DETAIL_TRANSAKSI_JASASERVICE' =>$row['ID_DETAIL_TRANSAKSI_JASASERVICE'],
        'ID_JASASERVICE'   		=>$row['ID_JASASERVICE'], 
        'ID_TRANSAKSI_PEMBAYARAN'      			=>$row['NO_TRANSAKSI'],
        'ID_MONTIR'      			=>$row['ID_MONTIR'], 
        'JUMLAH'   					=>$row['JUMLAH'],
    	'SUBTOTAL'   						=>$row['SUBTOTAL'],
    	'ID_DETAIL_TRANSAKSI_SPAREPART' =>$row['ID_DETAIL_TRANSAKSI_SPAREPART'],
    	'ID_SPAREPART'   		=>$row['ID_SPAREPART'], ) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
