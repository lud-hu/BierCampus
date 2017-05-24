//Array für die Schritte
schritte = [];
//Anzahl der Schritte
anzahl = 0;
//Progress
fortschritt = 0;

// Lade Schritte für das Level aus der Datenbank
function starteSeite() {
    $.ajax({
        url: '/spiel/bekommeReihenfolge',
        success: function (data) {
            ordneReihenfolge(data);
        }
    });
}

// Füge alle Zutaten in ein array ein
function bekommezutaten(schritte) {
    var zutaten = [];
    var j = 0;
    for (var i = 0; i < schritte.length; i++) {
        if (schritte[i].length == 2) {
            zutaten [j] = schritte[i];
            j++;
        }
    }
    return zutaten;
}

// Füge alle Fragen in ein Array ein
function bekommefragen(schritte) {
    var fragen = [];
    var j = 0;
    for (var i = 0; i < schritte.length; i++) {
        if (!(schritte[i].length == 2)) {
            fragen[j] = schritte[i];
            j++;
        }
    }
    return fragen;
}

// Drag und drop für die Bilder
function draganddrop(zutaten, fragen) {
    $(function () {
        $(".zutat").draggable({
            revert: "invalid",
            cursor: "move",
            opacity: 1,
            snap: ".dropfield",
            snapMode: "inner",
            stack: ".zutat",
            start: function (event, ui) {
                //Bei Start des Drags wird die Frage unsichtbar
                document.getElementById("infotext").innerHTML = "";
                document.getElementById("loesung").innerHTML = "";
                document.getElementById("antwort1").innerHTML = "";
                document.getElementById("antwort2").innerHTML = "";
                document.getElementById("antwort3").innerHTML = "";
                document.getElementById("antwort4").innerHTML = "";
                document.getElementById("antwort1").style.visibility = 'hidden';
                document.getElementById("antwort2").style.visibility = 'hidden';
                document.getElementById("antwort3").style.visibility = 'hidden';
                document.getElementById("antwort4").style.visibility = 'hidden';
            }
        });
        $("#dropfield").droppable({
            accept: ".zutat",
            drop: function (event, ui) {
                $(this)
                    // akzeptiert nur zutaten
                .find(".zutat");
                // Bekomme das Bild und den Beschreibungstext
                var img = document.getElementById(ui.draggable.attr("id"));
                var text = document.getElementById(ui.draggable.attr("id")).alt;
                // Wenn das das richtige Bild war dann füge es zum Kessel.
                if (text == zutaten[0][0]) {
                    // ProgressBar aktualisieren
                    fortschritt = fortschritt + anzahl;
                    document.getElementById("progressBarFilling").style.width = fortschritt + "%";
                    document.getElementById("progressBarText").innerHTML = Math.round(fortschritt) + "%";
                    //Wenn Element in KEssel gedroppt -> Text dazu ausblenden
                    document.getElementById(ui.draggable.attr("id") + "Text").style.visibility = 'hidden';
                    // Wenn es das letzte Element ist, dann füge kein Komma an
                    if (typeof zutaten[1] == 'undefined') {
                        $('#text').append(text);
                    }
                    else {
                        $('#text').append(text + ", ");
                    }
                    // Lösche das Bild, wenn es in den Kessel gedroppt wurde
                    img.parentNode.removeChild(img);
                    // Zutat aus dem Array Zutaten löschen
                    zutaten.splice(0, 1);
                    //Prüfe ob der nächste Schritt eine Frage ist
                    if (schritte.length > 0 && fragen.length > 0) {
                        if (schritte[1] == fragen[0]) {
                            // Zeige die Frage an
                            zeigeFrage(fragen, zutaten);
                        }
                    }
                    // Zutat aus dem Array Schritte löschen
                    schritte.splice(0, 1);
                    // Prüfe ob das Level fertig ist
                    levelAbgeschlossen(zutaten, fragen);
                }
                // Wenn es die falsche Zutat war, gebe Fehlermeldung und lade die Seite neu
                else {
                    document.getElementById("texteUnten").style.borderColor = '#fffff0';
                    document.getElementById("kessel").style.borderColor = '#fffff0';
                    toast("Das war die falsche Zutat, probiere das Level von vorne!");
                    // Lädt das Level nach einem Timeout neu
                    setTimeout(function () {
                        location.reload(true)
                    }, 5000);
                }

            }

        });

    });
}

// prüfe per ajax ob die Antwort korrekt ist
function checkeAntwort(antwort, fragen, zutaten, id) {
    var fragenid = fragen[0][5];
    var levelid = fragen[0][6];
    // Daten für den SpielController
    var d = [levelid, fragenid, antwort];
    $.ajax({
        url: '/spiel/checkeAntwort',
        data: JSON.stringify(d),
        dataType: 'text',
        contentType: 'application/json',
        type: 'POST',
        // Bei Erfolg zeige die Auflösung
        success: function (data) {
            // Zeige die Lösung an
            document.getElementById("loesung").innerHTML = data;
            document.getElementById(id).style.borderColor = '#64FE2E';
            // Lösche die gestellte Frage aus Schritte und Fragen
            schritte.splice(0, 1);
            fragen.splice(0, 1);
            // ProgressBar aktualisieren
            fortschritt = fortschritt + anzahl;
            document.getElementById("progressBarFilling").style.width = fortschritt + "%";
            document.getElementById("progressBarText").innerHTML = Math.round(fortschritt) + "%";
            // Wenn der nächste Schritt eine Frage ist, dann zeige die Frage an
            if (schritte[0] == fragen[0] && fragen.length > 0) {
                $(".zutat").off("click");
                $(".zutat").draggable('disable');
                document.getElementById("naechsteFrage").style.visibility = 'visible';
                $("#naechsteFrage").click(function (event) {
                    // Wenn Level fertig
                    levelAbgeschlossen(zutaten, fragen);
                    zeigeFrage(fragen, zutaten);
                    document.getElementById("loesung").innerHTML = "";
                    document.getElementById("naechsteFrage").style.visibility = 'hidden';
                    $("#naechsteFrage").off("click");
                });
            }
            // Nächster Schritt ist eine Zutat, View manipulieren
            else {
                document.getElementById("naechsteFrage").style.visibility = 'visible';
                $("#naechsteFrage").click(function (event) {
                    // Wenn Level fertig
                    levelAbgeschlossen(zutaten, fragen);
                    $(".zutat").draggable('enable');
                    blendeFrageAus();
                    bekommeInfo(zutaten);
                    document.getElementById("loesung").innerHTML = "";
                    document.getElementById("naechsteFrage").style.visibility = 'hidden';
                    $("#naechsteFrage").off("click");
                });
                document.getElementById("texteUnten").style.borderColor = '#fffff0';
                document.getElementById("kessel").style.borderColor = '#64FE2E';
            }
        },
        // Bei Fehler lade die Seite neu
        error: function (data) {
            document.getElementById(id).style.borderColor = '#fe3842';
            toast("Das war die falsche Antwort, probiere das Level von vorne!");
            // Nach einem Timeout das Level neu laden
            setTimeout(function () {
                location.reload(true)
            }, 5000);
        }
    });
}

// Clickhandler für die Antwortmöglichkeiten
function setHandlerAntworten(fragen, zutaten) {
    for (var i = 1; i < 5; i++) {
        $("#antwort" + i).click(function () {
            var antwort = $(this).text();
            var id = $(this).attr("id");
            // Prüfe ob die Antwort korrekt war
            checkeAntwort(antwort, fragen, zutaten, id);
            // Clickhandler wieder deaktivieren
            $(".antwort").off("click");
        });
    }
    return fragen;
}

// Fragen werden angezeigt
function zeigeFrage(fragen, zutaten) {
    fragen = setHandlerAntworten(fragen, zutaten);
    document.getElementById("infotext").innerHTML = fragen[0][0];

    // Markiere die Border der Frage
    document.getElementById("texteUnten").style.borderColor = '#64FE2E';
    document.getElementById("kessel").style.borderColor = '#fffff0';

    //aktviere antwort divs:
    document.getElementById("antwort1").style.visibility = 'visible';
    document.getElementById("antwort2").style.visibility = 'visible';
    document.getElementById("antwort3").style.visibility = 'visible';
    document.getElementById("antwort4").style.visibility = 'visible';
    //setze Frage zurück
    document.getElementById("antwort1").style.borderColor = '#fffff0';
    document.getElementById("antwort2").style.borderColor = '#fffff0';
    document.getElementById("antwort3").style.borderColor = '#fffff0';
    document.getElementById("antwort4").style.borderColor = '#fffff0';

    //text von antwort divs setzen:
    document.getElementById("antwort1").innerHTML = fragen[0][1];
    document.getElementById("antwort2").innerHTML = fragen[0][2];
    document.getElementById("antwort3").innerHTML = fragen[0][3];
    document.getElementById("antwort4").innerHTML = fragen[0][4];

    // Drag und Click für die Zutaten deaktiviert
    $(".zutat").off("click");
    $(".zutat").draggable('disable');
}

// Ein Level fängt immer mit einer Zutat an
function onLoad() {
    $(document).ready(function () {
        // Zahl für die Progressbar erhalten
        anzahl = 100 / schritte.length;
        // Zutaten und Fragen in ein eigenes Array
        var zutaten = bekommezutaten(schritte);
        var fragen = bekommefragen(schritte);
        bekommeInfo(zutaten);
        document.getElementById("texteUnten").style.borderColor = '#fffff0';
        document.getElementById("kessel").style.borderColor = '#64FE2E';
        draganddrop(zutaten, fragen);
    });
}

// Clickhanlder für die Zutaten, bekommt Information was die jeweilige Zutat ist
function bekommeInfo(zutaten) {
    $(".zutat").click(function (event) {
        var alt = $(this).attr("alt");
        for (var i = 0; i < zutaten.length; i++) {
            if (zutaten[i][0] == alt) {
                document.getElementById("infotext").innerHTML = zutaten[i][1];
            }
        }
    });
}

// Blendet die Frage aus, weil der nächste Schritt eine Zutat ist
function blendeFrageAus() {
    document.getElementById("infotext").innerHTML = "";
    document.getElementById("loesung").innerHTML = "";
    document.getElementById("antwort1").innerHTML = "";
    document.getElementById("antwort2").innerHTML = "";
    document.getElementById("antwort3").innerHTML = "";
    document.getElementById("antwort4").innerHTML = "";
    document.getElementById("antwort1").style.visibility = 'hidden';
    document.getElementById("antwort2").style.visibility = 'hidden';
    document.getElementById("antwort3").style.visibility = 'hidden';
    document.getElementById("antwort4").style.visibility = 'hidden';
}

// Prüft, ob das Level abgeschlossen ist
function levelAbgeschlossen(zutaten, fragen) {
    if (schritte.length == 0 && zutaten.length == 0 && fragen.length == 0) {
        document.getElementById("texteUnten").style.borderColor = '#fffff0';
        document.getElementById("kessel").style.borderColor = '#fffff0';
        $.ajax({
            url: '/spiel/speicherErfahrung',
            type: 'GET',
            // Bei Erfolg zeige die Auflösung und beende das Level
            success: function (data) {
                zeigeLevelAbgeschlossen(data);

            },
            error: function (data) {
                alert("Es ist etwas schief gelaufen, melde dich bitte bei unserem Support!");
            }
        });
    }

}

// Manipuliert die view, dass die Auflösung des Levels, nämlich die erhaltene Erfahrung und das Abzeichen angezeigt wird
function zeigeLevelAbgeschlossen(data) {
    document.getElementById("infotext").innerHTML = data;
    document.getElementById("naechsteFrage").style.visibility = 'visible';
    document.getElementById("info").innerHTML = "";
    document.getElementById("antwort1").innerHTML = "";
    document.getElementById("antwort1").style.visibility = 'hidden';
    document.getElementById("antwort2").innerHTML = "";
    document.getElementById("antwort2").style.visibility = 'hidden';
    document.getElementById("antwort3").innerHTML = "";
    document.getElementById("antwort3").style.visibility = 'hidden';
    document.getElementById("antwort4").innerHTML = "";
    document.getElementById("antwort4").style.visibility = 'hidden';
    document.getElementById("loesung").innerHTML = "";
    document.getElementById("loesung").style.visibility = 'hidden';
    // Manipuliert einen Button, dass das neue Level geladen werden kann
    document.getElementById("naechsteFrage").innerHTML = "Lade das nächste Level!";
    $("#naechsteFrage").click(function (event) {
        location.reload(true);
    });
}

// Speichert die Daten von dem Controller als Array in der Variable Schritte ab und ruft document.ready auf
function ordneReihenfolge(data) {
    var s = data.split("}");
    for (var i = 0; i < s.length; i++) {
        schritte[i] = s[i].split("+");
    }
    schritte.splice(schritte.length - 1, 1);
    onLoad();
}

// Zeigt verschiedene Texte als Toast an
function toast(nachricht) {
    // Get the snackbar DIV
    var x = document.getElementById("snackbar");
    x.innerHTML = nachricht;
    // Add the "show" class to DIV
    x.className = "show";

    // After 10 seconds, remove the show class from DIV
    setTimeout(function () {
        x.className = x.className.replace("show", "");
    }, 10000);
}

// Funktion, die ausgeführt wird um das Level zu laden, bevor das Dokument komplett geladen ist
starteSeite();

