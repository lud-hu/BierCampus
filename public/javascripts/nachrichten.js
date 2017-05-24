//laedt standardmaessig das Form zur Eingabe fuer eine Nachricht, wenn die seite fertig geladen ist
//https://api.jquery.com/ready/
$(document).ready(function(){
    //schreibeNachricht();
});

//tutorial: fuer AJAX mit Play, das gut ist: https://dzone.com/articles/jquery-ajax-play-2
//laedt die ausgewaehlte Nachricht via JSON und AJAX in den Anzeigebereich, voll Webentwickelung und so
function zeigeNachrichtAn(nachrichtenID) {
    //Zeigt den Button wieder an der zum Nachtschrieben-Form fuehrt
    //document.getElementById("nachrichtSchreibenButton").style.visibility = "visible";
    document.getElementById("nachrichtenAnzeige").style.visibility = "visible";
    document.getElementById("nachrichtSchreibenForm").style.visibility = "hidden";
    document.getElementById("nachrichtSchreibenForm").innerHTML = "";

    //Zwischenspeicher als JSON
    var nachrichtJson = {"nachrichtenID": nachrichtenID};
    //laden des HTML-Snippets mit der Nachricht vom Server
    $.get("/nachrichten/nachrichtLesen",nachrichtJson, function(data){
        //ersetzt das Nachrichten-Form durch die anzuzeigende Nachricht
        document.getElementById("nachrichtenAnzeige").innerHTML = data});

}

//laedt wieder das Form zum Verfassen einer Nachricht
function schreibeNachricht() {
    //versteckt den Button wieder an der zum Nachtschrieben-Form fuehrt
    //document.getElementById("nachrichtSchreibenButton").style.visibility = "hidden";
    document.getElementById("nachrichtSchreibenForm").style.visibility = "visible";
    document.getElementById("nachrichtenAnzeige").style.visibility = "hidden";
    document.getElementById("nachrichtenAnzeige").innerHTML = "";
    //laedtdt das Form als HTML-Snippets zur Nachrichten und zeigt es an
   $("#nachrichtSchreibenForm").load("/nachrichten/nachrichtSchreiben");
}

