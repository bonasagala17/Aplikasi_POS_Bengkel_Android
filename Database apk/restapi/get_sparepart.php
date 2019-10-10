<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_sparepart ORDER BY id_sparepart DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

   while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_SPAREPART'        =>$row['ID_SPAREPART'], 
        'NAMA_SPAREPART'      =>$row['NAMA_SPAREPART'], 
        'MERK_SPAREPART'   =>$row['MERK_SPAREPART'],
        'TIPE_SPAREPART'     =>$row['TIPE_SPAREPART'],
        'HARGABELI_SPAREPART'    =>$row['HARGABELI_SPAREPART'],
        'HARGAJUAL_SPAREPART'    =>$row['HARGAJUAL_SPAREPART'],
        'STOK_SPAREPART'    =>$row['STOK_SPAREPART'],
        'STOKOPTIMAL_SPAREPART'    =>$row['STOKOPTIMAL_SPAREPART'],
        'GAMBAR_SPAREPART'   =>"http://$server_name" . $row['GAMBAR_SPAREPART'],
        'LETAK_SPAREPART'      =>$row['LETAK_SPAREPART']) 
    );
}
        
echo json_encode($response);

mysqli_close($conn);

?>