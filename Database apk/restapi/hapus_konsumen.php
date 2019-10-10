<?php 

require_once 'koneksi.php';

$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_konsumen = isset($_POST['ID_KONSUMEN']) ? $_POST['ID_KONSUMEN'] : 'empty';

if ( $key == "delete" ){

    $query = "DELETE FROM `tbl_konsumen` WHERE ID_KONSUMEN='$id_konsumen' ";

        if ( mysqli_query($conn, $query) ){

            $result["valueKonsumen"] = "1";
            $result["messageKonsumen"] = "Data konsumen berhasil dihapus";

            echo json_encode($result);
            mysqli_close($conn);

        } 
        else {

            $response["valueKonsumen"] = "0";
            $response["messageKonsumen"] = "Error! ".mysqli_error($conn);
            
            echo json_encode($response);
            mysqli_close($conn);
        }

} else {
    $response["valueKonsumen"] = "0";
    $response["messageKonsumen"] = "Error! ".mysqli_error($conn);
    echo json_encode($response);

    mysqli_close($conn);
}

?>