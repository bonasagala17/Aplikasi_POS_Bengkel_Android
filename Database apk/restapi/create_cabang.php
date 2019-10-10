<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$nama_cabang = isset($_POST['NAMA_CABANG']) ? $_POST['NAMA_CABANG'] : 'empty';
$alamat_cabang = isset($_POST['ALAMAT_CABANG']) ? $_POST['ALAMAT_CABANG'] : 'empty';


if ( $key == "insert" )
{
    $query = "INSERT INTO `tbl_cabang` (NAMA_CABANG,ALAMAT_CABANG) VALUES('$nama_cabang', '$alamat_cabang')";

    if ( mysqli_query($conn, $query) )
    {
        $result["value"] = "1";
        $result["message"] = "Data cabang berhasil dibuat";
    
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
