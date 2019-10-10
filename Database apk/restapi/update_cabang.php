<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';



if ( $key == "update" )
{
	
	$id = isset($_POST['ID_CABANG']) ? $_POST['ID_CABANG'] : 'empty';
	$nama_cabang = isset($_POST['NAMA_CABANG']) ? $_POST['NAMA_CABANG'] : 'empty';
	$alamat_cabang = isset($_POST['ALAMAT_CABANG']) ? $_POST['ALAMAT_CABANG'] : 'empty';

    $query = "UPDATE tbl_cabang SET NAMA_CABANG = '$nama_cabang', ALAMAT_CABANG = '$alamat_cabang' WHERE ID_CABANG = $id ";

    if ( mysqli_query($conn, $query) )
    {
        $result["value"] = "1";
        $result["message"] = "Data cabang berhasil diubah";
    
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
