<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';


if ( $key == "update" )
{
	
	$id_detail_transaksi_sparepart = isset($_POST['ID_DETAIL_TRANSAKSI_SPAREPART']) ? $_POST['ID_DETAIL_TRANSAKSI_SPAREPART'] : 'empty';
    $id_sparepart = isset($_POST['ID_SPAREPART']) ? $_POST['ID_SPAREPART'] : 'empty';
    $id_transaksi_pembayaran = isset($_POST['ID_TRANSAKSI_PEMBAYARAN']) ? $_POST['ID_TRANSAKSI_PEMBAYARAN'] : 'empty';
    $id_montir = isset($_POST['ID_MONTIR']) ? $_POST['ID_MONTIR'] : 'empty';
    $jumlah = isset($_POST['JUMLAH']) ? $_POST['JUMLAH'] : 'empty';
    $subtotal = isset($_POST['SUBTOTAL']) ? $_POST['SUBTOTAL'] : 'empty';

    $query = "UPDATE tbl_detail_transaksi_sparepart SET ID_SPAREPART = '$id_sparepart', ID_TRANSAKSI_PEMBAYARAN = '$id_transaksi_pembayaran', ID_MONTIR = '$id_montir', JUMLAH = '$jumlah', SUBTOTAL = '$subtotal' WHERE ID_DETAIL_TRANSAKSI_SPAREPART = $id_detail_transaksi_sparepart ";

    if ( mysqli_query($conn, $query) )
    {
        $result["value"] = "1";
        $result["message"] = "Data detail penjualan sparepart berhasil diubah";
    
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
