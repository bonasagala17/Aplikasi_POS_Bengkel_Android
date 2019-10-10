<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';



if ( $key == "update" )
{
	
	$id_supplier = isset($_POST['ID_SUPPLIER']) ? $_POST['ID_SUPPLIER'] : 'empty';
	$nama_supplier = isset($_POST['NAMA_SUPPLIER']) ? $_POST['NAMA_SUPPLIER'] : 'empty';
	$alamat_supplier = isset($_POST['ALAMAT_SUPPLIER']) ? $_POST['ALAMAT_SUPPLIER'] : 'empty';
	$telp_supplier = isset($_POST['TELP_SUPPLIER']) ? $_POST['TELP_SUPPLIER'] : 'empty';
	$nama_sales = isset($_POST['NAMA_SALES']) ? $_POST['NAMA_SALES'] : 'empty';
	$telp_sales = isset($_POST['TELP_SALES']) ? $_POST['TELP_SALES'] : 'empty';

    $query = "UPDATE tbl_supplier SET NAMA_SUPPLIER = '$nama_supplier', ALAMAT_SUPPLIER = '$alamat_supplier', TELP_SUPPLIER = '$telp_supplier', NAMA_SALES = '$nama_sales', TELP_SALES = '$telp_sales' WHERE ID_SUPPLIER = $id_supplier ";

    if ( mysqli_query($conn, $query) )
    {
        $result["valueSupplier"] = "1";
        $result["messageSupplier"] = "Data supplier berhasil diubah";
    
        echo json_encode($result);
        mysqli_close($conn);

    }
    else 
    {
            $response["valueSupplier"] = "0";
            $response["messageSupplier"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
    }
}
