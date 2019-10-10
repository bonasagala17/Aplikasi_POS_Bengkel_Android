<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';



if ( $key == "update" )
{
	
	$id_pembelian = isset($_POST['ID_PEMBELIAN']) ? $_POST['ID_PEMBELIAN'] : 'empty';
	$id_supplier = isset($_POST['ID_SUPPLIER']) ? $_POST['ID_SUPPLIER'] : 'empty';
    $tanggal_pembelian = isset($_POST['TANGGAL_PEMBELIAN']) ? $_POST['TANGGAL_PEMBELIAN'] : 'empty';
    $total_pembayaran = isset($_POST['TOTAL_PEMBAYARAN']) ? $_POST['TOTAL_PEMBAYARAN'] : 'empty';
    $status = isset($_POST['STATUS']) ? $_POST['STATUS'] : 'empty';

    $tanggal_pembelian =  date('Y-m-d', strtotime($tanggal_pembelian));

    $query = "UPDATE tbl_pembelian_sparepart SET ID_SUPPLIER = '$id_supplier', TANGGAL_PEMBELIAN = '$tanggal_pembelian', TOTAL_PEMBAYARAN = '$total_pembayaran', STATUS = '$status' WHERE ID_PEMBELIAN = $id_pembelian ";

    if ( mysqli_query($conn, $query) )
    {
        $result["valuePembelian"] = "1";
        $result["messagePembelian"] = "Data pembelian berhasil diubah";
    
        echo json_encode($result);
        mysqli_close($conn);

    }
    else 
    {
            $response["valuePembelian"] = "0";
            $response["messagePembelian"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
    }
}
