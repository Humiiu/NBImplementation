<?php
$host = "localhost";
$user = "root";
$pass = "";
$db   = "penjualan";

$conn = new mysqli($host, $user, $pass, $db);

if ($conn->connect_error) {
    die(json_encode([
        "status" => false,
        "message" => "Koneksi Database Gagal: " . $conn->connect_error
    ]));
}
?>