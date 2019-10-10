<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_konsumen = isset($_POST['ID_KONSUMEN']) ? $_POST['ID_KONSUMEN'] : 'empty';
$id_cabang = isset($_POST['ID_CABANG']) ? $_POST['ID_CABANG'] : 'empty';
//$no_transaksi = isset($_POST['NO_TRANSAKSI']) ? $_POST['NO_TRANSAKSI'] : 'empty';
//$tanggal_transaksi = isset($_POST['TANGGAL_TRANSAKSI']) ? $_POST['TANGGAL_TRANSAKSI'] : 'empty';
$diskon = isset($_POST['DISKON']) ? $_POST['DISKON'] : 'empty';
$total_transaksi = isset($_POST['TOTAL_TRANSAKSI']) ? $_POST['TOTAL_TRANSAKSI'] : 'empty';
//$status_pembayaran = isset($_POST['STATUS_PEMBAYARAN']) ? $_POST['STATUS_PEMBAYARAN'] : 'empty';

$tanggal_transaksi = date('Y-m-d H:i:s');
$date=date_create($tanggal_transaksi);
$date1 =date_format($date,"dmY");
$tahun =substr($date1, 6);
$tglbln = substr($date1, 0,4);
$no_transaksi='SP-'.$tglbln.$tahun.'-';

if ( $key == "insert" )
{

	$urutan=1;
	$no_transaksi1=$no_transaksi.$urutan;
	//$tanggal_transaksi_newformat = date('Y-m-d', strtotime($tanggal_transaksi));

	$query = "INSERT INTO `tbl_transaksi_pembayaran` (ID_KONSUMEN,ID_CABANG,NO_TRANSAKSI,TANGGAL_TRANSAKSI,DISKON,TOTAL_TRANSAKSI,STATUS_PEMBAYARAN,TOTAL_DISKON, BAYAR, KEMBALIAN) VALUES('$id_konsumen', '$id_cabang', '$no_transaksi1', '$tanggal_transaksi', '$diskon', '$total_transaksi', 'On Process','','','')";

	if ( mysqli_query($conn, $query) )
    {
        $result["value"] = "1";
        $result["message"] = "Data penjualan berhasil dibuat";
    
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

?>