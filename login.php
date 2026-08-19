<?php
header('Content-Type: application/json');
require_once 'koneksi.php';

$jsonInput = file_get_contents('php://input');
$data = json_decode($jsonInput, true);

$username = $data['username'] ?? '';
$password = $data['password'] ?? '';

if (empty($username) || empty($password)) {
    echo json_encode([
        "status"  => false, 
        "message" => "Username dan Password wajib diisi!"
    ]);
    exit();
}

$sql = "SELECT id_pelanggan, nama, username, password, alamat, telp FROM pelanggan WHERE username = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $username);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    $pelanggan = $result->fetch_assoc();

    if (password_verify($password, $pelanggan['password'])) {
        unset($pelanggan['password']);  

        echo json_encode([
            "status"  => true,
            "message" => "Login Berhasil!",
            "data"    => $pelanggan
        ]);
    } else {
        echo json_encode([
            "status"  => false, 
            "message" => "Password salah!"
        ]);
    }
} else {
    echo json_encode([
        "status"  => false, 
        "message" => "Username tidak ditemukan!"
    ]);
}
?>