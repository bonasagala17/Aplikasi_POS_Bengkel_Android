<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';


if ( $key == "update" )
{

	$id_detail_pembelian = isset($_POST['ID_DETAIL_PEMBELIAN']) ? $_POST['ID_DETAIL_PEMBELIAN'] : 'empty';
	$id_pembelian = isset($_POST['ID_PEMBELIAN']) ? $_POST['ID_PEMBELIAN'] : 'empty';
	$id_sparepart = isset($_POST['ID_SPAREPART']) ? $_POST['ID_SPAREPART'] : 'empty';
	$hargabeli = isset($_POST['HARGABELI']) ? $_POST['HARGABELI'] : 'empty';
	$jumlah = isset($_POST['JUMLAH']) ? $_POST['JUMLAH'] : 'empty';

	$query = "UPDATE tbl_detail_pembelian_sparepart SET ID_PEMBELIAN = '$id_pembelian', ID_SPAREPART = '$id_sparepart', HARGABELI = '$hargabeli', JUMLAH = '$jumlah' WHERE ID_DETAIL_PEMBELIAN = $id_detail_pembelian ";

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