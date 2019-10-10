<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT tbl_detail_transaksi_sparepart.ID_DETAIL_TRANSAKSI_SPAREPART, 
tbl_sparepart.NAMA_SPAREPART as ID_SPAREPART, 
tbl_transaksi_pembayaran.NO_TRANSAKSI as ID_TRANSAKSI_PEMBAYARAN, 
tbl_detail_transaksi_sparepart.ID_MONTIR, 
tbl_sparepart.HARGAJUAL_SPAREPART as HARGA,
tbl_detail_transaksi_sparepart.JUMLAH, 
tbl_detail_transaksi_sparepart.SUBTOTAL 



FROM tbl_sparepart JOIN tbl_detail_transaksi_sparepart 

ON tbl_sparepart.ID_SPAREPART=tbl_detail_transaksi_sparepart.ID_SPAREPART 

JOIN tbl_transaksi_pembayaran ON tbl_detail_transaksi_sparepart.ID_TRANSAKSI_PEMBAYARAN=tbl_transaksi_pembayaran.ID_TRANSAKSI_PEMBAYARAN ";

$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
   		'ID_DETAIL_TRANSAKSI_SPAREPART' =>$row['ID_DETAIL_TRANSAKSI_SPAREPART'],
        'ID_SPAREPART'   		=>$row['ID_SPAREPART'], 
        'ID_TRANSAKSI_PEMBAYARAN'      			=>$row['ID_TRANSAKSI_PEMBAYARAN'],
        'ID_MONTIR'      			=>$row['ID_MONTIR'],
        'HARGA'      			=>$row['HARGA'], 
        'JUMLAH'   					=>$row['JUMLAH'],
    	'SUBTOTAL'   						=>$row['SUBTOTAL']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
