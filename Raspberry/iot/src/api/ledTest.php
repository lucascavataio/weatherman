<?php
exec("sudo python3 ../python/ledsTest.py ", $out, $resp);
header("HTTP/1.0 200 Ok");
header('Content-type: application/json');

echo "{}";   

?>
