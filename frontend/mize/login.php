---login.php---
<?php
if (isset($_POST["user"]) && isset($_POST["pw"])) {
    $user=$_POST["user"];
    $pw=$_POST["pw"];
    @$db=new mysqli("localhost", "root", "idontwannastop!", "daten");
    $sql="SELECT id,passwort FROM users WHERE name='".$user."'";
    if (@$resultat=$db->query($sql)) {
        if ($ergeb=$resultat->fetch_array()) {
            // Wir kenne die Prozedur ja
            $sql="SELECT acc FROM bestatigung WHERE acc='".$ergeb["id"]."'";
            @$resultat=$db->query($sql);
            @$ergeb2=$resultat->fetch_array();
            if ($ergeb2[0]=="") {
                // Der Account ist Bestätigt
                if (md5($pw)==$ergeb["passwort"]) {
                    // Passwort ok, login
                    session_start();
                    $_SESSION["id"]=$ergeb["id"];
                    $_SESSION["name"]=$user;
                    echo "Eingeloggt";
                } else {
                    echo "Falsches Passwort".$db->error;
                }
            } else {
                echo "Account noch nicht bestätigt";
            }
        } else {
            echo "Der Benutzer ".$user." Existriert nicht";
        }
    } else {
        echo "Der Benutzer ".$user." Existriert nicht";
    }
} else {
    header ("Location: login.html");
}
?>