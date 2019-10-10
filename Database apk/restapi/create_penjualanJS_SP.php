<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_jasaservice = isset($_POST['ID_JASASERVICE']) ? $_POST['ID_JASASERVICE'] : 'empty';
$id_sparepart = isset($_POST['ID_SPAREPART']) ? $_POST['ID_SPAREPART'] : 'empty';
$id_transaksi_pembayaran = isset($_POST['ID_TRANSAKSI_PEMBAYARAN']) ? $_POST['ID_TRANSAKSI_PEMBAYARAN'] : 'empty';
$id_montir = isset($_POST['ID_MONTIR']) ? $_POST['ID_MONTIR'] : 'empty';
$jumlah = isset($_POST['JUMLAH']) ? $_POST['JUMLAH'] : 'empty';
$subtotal = isset($_POST['SUBTOTAL']) ? $_POST['SUBTOTAL'] : 'empty';

if ( $key == "insert" )
{
	$query = "INSERT INTO `tbl_detail_transaksi_jasaservice` (ID_JASASERVICE,ID_TRANSAKSI_PEMBAYARAN,ID_MONTIR,JUMLAH,SUBTOTAL) VALUES('$id_jasaservice', '$id_transaksi_pembayaran', '$id_montir', '$jumlah', '$subtotal')";

	$query .= "INSERT INTO `tbl_detail_transaksi_sparepart` (ID_SPAREPART,ID_TRANSAKSI_PEMBAYARAN,ID_MONTIR,JUMLAH,SUBTOTAL) VALUES('$id_sparepart', '$id_transaksi_pembayaran', '$id_montir', '$jumlah', '$subtotal')";

	$q = mysqli_multi_query($conn, $query);

    if ($q)
    {
        $result["value"] = "1";
        $result["message"] = "Data detail penjualan Jasa service berhasil dibuat";
    
        echo json_encode($result);
        mysqli_close($conn);

    }
    else 
    {
            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
    }
}
