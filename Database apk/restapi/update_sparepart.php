<?php 

require_once 'koneksi.php';

$key = isset($_POST['key']) ? $_POST['key'] : 'empty';


if ($key == "update" ){

    $id_sparepart = isset($_POST['ID_SPAREPART']) ? $_POST['ID_SPAREPART'] : 'empty';
    $nama_sparepart = isset($_POST['NAMA_SPAREPART']) ? $_POST['NAMA_SPAREPART'] : 'empty';
    $merk_sparepart = isset($_POST['MERK_SPAREPART']) ? $_POST['MERK_SPAREPART'] : 'empty';
    $tipe_sparepart = isset($_POST['TIPE_SPAREPART']) ? $_POST['TIPE_SPAREPART'] : 'empty';
    $hargabeli_sparepart = isset($_POST['HARGABELI_SPAREPART']) ? $_POST['HARGABELI_SPAREPART'] : 'empty';
    $hargajual_sparepart = isset($_POST['HARGAJUAL_SPAREPART']) ? $_POST['HARGAJUAL_SPAREPART'] : 'empty';
    $stok_sparepart = isset($_POST['STOK_SPAREPART']) ? $_POST['STOK_SPAREPART'] : 'empty';
    $stokoptimal_sparepart = isset($_POST['STOKOPTIMAL_SPAREPART']) ? $_POST['STOKOPTIMAL_SPAREPART'] : 'empty';
    $gambar_sparepart = isset($_POST['GAMBAR_SPAREPART']) ? $_POST['GAMBAR_SPAREPART'] : 'empty';
    $letak_sparepart = isset($_POST['LETAK_SPAREPART']) ? $_POST['LETAK_SPAREPART'] : 'empty';

    $query = "UPDATE tbl_sparepart SET NAMA_SPAREPART = '$nama_sparepart', MERK_SPAREPART = '$merk_sparepart', TIPE_SPAREPART = '$tipe_sparepart', HARGABELI_SPAREPART = '$hargabeli_sparepart', HARGAJUAL_SPAREPART = '$hargajual_sparepart', STOK_SPAREPART = '$stok_sparepart', STOKOPTIMAL_SPAREPART = '$stokoptimal_sparepart', LETAK_SPAREPART = '$letak_sparepart' WHERE ID_SPAREPART = $id_sparepart ";

        if ( mysqli_query($conn, $query) ){

            if ($gambar_sparepart == null) {

                $result["valueSparepart"] = "1";
                $result["messageSparepart"] = "Data sparepart berhasil diubah";
    
                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $path = "sparepart_picture/$id_sparepart.jpeg";
                $finalPath = "/restapi/".$path;

                $insert_picture = "UPDATE tbl_sparepart SET GAMBAR_SPAREPART='$finalPath' WHERE ID_SPAREPART='$id_sparepart' ";
            
                if (mysqli_query($conn, $insert_picture)) {
            
                    if ( file_put_contents( $path, base64_decode($gambar_sparepart) ) ) {
                        
                        $result["valueSparepart"] = "1";
                        $result["messageSparepart"] = "Data sparepart berhasil diubah!";
            
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