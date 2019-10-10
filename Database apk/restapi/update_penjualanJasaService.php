<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';


if ( $key == "update" )
{
	
	$id_detail_transaksi_jasaservice = isset($_POST['ID_DETAIL_TRANSAKSI_JASASERVICE']) ? $_POST['ID_DETAIL_TRANSAKSI_JASASERVICE'] : 'empty';
    $id_jasaservice = isset($_POST['ID_JASASERVICE']) ? $_POST['ID_JASASERVICE'] : 'empty';
    $id_transaksi_pembayaran = isset($_POST['ID_TRANSAKSI_PEMBAYARAN']) ? $_POST['ID_TRANSAKSI_PEMBAYARAN'] : 'empty';
    $id_montir = isset($_POST['ID_MONTIR']) ? $_POST['ID_MONTIR'] : 'empty';
    $jumlah = isset($_POST['JUMLAH']) ? $_POST['JUMLAH'] : 'empty';
    $subtotal = isset($_POST['SUBTOTAL']) ? $_POST['SUBTOTAL'] : 'empty';

    $query = "UPDATE tbl_detail_transaksi_jasaservice SET ID_JASASERVICE = '$id_jasaservice', ID_TRANSAKSI_PEMBAYARAN = '$id_transaksi_pembayaran', ID_MONTIR = '$id_montir', JUMLAH = '$jumlah', SUBTOTAL = '$subtotal' WHERE ID_DETAIL_TRANSAKSI_JASASERVICE = $id_detail_transaksi_jasaservice ";

    if ( mysqli_query($conn, $query) )
    {
        $result["value"] = "1";
        $result["message"] = "Data detail penjualan jasa service berhasil diubah";
    
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
