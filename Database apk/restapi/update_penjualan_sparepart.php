<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';



if ( $key == "update" )
{
	
	$id_transaksi_pembayaran = isset($_POST['ID_TRANSAKSI_PEMBAYARAN']) ? $_POST['ID_TRANSAKSI_PEMBAYARAN'] : 'empty';
    $id_konsumen = isset($_POST['ID_KONSUMEN']) ? $_POST['ID_KONSUMEN'] : 'empty';
    $id_cabang = isset($_POST['ID_CABANG']) ? $_POST['ID_CABANG'] : 'empty';
    $no_transaksi = isset($_POST['NO_TRANSAKSI']) ? $_POST['NO_TRANSAKSI'] : 'empty';
    $tanggal_transaksi = isset($_POST['TANGGAL_TRANSAKSI']) ? $_POST['TANGGAL_TRANSAKSI'] : 'empty';
    $diskon = isset($_POST['DISKON']) ? $_POST['DISKON'] : 'empty';
    $total_transaksi = isset($_POST['TOTAL_TRANSAKSI']) ? $_POST['TOTAL_TRANSAKSI'] : 'empty';
    $status_pembayaran = isset($_POST['STATUS_PEMBAYARAN']) ? $_POST['STATUS_PEMBAYARAN'] : 'empty';
    $total_diskon = isset($_POST['TOTAL_DISKON']) ? $_POST['TOTAL_DISKON'] : 'empty';
    $bayar = isset($_POST['BAYAR']) ? $_POST['BAYAR'] : 'empty';
    $kembalian = isset($_POST['KEMBALIAN']) ? $_POST['KEMBALIAN'] : 'empty';

    $tanggal_transaksi = date('Y-m-d', strtotime($tanggal_transaksi));

    $query = "UPDATE tbl_transaksi_pembayaran SET ID_KONSUMEN = '$id_konsumen', ID_CABANG = '$id_cabang', NO_TRANSAKSI = '$no_transaksi', TANGGAL_TRANSAKSI = '$tanggal_transaksi', DISKON = '$diskon', TOTAL_TRANSAKSI = '$total_transaksi', STATUS_PEMBAYARAN = '$status_pembayaran', TOTAL_DISKON = '$total_diskon', BAYAR = '$bayar', KEMBALIAN = '$kembalian' WHERE ID_TRANSAKSI_PEMBAYARAN = $id_transaksi_pembayaran ";

    if ( mysqli_query($conn, $query) )
    {
        $result["value"] = "1";
        $result["message"] = "Data penjualan sparepart berhasil diubah";
    
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
