---logout.php---
<?php
// erstmal ne Session starten, damit wir sie auch l�schen k�nnen
session_start();
// Die Besagte l�schen
session_destroy();
header ("Location: login.html");
// und zur�ck zu login.html
?>