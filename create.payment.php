<?php
header('Content-Type: application/json');
require_once 'koneksi.php';
require_once 'config_payment.php';

$jsonInput = file_get_contents('php://input');
$data = json_decode($jsonInput, true);

$id_pelanggan = $data['id_pelanggan'] ?? null;
$items        = $data['items'] ?? []; // Array isi: [{kode_produk, jumlah_produk, subtotal}]
$gross_amount = $data['gross_amount'] ?? 0;

if (!$id_pelanggan || empty($items) || $gross_amount <= 0) {
    echo json_encode(["status" => false, "message" => "Data checkout tidak valid!"]);
    exit();
}

$order_id = 'TRX-' . time() . '-' . rand(100, 999);

$sql = "INSERT INTO penjualan (order_id, tanggal_penjualan, total_harga, status_pembayaran, id_pelanggan) 
        VALUES (?, CURDATE(), ?, 'PENDING', ?)";
$stmt = $conn->prepare($sql);
$stmt->bind_param("sdi", $order_id, $gross_amount, $id_pelanggan);

if ($stmt->execute()) {
    $id_penjualan = $stmt->insert_id;

    foreach ($items as $item) {
        $sqlDetail = "INSERT INTO detail_penjualan (id_penjualan, kode_produk, jumlah_produk, subtotal) VALUES (?, ?, ?, ?)";
        $stmtDetail = $conn->prepare($sqlDetail);
        $stmtDetail->bind_param("isid", $id_penjualan, $item['kode_produk'], $item['jumlah_produk'], $item['subtotal']);
        $stmtDetail->execute();
    }

    // 3. Minta Snap Token dari Midtrans
    $payload = [
        'transaction_details' => [
            'order_id'     => $order_id,
            'gross_amount' => (int)$gross_amount
        ]
    ];

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, MIDTRANS_SNAP_URL);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($payload));
    curl_setopt($ch, CURLOPT_HTTPHEADER, [
        'Content-Type: application/json',
        'Accept: application/json',
        'Authorization: Basic ' . base64_encode(MIDTRANS_SERVER_KEY . ':')
    ]);

    $response = curl_exec($ch);
    curl_close($ch);

    $result = json_decode($response, true);

    if (isset($result['token'])) {
        echo json_encode([
            "status"       => true,
            "message"      => "Transaksi berhasil dibuat",
            "id_penjualan" => $id_penjualan,
            "order_id"     => $order_id,
            "snap_token"   => $result['token'],
            "redirect_url" => $result['redirect_url']
        ]);
    } else {
        echo json_encode([
            "status"  => false,
            "message" => "Gagal mendapatkan token Midtrans",
            "error"   => $result
        ]);
    }
} else {
    echo json_encode(["status" => false, "message" => "Gagal menyimpan transaksi ke database"]);
}
?>