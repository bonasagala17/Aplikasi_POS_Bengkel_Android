<?php 

require_once 'koneksi.php';

$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_pembelian = isset($_POST['ID_PEMBELIAN']) ? $_POST['ID_PEMBELIAN'] : 'empty';

if ( $key == "delete" ){

    $query = "DELETE FROM `tbl_pembelian_sparepart` WHERE ID_PEMBELIAN='$id_pembelian' ";

        if ( mysqli_query($conn, $query) ){

            $result["valuePembelian"] = "1";
            $result["messagePembelian"] = "Data pembelian berhasil dihapus";

            echo json_encode($result);
            mysqli_close($conn);

        } 
        else {

            $response["valuePembelian"] = "0";
            $response["messagePembelian"] = "Error! ".mysqli_error($conn);
            
            echo json_encode($response);
            mysqli_close($conn);
        }

} else {
    $response["valuePembelian"] = "0";
    $response["messagePembelian"] = "Error! ".mysqli_error($conn);
    echo json_encode($response);

    mysqli_close($conn);
}

?>