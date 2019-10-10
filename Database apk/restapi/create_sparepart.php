<?php 

require_once 'koneksi.php';

$key = isset($_POST['key']) ? $_POST['key'] : 'empty';

$nama_sparepart = isset($_POST['NAMA_SPAREPART']) ? $_POST['NAMA_SPAREPART'] : 'empty';
$merk_sparepart = isset($_POST['MERK_SPAREPART']) ? $_POST['MERK_SPAREPART'] : 'empty';
$tipe_sparepart = isset($_POST['TIPE_SPAREPART']) ? $_POST['TIPE_SPAREPART'] : 'empty';
$hargabeli_sparepart = isset($_POST['HARGABELI_SPAREPART']) ? $_POST['HARGABELI_SPAREPART'] : 'empty';
$hargajual_sparepart = isset($_POST['HARGAJUAL_SPAREPART']) ? $_POST['HARGAJUAL_SPAREPART'] : 'empty';
$stok_sparepart = isset($_POST['STOK_SPAREPART']) ? $_POST['STOK_SPAREPART'] : 'empty';
$stokoptimal_sparepart = isset($_POST['STOKOPTIMAL_SPAREPART']) ? $_POST['STOKOPTIMAL_SPAREPART'] : 'empty';
$gambar_sparepart = isset($_POST['GAMBAR_SPAREPART']) ? $_POST['GAMBAR_SPAREPART'] : 'empty';
$letak_sparepart = isset($_POST['LETAK_SPAREPART']) ? $_POST['LETAK_SPAREPART'] : 'empty';

if ( $key == "insert" ){

    $query = "INSERT INTO tbl_sparepart (NAMA_SPAREPART, MERK_SPAREPART, TIPE_SPAREPART, HARGABELI_SPAREPART, HARGAJUAL_SPAREPART, STOK_SPAREPART, STOKOPTIMAL_SPAREPART, GAMBAR_SPAREPART, LETAK_SPAREPART)
    VALUES ('$nama_sparepart', '$merk_sparepart', '$tipe_sparepart', '$hargabeli_sparepart', '$hargajual_sparepart', '$stok_sparepart', '$stokoptimal_sparepart', '$gambar_sparepart', '$letak_sparepart') ";

        if ( mysqli_query($conn, $query) ){

            if ($gambar_sparepart == null) {

                $finalPath = "/restapi/sparepart_logo.png"; 
                $result["valueSparepart"] = "1";
                $result["messageSparepart"] = "Data sparepart berhasil dibuat";
    
                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $id_sparepart = mysqli_insert_id($conn);
                $path = "sparepart_picture/$id_sparepart.jpeg";
                $finalPath = "/restapi/".$path;

                $insert_picture = "UPDATE tbl_sparepart SET GAMBAR_SPAREPART='$finalPath' WHERE ID_SPAREPART='$id_sparepart' ";
            
                if (mysqli_query($conn, $insert_picture)) {
            
                    if ( file_put_contents( $path, base64_decode($gambar_sparepart) ) ) {
                        
                        $result["valueSparepart"] = "1";
                        $result["messageSparepart"] = "Data sparepart berhasil dibuat";
            
                        echo json_encode($result);
                        mysqli_close($conn);
            
                    } else {
                        
                        $response["valueSparepart"] = "0";
                        $response["messageSparepart"] = "Error! ".mysqli_error($conn);
                        echo json_encode($response);

                        mysqli_close($conn);
                    }

                }
            }

        } 
        else {
            $response["valueSparepart"] = "0";
            $response["messageSparepart"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
        }
}

?>