<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$nama_konsumen = isset($_POST['NAMA_KONSUMEN']) ? $_POST['NAMA_KONSUMEN'] : 'empty';
$telp_konsumen = isset($_POST['TELP_KONSUMEN']) ? $_POST['TELP_KONSUMEN'] : 'empty';
$alamat_konsumen = isset($_POST['ALAMAT_KONSUMEN']) ? $_POST['ALAMAT_KONSUMEN'] : 'empty';


if ( $key == "insert" )
{
    $query = "INSERT INTO `tbl_konsumen` (NAMA_KONSUMEN,TELP_KONSUMEN,ALAMAT_KONSUMEN) VALUES('$nama_konsumen', '$telp_konsumen', '$alamat_konsumen')";

    if ( mysqli_query($conn, $query) )
    {
        $result["valueKonsumen"] = "1";
        $result["messageKonsumen"] = "Data konsumen berhasil dibuat";
    
        echo json_encode($result);
        mysqli_close($conn);

    }
    else 
    {
            $response["valueKonsumen"] = "0";
            $response["messageKonsumen"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
    }
}
