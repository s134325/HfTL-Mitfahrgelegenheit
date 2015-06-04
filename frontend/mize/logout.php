---logout.php---
<?php
// erstmal ne Session starten, damit wir sie auch löschen können
session_start();
// Die Besagte löschen
session_destroy();
header ("Location: login.html");
// und zurück zu login.html
?>