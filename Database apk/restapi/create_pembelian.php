<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_pembelian = isset($_POST['ID_PEMBELIAN']) ? $_POST['ID_PEMBELIAN'] : 'empty';
$id_supplier = isset($_POST['ID_SUPPLIER']) ? $_POST['ID_SUPPLIER'] : 'empty';
$tanggal_pembelian = isset($_POST['TANGGAL_PEMBELIAN']) ? $_POST['TANGGAL_PEMBELIAN'] : 'empty';
$total_pembayaran = isset($_POST['TOTAL_PEMBAYARAN']) ? $_POST['TOTAL_PEMBAYARAN'] : 'empty';
$status = isset($_POST['STATUS']) ? $_POST['STATUS'] : 'empty';


if ( $key == "insert" )
{

    $tanggal_pembelian_newformat = date('Y-m-d', strtotime($tanggal_pembelian));

    $query = "INSERT INTO `tbl_pembelian_sparepart` (ID_PEMBELIAN,ID_SUPPLIER,TANGGAL_PEMBELIAN,TOTAL_PEMBAYARAN,STATUS) VALUES('$id_pembelian', '$id_supplier', '$tanggal_pembelian_newformat', '$total_pembayaran', '$status')";

    if ( mysqli_query($conn, $query) )
    {
        $result["valuePembelian"] = "1";
        $result["messagePembelian"] = "Data pembelian berhasil dibuat";
    
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
