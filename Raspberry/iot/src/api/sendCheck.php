<?php
$tempOk=$_GET['tempOk'];
$humOk=$_GET['humOk'];

$command = escapeshellcmd('sudo python3 ../python/onLeds.py'." ".$tempOk." ".$humOk);

shell_exec($command);

header("HTTP/1.0 200 Ok");
header('Content-type: application/json');

echo "{}";            
?>
