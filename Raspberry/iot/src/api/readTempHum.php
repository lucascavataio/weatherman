<?php
exec("sudo python3 ../python/readTempHum.py ", $out, $resp);
header("HTTP/1.0 200 Ok");
header('Content-type: application/json');
echo implode($out);

?>
