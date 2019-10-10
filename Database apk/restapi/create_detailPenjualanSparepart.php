<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_sparepart = isset($_POST['ID_SPAREPART']) ? $_POST['ID_SPAREPART'] : 'empty';
$id_transaksi_pembayaran = isset($_POST['ID_TRANSAKSI_PEMBAYARAN']) ? $_POST['ID_TRANSAKSI_PEMBAYARAN'] : 'empty';
$id_montir = isset($_POST['ID_MONTIR']) ? $_POST['ID_MONTIR'] : 'empty';
$jumlah = isset($_POST['JUMLAH']) ? $_POST['JUMLAH'] : 'empty';
$subtotal = isset($_POST['SUBTOTAL']) ? $_POST['SUBTOTAL'] : 'empty';

if ( $key == "insert" )
{
    $sql=mysqli_query($conn,"SELECT * from tbl_sparepart where ID_SPAREPART = '$id_sparepart'");
    $sql1 = mysqli_fetch_array($sql); 
    $s = $sql1['STOK_SPAREPART'];
    $harga = $sql1['HARGAJUAL_SPAREPART'];
    
    $sub = $harga*$jumlah;

    $query = "INSERT INTO `tbl_detail_transaksi_sparepart` (ID_SPAREPART,ID_TRANSAKSI_PEMBAYARAN,ID_MONTIR,HARGA,JUMLAH,SUBTOTAL) VALUES('$id_sparepart', '$id_transaksi_pembayaran', '$id_montir', '$harga','$jumlah', '$sub')";

    if ( mysqli_query($conn, $query) )
    {
       
        $NS = $s - $jumlah;

        $update1 = mysqli_query($conn, "UPDATE tbl_sparepart
        set 
        STOK_SPAREPART='$NS'
        where ID_SPAREPART = '$id_sparepart'") ;
        
        $result["value"] = "1";
        $result["message"] = "Data detail penjualan berhasil dibuat";
    
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
