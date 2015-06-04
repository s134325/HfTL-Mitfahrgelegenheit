---register.php---
<?php
$user=$_POST["user"];
$pw=$_POST["pw1"];
$mail=$_POST["email"];
// Die Daten werden variablen zugewiesen, die wir später verwenden
function checkmail($email) {
// wir könnten hier den host testen, was aber bei 1und1 adressen fehlschlägt
if (strpos($email, "@")) {
    return true;
    // Die E-Mail adresse enthält ein @, also ist sie korekt
} else {
    return false;
    // Sie Enthält kein @, aslo ist sie nicht korrekt
}}
function create() {
   // Funktion zum erstellen des Bestätigungs-Codes
   $create_array=array(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
        "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
    $create_i=0;
    $created_output="";
    while ($create_i<=15) {
        $create_random=rand(1,26);
        $created_output.=$create_array[$create_random];
        $create_i++;
    }
    return $created_output;
}
//Fangen wir mit der eigentlichen datenverarbeitung an
if ($user!="") {
   // Der Benutzer ist nicht nix
   if ($pw!="" && $pw==$_POST["pw2"]) {
       // Das Passwort ist nicht nichts und stimmt mit dem bestätigungs-üw überein
       if (checkmail($mail)) {
           // Die E-Mail Stimmt
           $db=new mysqli("localhost", "root", "", "daten");
           // Datenbankverbindung

           $sql="INSERT INTO users(id, name, passwort, email) VALUES ('', '".$user."', '".md5($pw)."', '".$mail."')";
           // Befehl, die daten einzutragen
           if (@$db->query($sql)) {
               // Die Daten sind in der Datenbank, jetzt brauchen wir die id des nutzers
               $sql="SELECT id FROM users WHERE name='".$user."'";
               if (@$resultat=$db->query($sql)) {
                   // Daten abgerufen
                   if (@$erbeb=$resultat->fetch_array()) {
                       // Daten als array $ergeb geholt
                       $vcode=create();
                       // bestätigungscode
                       $sql="INSERT INTO bestatigung(acc, code) VALUES ('".$ergeb["id"]."', '".$vcode."')";
                       if (@$db->query($sql)) {
                           // Daten sind inner Datenbank, fertig!
                           @mail($mail, "Registreirung erfolgreich", "Hallo ".$user.",\nDeine Registrierung bei ~ war erfolgreich\nDeine Daten sind:\n".$user."\n".$pw."\n\n Um Deinen account zu aktivieren gehe nach http://deineseite.iwas/verify.php?".$vcode."\n Viele Grüße, dein ~ Team", "From: no-replay@supernaturetutorial.de");
                           //E-Mail an den Nutzer
                           echo "Registrierung Erolgreich. Es wurde eine Mail an ".$mail." gesendet";
                       } else {
                           echo "datenbankprobleme".$db->error;
                       }
                   } else {
                       echo "Datenbankprobleme".$db->error;
                   }
               } else {
                   echo "Datenbankprobleme".$db->error;
               }
           } else {
               echo "Datenbankprobleme".$db->error;
           }
       } else {
           echo "Keine gültige E-Mail Adresse";
       }
    } else {
        echo "die Passwörter stimmen nicht";
    }
} else {
    echo "Kein Benutzer angegeben";
}
@$db->close();
?>
// Schliessen der Datenbankverbindung