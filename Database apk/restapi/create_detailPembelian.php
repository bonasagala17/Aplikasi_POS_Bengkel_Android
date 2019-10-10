<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_detail_pembelian = isset($_POST['ID_DETAIL_PEMBELIAN']) ? $_POST['ID_DETAIL_PEMBELIAN'] : 'empty';
$id_pembelian = isset($_POST['ID_PEMBELIAN']) ? $_POST['ID_PEMBELIAN'] : 'empty';
$id_sparepart = isset($_POST['ID_SPAREPART']) ? $_POST['ID_SPAREPART'] : 'empty';
$hargabeli = isset($_POST['HARGABELI']) ? $_POST['HARGABELI'] : 'empty';
$jumlah = isset($_POST['JUMLAH']) ? $_POST['JUMLAH'] : 'empty';

if ( $key == "insert" )
{
    $NS = $s + $jumlah;

    $sql=mysqli_query($conn,"SELECT * from tbl_sparepart where ID_SPAREPART = '$id_sparepart'");
    $sql1 = mysqli_fetch_array($sql); 
    $s = $sql1['STOK_SPAREPART'];


    $query = "INSERT INTO `tbl_detail_pembelian_sparepart` (ID_PEMBELIAN,ID_SPAREPART,HARGABELI,JUMLAH) VALUES('$id_pembelian', '$id_sparepart', '$hargabeli', '$jumlah')";

    if ( mysqli_query($conn, $query) )
    {
         $update1 = mysqli_query($conn, "UPDATE tbl_sparepart
        set 
        STOK_SPAREPART='$NS',
        HARGABELI_SPAREPART= '$hargabeli',
        where ID_SPAREPART = '$id_sparepart'") ;

        $result["value"] = "1";
        $result["message"] = "Data pembelian sparepart berhasil dibuat";
    
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
