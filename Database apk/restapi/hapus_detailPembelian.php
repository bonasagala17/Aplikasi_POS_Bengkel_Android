<?php 

require_once 'koneksi.php';

$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_detail_pembelian = isset($_POST['ID_DETAIL_PEMBELIAN']) ? $_POST['ID_DETAIL_PEMBELIAN'] : 'empty';

if ( $key == "delete" ){

    $query = "DELETE FROM `tbl_detail_pembelian_sparepart` WHERE ID_DETAIL_PEMBELIAN='$id_detail_pembelian' ";

        if ( mysqli_query($conn, $query) ){

            $result["value"] = "1";
            $result["message"] = "Data pembelian berhasil dihapus";

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