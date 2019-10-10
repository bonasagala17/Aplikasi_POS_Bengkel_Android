<?php 

require_once 'koneksi.php';

$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id = isset($_POST['ID_CABANG']) ? $_POST['ID_CABANG'] : 'empty';

if ( $key == "delete" ){

    $query = "DELETE FROM `tbl_cabang` WHERE ID_CABANG='$id' ";

        if ( mysqli_query($conn, $query) ){

            $result["value"] = "1";
            $result["message"] = "Data cabang berhasil dihapus";

            echo json_encode($result);
            mysqli_close($conn);

        } 
        else {

            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            
            echo json_encode($response);
            mysqli_close($conn);
        }

} else {
    $response["value"] = "0";
    $response["message"] = "Error! ".mysqli_error($conn);
    echo json_encode($response);

    mysqli_close($conn);
}

?>