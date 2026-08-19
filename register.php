<?php
header('Content-Type: application/json');
require_once 'koneksi.php';

$jsonInput = file_get_contents('php://input');
$data = json_decode($jsonInput, true);

$nama     = $data['nama'] ?? '';
$username = $data['username'] ?? '';
$password = $data['password'] ?? '';
$alamat   = $data['alamat'] ?? '';
$telp     = $data['telp'] ?? '';

if (empty($nama) || empty($username) || empty($password)) {
    echo json_encode([
        "status"  => false, 
        "message" => "Nama, Username, dan Password wajib diisi!"
    ]);
    exit();
}

$checkSql = "SELECT id_pelanggan FROM pelanggan WHERE username = ?";
$stmtCheck = $conn->prepare($checkSql);
$stmtCheck->bind_param("s", $username);
$stmtCheck->execute();
$stmtCheck->store_result();

if ($stmtCheck->num_rows > 0) {
    echo json_encode([
        "status"  => false, 
        "message" => "Username sudah dipakai, gunakan username lain!"
    ]);
    exit();
}

$hashedPassword = password_hash($password, PASSWORD_BCRYPT);
$sql = "INSERT INTO pelanggan (nama, username, password, alamat, telp) VALUES (?, ?, ?, ?, ?)";
$stmt = $conn->prepare($sql);
$stmt->bind_param("sssss", $nama, $username, $hashedPassword, $alamat, $telp);

if ($stmt->execute()) {
    echo json_encode([
        "status"       => true,
        "message"      => "Registrasi berhasil!",
        "id_pelanggan" => $stmt->insert_id
    ]);
} else {
    echo json_encode([
        "status"  => false, 
        "message" => "Registrasi gagal: " . $conn->error
    ]);
}
?>