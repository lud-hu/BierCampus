//das skript ist im Wesentlichen von den Nachrichten uebernommen

//fuegt das Profil des ausgewaehlten Freundes in die Setite ein
function zeigeFreundesProfilAn(freundID) {

    document.getElementById("angezeigterFreund").style.visibility = "visible";
    document.getElementById("freundeHinzufuegenForm").style.visibility = "hidden";
    document.getElementById("freundeHinzufuegenForm").innerHTML = "";
    //document.getElementById("freundHinzufuegenButton").style.visibility = "hidden";
    //document.getElementById("freundeDropDown").style.visibility = "hidden";
    //Zwischenspeicher als JSON
    var freundJson = {"freundID": freundID};
    //laden des HTML-Snippets mit dem Profil des Freundes vom Server
    $.get("/freunde/zeigeProfilAn",freundJson, function(data){
        //ersetzt den Hilfstext durch das Freundesprofil
        document.getElementById("angezeigterFreund").innerHTML = data});
}
//Laedt die Suchmaske fuer neue Freunde
function neueFreundeFinden() {
    //document.getElementById("freundeHinzufuegenButton").style.visibility = "hidden";

    document.getElementById("freundeHinzufuegenForm").style.visibility = "visible";
    document.getElementById("angezeigterFreund").style.visibility = "hidden";
    document.getElementById("angezeigterFreund").innerHTML = "";
    //ladt die Suchmaske zum Freundehinzufuegen
    $("#freundeHinzufuegenForm").load("/freunde/freundeSuche");
}