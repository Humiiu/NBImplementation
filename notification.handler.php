<?php
header('Content-Type: application/json');
require_once 'koneksi.php';
require_once 'config_payment.php';

$jsonInput = file_get_contents('php://input');
$notification = json_decode($jsonInput, true);

$transaction_status = $notification['transaction_status'] ?? '';
$order_id           = $notification['order_id'] ?? '';

if ($transaction_status == 'capture' || $transaction_status == 'settlement') {
    $sqlUpdate = "UPDATE penjualan SET status_pembayaran = 'PAID' WHERE order_id = ?";
    $stmt = $conn->prepare($sqlUpdate);
    $stmt->bind_param("s", $order_id);
    $stmt->execute();

    $sqlStok = "UPDATE produk p 
                JOIN detail_penjualan dp ON p.kode_produk = dp.kode_produk 
                JOIN penjualan pj ON dp.id_penjualan = pj.id_penjualan 
                SET p.stok = p.stok - dp.jumlah_produk 
                WHERE pj.order_id = ?";
    $stmtStok = $conn->prepare($sqlStok);
    $stmtStok->bind_param("s", $order_id);
    $stmtStok->execute();

} else if (in_array($transaction_status, ['cancel', 'deny', 'expire'])) {
    $sqlUpdate = "UPDATE penjualan SET status_pembayaran = 'FAILED' WHERE order_id = ?";
    $stmt = $conn->prepare($sqlUpdate);
    $stmt->bind_param("s", $order_id);
    $stmt->execute();
}

echo json_encode(["status" => "OK"]);
?>