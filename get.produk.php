<?php
header('Content-Type: application/json');
require_once 'koneksi.php';

$sql = "SELECT kode_produk, nama_produk, harga, stok FROM produk WHERE stok > 0";
$result = $conn->query($sql);

$produk_list = [];
while ($row = $result->fetch_assoc()) {
    $produk_list[] = $row;
}

echo json_encode([
    "status" => true,
    "data"   => $produk_list
]);
?>