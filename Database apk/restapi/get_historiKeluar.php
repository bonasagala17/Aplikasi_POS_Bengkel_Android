<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT tbl_sparepart.NAMA_SPAREPART, tbl_transaksi_pembayaran.TANGGAL_TRANSAKSI FROM tbl_sparepart, tbl_transaksi_pembayaran WHERE tbl_sparepart.ID_SPAREPART = tbl_transaksi_pembayaran.ID_TRANSAKSI_PEMBAYARAN";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
   		'NAMA_SPAREPART' =>$row['NAMA_SPAREPART'],
        'TANGGAL_TRANSAKSI'   		=>$row['TANGGAL_TRANSAKSI']) 
    );
}

echo json_encode($response);

mysqli_close($conn);

?>
