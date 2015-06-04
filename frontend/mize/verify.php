---verify.php---
<?php
foreach ($_GET as $key=>$val) {
    $code=$key;
}
// Auslesen der GET-Variable
if (!empty($code)) {
    // Code übergeben
    @$db=new mysqli("localhost", "root", "", "daten");
    // Datenbankverbindung
    $sql="DELETE FROM bestatigung WHERE code='".$code."'";
    if (@$db->query($sql)) {
        echo "Account freigeschaltet";
    } else {
        echo "Datenbankprobleme";
    }
    @$db->close();
} else {
    header ("Location: register.html");
    // Kein Code, auf zu register.html
}
?>