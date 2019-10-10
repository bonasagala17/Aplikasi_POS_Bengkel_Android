<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_sparepart = isset($_POST['ID_SPAREPART']) ? $_POST['ID_SPAREPART'] : 'empty';
$gambar_sparepart = isset($_POST['GAMBAR_SPAREPART']) ? $_POST['GAMBAR_SPAREPART'] : 'empty';

if ($key == "delete"){

    $query = "DELETE FROM `tbl_sparepart` WHERE ID_SPAREPART='$id_sparepart' ";

        if (mysqli_query($conn, $query) ){

            $iparr = split ("/", $gambar_sparepart);
            $picture_split = $iparr[5];

            if (unlink("sparepart_picture/".$picture_split)){

                $result["valueSparepart"] = "1";
                $result["messageSparepart"] = "Data sparepart berhasil hapus";

                echo json_encode($result);
                mysqli_close($conn);

            } else {
            
                $response["valueSparepart"] = "0";
                $response["messageSparepart"] = "Error to delete a image! ".mysqli_error($conn);
                echo json_encode($response);
    
                mysqli_close($conn);
            }

        } 
        else {

            $response["valueSparepart"] = "0";
            $response["messageSparepart"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
        }

} else {
    $response["valueSparepart"] = "0";
    $response["messageSparepart"] = "Error! ".mysqli_error($conn);
    echo json_encode($response);

    mysqli_close($conn);
}

?>