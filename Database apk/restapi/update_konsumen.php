<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';



if ( $key == "update" )
{
	
	$id_konsumen = isset($_POST['ID_KONSUMEN']) ? $_POST['ID_KONSUMEN'] : 'empty';
	$nama_konsumen = isset($_POST['NAMA_KONSUMEN']) ? $_POST['NAMA_KONSUMEN'] : 'empty';
    $telp_konsumen = isset($_POST['TELP_KONSUMEN']) ? $_POST['TELP_KONSUMEN'] : 'empty';
    $alamat_konsumen = isset($_POST['ALAMAT_KONSUMEN']) ? $_POST['ALAMAT_KONSUMEN'] : 'empty';

    $query = "UPDATE tbl_konsumen SET NAMA_KONSUMEN = '$nama_konsumen', TELP_KONSUMEN = '$telp_konsumen', ALAMAT_KONSUMEN = '$alamat_konsumen' WHERE ID_KONSUMEN = $id_konsumen ";

    if ( mysqli_query($conn, $query) )
    {
        $result["valueKonsumen"] = "1";
        $result["messageKonsumen"] = "Data konsumen berhasil diubah";
    
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
