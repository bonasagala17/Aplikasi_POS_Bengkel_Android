<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'koneksi.php';

$query = "SELECT * FROM tbl_pegawai WHERE ROLE = 'Montir' ORDER BY id_pegawai DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

   while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'ID_PEGAWAI'        =>$row['ID_PEGAWAI'], 
        'NAMA_PEGAWAI'      =>$row['NAMA_PEGAWAI'], 
        'ALAMAT_PEGAWAI'    =>$row['ALAMAT_PEGAWAI'],
        'TELP_PEGAWAI'      =>$row['TELP_PEGAWAI'],
        'GAJI_PEGAWAI'      =>$row['GAJI_PEGAWAI'],
        'ROLE'              =>$row['ROLE'],
        'USERNAME'          =>$row['USERNAME'],
        'PASSWORD'          =>$row['PASSWORD']) 
    );
}
        
echo json_encode($response);

mysqli_close($conn);

?>