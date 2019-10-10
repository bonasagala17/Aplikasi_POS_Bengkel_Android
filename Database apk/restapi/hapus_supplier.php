<?php 

require_once 'koneksi.php';

$key = isset($_POST['key']) ? $_POST['key'] : 'empty';
$id_supplier = isset($_POST['ID_SUPPLIER']) ? $_POST['ID_SUPPLIER'] : 'empty';

if ( $key == "delete" ){

    $query = "DELETE FROM `tbl_supplier` WHERE ID_SUPPLIER='$id_supplier' ";

        if ( mysqli_query($conn, $query) ){

            $result["valueSupplier"] = "1";
            $result["messageSupplier"] = "Data supplier berhasil dihapus";

            echo json_encode($result);
            mysqli_close($conn);

        } 
        else {

            $response["valueSupplier"] = "0";
            $response["messageSupplier"] = "Error! ".mysqli_error($conn);
            
            echo json_encode($response);
            mysqli_close($conn);
        }

} else {
    $response["valueSupplier"] = "0";
    $response["messageSupplier"] = "Error! ".mysqli_error($conn);
    echo json_encode($response);

    mysqli_close($conn);
}

?>