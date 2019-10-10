<?php 

require_once 'koneksi.php';


$key = isset($_POST['key']) ? $_POST['key'] : 'empty';

$nama_supplier = isset($_POST['NAMA_SUPPLIER']) ? $_POST['NAMA_SUPPLIER'] : 'empty';
$alamat_supplier = isset($_POST['ALAMAT_SUPPLIER']) ? $_POST['ALAMAT_SUPPLIER'] : 'empty';
$telp_supplier = isset($_POST['TELP_SUPPLIER']) ? $_POST['TELP_SUPPLIER'] : 'empty';
$nama_sales = isset($_POST['NAMA_SALES']) ? $_POST['NAMA_SALES'] : 'empty';
$telp_sales = isset($_POST['TELP_SALES']) ? $_POST['TELP_SALES'] : 'empty';


if ( $key == "insert" )
{

	$query = "INSERT INTO `tbl_supplier` (NAMA_SUPPLIER,ALAMAT_SUPPLIER,TELP_SUPPLIER,NAMA_SALES,TELP_SALES) 
	VALUES('$nama_supplier', '$alamat_supplier', '$telp_supplier', '$nama_sales', '$telp_sales')";

	if ( mysqli_query($conn, $query) )
	{
	    $result["valueSupplier"] = "1";
	   $result["messageSupplier"] = "Data supplier berhasil dibuat";
	    
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
?>
	


