<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_jasaservice = isset($_POST['ID_JASASERVICE']) ? $_POST['ID_JASASERVICE'] : 'empty';
$id_transaksi_pembayaran = isset($_POST['ID_TRANSAKSI_PEMBAYARAN']) ? $_POST['ID_TRANSAKSI_PEMBAYARAN'] : 'empty';
$id_montir = isset($_POST['ID_MONTIR']) ? $_POST['ID_MONTIR'] : 'empty';
$jumlah = isset($_POST['JUMLAH']) ? $_POST['JUMLAH'] : 'empty';
$subtotal = isset($_POST['SUBTOTAL']) ? $_POST['SUBTOTAL'] : 'empty';
$id_kendaraan_customer = isset($_POST['ID_KENDARAAN_CUSTOMER']) ? $_POST['ID_KENDARAAN_CUSTOMER'] : 'empty';


if ( $key == "insert" )
{

    $q = "INSERT INTO tbl_montir (ID_MONTIR,ID_PEGAWAI,ID_KENDARAAN_CUSTOMER) VALUES ('','$id_montir','$id_kendaraan_customer')";


    if ( mysqli_query($conn, $q) )
    {
        $sql=mysqli_query($conn,"SELECT * from tbl_jasa_service where ID_JASASERVICE = '$id_jasaservice'");
        $sql1 = mysqli_fetch_array($sql); 
        $harga = $sql1['HARGA_JASASERVICE'];

        $sql2 = mysqli_query($conn,"SELECT MAX(ID_MONTIR) AS pid from tbl_montir");
        $sql22 = mysqli_fetch_array($sql2);
        $id_montirs = $sql22['pid'];

        $query = "INSERT INTO `tbl_detail_transaksi_jasaservice` (ID_JASASERVICE,ID_TRANSAKSI_PEMBAYARAN,ID_MONTIR,JUMLAH,SUBTOTAL) VALUES('$id_jasaservice', '$id_transaksi_pembayaran', '$id_montirs', '1', '$harga')";
        
        if ( mysqli_query($conn, $query) )
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
    else 
    {
            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
    }
}
