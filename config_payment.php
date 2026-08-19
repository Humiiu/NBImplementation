<?php
define('MIDTRANS_SERVER_KEY', 'SB-Mid-server-aDhKagekjzFq0dMroQEpIR6m');
define('MIDTRANS_IS_PRODUCTION', false);

if (MIDTRANS_IS_PRODUCTION) {
    define('MIDTRANS_SNAP_URL', 'https://app.midtrans.com/snap/v1/transactions');
} else {
    define('MIDTRANS_SNAP_URL', 'https://app.sandbox.midtrans.com/snap/v1/transactions');
}
?>