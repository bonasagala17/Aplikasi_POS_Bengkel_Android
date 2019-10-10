<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_transaksi_pembayaran ORDER BY id_transaksi_pembayaran DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

   while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_TRANSAKSI_PEMBAYARAN'   =>$row['ID_TRANSAKSI_PEMBAYARAN'], 
        'ID_KONSUMEN'               =>$row['ID_KONSUMEN'], 
        'ID_CABANG'                 =>$row['ID_CABANG'],
        'NO_TRANSAKSI'              =>$row['NO_TRANSAKSI'],
        'TANGGAL_TRANSAKSI'         =>$row['TANGGAL_TRANSAKSI'],
        'DISKON'                    =>$row['DISKON'],
        'TOTAL_TRANSAKSI'           =>$row['TOTAL_TRANSAKSI'],
        'STATUS_PEMBAYARAN'         =>$row['STATUS_PEMBAYARAN']) 
    );
}
        
echo json_encode($response);

mysqli_close($conn);

?>