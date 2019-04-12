<?php
    include_once("config.php");

    $medthod = $_POST["medthod"];
    switch ($medthod) {
        case 'layDanhSachMenu':
            $medthod($conn);
            break;

        case 'dangKyThanhVien':
            $medthod($conn);
            break;
        case 'kiemTraDangNhap':
            $medthod($conn);
            break;
    }

    function layDanhSachMenu($conn){
        $maloaicha = $_POST["maloaicha"];
        $truyvan = "SELECT * FROM loaisanpham WHERE MALOAI_CHA=".$maloaicha;
        $ketqua = mysqli_query($conn, $truyvan);
        $chuoiJson = array();
        echo "{";
        echo "\"LOAISANPHAM\":";
        if($ketqua){
            while($dong=mysqli_fetch_array($ketqua)){
                array_push($chuoiJson, array("MALOAISP" => $dong["MALOAISP"], "TENLOAISP" => $dong["TENLOAISP"], "MALOAI_CHA" => $dong["MALOAI_CHA"]));
            }
            echo json_encode($chuoiJson, JSON_UNESCAPED_UNICODE);
        }
        echo "}";

        mysqli_close($conn);
    }

    function dangKyThanhVien($conn){
        
        $tenNhanVien = $_POST["tenNhanVien"];
        $tenDangNhap = $_POST["tenDangNhap"];
        $matKhau = $_POST["matKhau"];
        $maLoaiNhanVien = $_POST["maLoaiNhanVien"];
        $nhanKhuyenMai = $_POST["nhanKhuyenMai"];

        $queryRegister = "INSERT INTO nhanvien(	TENNV, TENDANGNHAP, MATKHAU, MALOAINV, NHANKHUYENMAI) 
            VALUES('".$tenNhanVien."', '".$tenDangNhap."', '".$matKhau."', '".$maLoaiNhanVien."', '".$nhanKhuyenMai."')";

        if (mysqli_query($conn, $queryRegister)){
            echo "{ ketQua : true }";
        } else {
            echo "{ ketQua : false }".mysqli_error($conn);
        }

        mysqli_close($conn);
    }

    function kiemTraDangNhap($conn){
        $tenDangNhap = $_POST["tenDangNhap"];
        $matKhau = $_POST["matKhau"];

        $queryDangNhap = "SELECT * FROM nhanvien WHERE TENDANGNHAP = '".$tenDangNhap."' AND MATKHAU = '".$matKhau."' ";
        $ketqua = mysqli_query($conn, $queryDangNhap);
        $count = mysqli_num_rows($ketqua);
        if($count > 0){
            echo "{ ketQua : true }";
        } else {
            echo "{ ketQua : false }".mysqli_error($conn);
        }
    }
?>
