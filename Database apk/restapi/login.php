<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    

    $username = isset($_POST['USERNAME']) ? $_POST['USERNAME'] : 'empty';
    $password = isset($_POST['PASSWORD']) ? $_POST['PASSWORD'] : 'empty';

    require_once 'koneksi.php';

    $sql = "SELECT * FROM tbl_pegawai WHERE USERNAME='$username' && PASSWORD='$password' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();
    
    if ( mysqli_num_rows($response) === 1 ) {
        
        $row = mysqli_fetch_assoc($response);

        if ( password_verify($password, $row['PASSWORD']) ) {
            
            $index['NAMA_PEGAWAI'] = $row['NAMA_PEGAWAI'];
            $index['ROLE'] = $row['ROLE'];
            array_push($result['login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";
            echo json_encode($result);

            mysqli_close($conn);

        } else {

            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($conn);

        }

    }

}

?>