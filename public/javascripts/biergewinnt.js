var image_Spieler = new Image();

// Bild der KI
var image_Ki = new Image();
image_Ki.src = "assets/images/abzeichenKI.png";
var spielerName;

// Lädt das aktuelle abzeichen vom user
function bekommeAbzeichen() {
    $.get('/bierGewinnt/bekommeAbzeichen',
        function (data) {
            image_Spieler.src = data;
        }
    );
}

// Lädt den Namen vom User
function bekommeSpielername() {
    $.get('/bierGewinnt/bekommeSpielername',
        function (data) {
            spielerName = data;
        }
    );
}

//Größe des Spielfeldes
zeile = 6;
spalte = 7;

schritte = 0;

// Schwierigkeit 1 = schwer, 0 = leicht
schwierigkeit = 0;

//Gewinn Bedingung
bier = 4;

//Variable fuer die Geschwindigkeit des Ki Zuges
schlafeFuerZeit = 1000;

//Mensch startet, wenn der Boolean true ist
menschStartet = true;

//Deklarierung des Booleans, ob der menschliche Spieler am Zug ist
isYourTurn = true;

// Click handler für start
function start() {
    $("#start").click(function () {
        isYourTurn = menschStartet;
        zeigeSpiel();
        //Schwierigkeit soll nicht mehr umgestellt werden
        $("#leicht").off("click");
        $("#schwer").off("click");
        $("#start").off("click");
        $("#mStart").off("click");
        $("#kiStart").off("click");
        // Für die Erweiterung, dass das Spielfeld beliebig groß ist
        //getZeile();
        //getSpalte();
        map = new Array(zeile);
        for (var i = 0; i < zeile; ++i) {
            map[i] = new Array(spalte);
            for (var j = 0; j < spalte; j++) {
                map[i][j] = 0;
            }
        }
        // Wenn der Computer anfangen soll, dann führe einmal den Zug der Ki aus
        if (!isYourTurn) {
            if (schwierigkeit == 1) {
                zugKiMetrik();
            }
            else {
                zugKi();
            }
        }
        for (var j = 0; j < spalte; j++) {
            setSpalteClickHandler(j);
        }
    });
}

// click handler für schwierigkeit
function schwierig() {
    $("#leicht").click(function () {
        schwierigkeit = 0;
    });
    $("#schwer").click(function () {
        schwierigkeit = 1;
    });
}

// click handler wer anfängt
function anfang() {
    $("#mStart").click(function () {
        menschStartet = true;
    });
    $("#kiStart").click(function () {
        menschStartet = false;
    });

}

// Spielfeld wird angezeigt und die Select Buttons werden ausgeschaltet
function zeigeSpiel() {

    document.getElementById("bier").style.visibility = "visible";
    document.getElementById("neustart").style.visibility = "visible";
    document.getElementById("mStart").disabled = true;
    document.getElementById("kiStart").disabled = true;
    document.getElementById("leicht").disabled = true;
    document.getElementById("schwer").disabled = true;

}


//Onload Initiallisierung der ClickHandler
$(document).ready(function () {
    bekommeAbzeichen();
    bekommeSpielername();
    schwierig();
    anfang();
    start();
    $("#neustart").click(function () {
        again();
        schwierig();
        start();
        anfang();
        // Funktionen um die Tabelle und das Array zurück zu setzen
        for (var j = 0; j < spalte; j++) {
            $(".spalte" + j).off("click");
        }
        map = [];
        $("#bier tr td img").attr("src", "assets/images/bierOhne.png");
        $("#bier .bier-highlight").removeClass("bier-highlight");
    });
});

// Manipulierung der View
function again() {
    document.getElementById("bier").style.visibility = "hidden";
    document.getElementById("neustart").style.visibility = "hidden";
    document.getElementById("level").style.visibility = "visible";
    document.getElementById("leicht").style.visibility = "visible";
    document.getElementById("schwer").style.visibility = "visible";
    document.getElementById("start").style.visibility = "visible";

    document.getElementById("mStart").disabled = false;
    document.getElementById("kiStart").disabled = false;
    document.getElementById("leicht").disabled = false;
    document.getElementById("schwer").disabled = false;

}
// Speichert die Erfahrung und gibt einen String zurücl
function speicherErfahrung() {
    // post befehl an den Server
    $.ajax({
        url: "/bierGewinnt/speicherErfahrung",
        type: "POST",
        data: JSON.stringify(schwierigkeit),
        dataType: 'text',
        contentType: 'application/json',
    }).done(function (data) {
        //Zeigt den String der im Controller zusammengebaut wird als Toast an
        toast(true, data);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert('FAILED! ERROR: ' + errorThrown);
    });
}

//ClickHandler für jede Spalte
function setSpalteClickHandler(j) {
    //Iteriere über jede Spalte
    $(".spalte" + j).click(function (event) {
        if (isYourTurn) {
            $("#bier .bier-highlight").removeClass("bier-highlight");
            //Bekomme Nummer der Spalte
            var j = parseInt($(this).attr("class").substr(6));
            //Iteriere über jede Zeile
            for (var i = 0; i < zeile; i++) {
                //Wenn das Feld noch nicht belegt ist belege es mit dem eigenen Icon
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    $("#bier tr:nth-child(" + (zeile - i) + ") td:eq(" + j + ") img").attr("src", image_Spieler.src);
                    //Check ob es einen Gewinner gibt ausgehend vom letzten hinzugefuegten Stein
                    schritte++;
                    var gewinner = checkGewinn(i, j);
                    if (gewinner == 1) {
                        speicherErfahrung();
                    }
                    isYourTurn = false;
                    if (gewinner == 0) {
                        if (schwierigkeit == 1) {
                            zugKiMetrik();
                        }
                        else {
                            zugKi();
                        }
                    }
                    break;
                }
            }
        }
    });
}

// Zug der Ki
function zugKi() {
    // Variable fuer noch nicht volle Spalten
    var moegSpalten = [];
    // Fuege jede mögliche Spalte zu meinem Array hinzu
    for (var j = 0; j < spalte; j++) {
        if (map[zeile - 1][j] == 0) {
            moegSpalten.push(j);
        }
    }
    // Randomisierte Spalte auswaehlen
    var naechsteSpalte = Math.round(Math.random() * (moegSpalten.length - 1));
    // Fuege das Bild der Ki in die richtige Zelle ein
    for (var i = 0; i < zeile; i++) {
        if (map[i][naechsteSpalte] == 0) {
            map[i][naechsteSpalte] = -1;
            $("#bier tr:nth-child(" + (zeile - i) + ") td:eq(" + naechsteSpalte + ") img").attr("src", image_Ki.src);
            $("#bier tr:nth-child(" + (zeile - i) + ") td:eq(" + naechsteSpalte + ")").addClass("bier-highlight");
            //Check ob es ein Gewinner gibt ausgehend vom letzten hinzugefuegten Stein
            var gewinner = checkGewinn(i, naechsteSpalte);
            if (gewinner == -1) {
                //alert("Sie sind ein Idiot!");
                toast(false, "Du hast leider verloren " + spielerName + ". Versuche es noch einmal!");
            }
            else {
                // Spieler ist wieder am Zug
                isYourTurn = true;
            }
            break;
        }
    }
}

//Check ob es ein Gewinner gibt ausgehend vom letzten hinzugefuegten Stein
function checkGewinn(aktuelleZeile, aktuelleSpalte) {
    //console.debug("Check :" + aktuelleZeile +" "+ aktuelleSpalte);
    //Check Zeile
    for (var s = Math.max((aktuelleSpalte - bier + 1), 0); s <= Math.min(aktuelleSpalte, spalte - bier); s++) {
        var summe = 0;
        for (var j = s; j <= bier + s - 1; j++) {
            summe += map[aktuelleZeile][j];
        }
        if (Math.abs(summe) == bier) {
            return Math.sign(summe);
        }
    }
    //Check Spalte
    for (var s = Math.max((aktuelleZeile - bier + 1), 0); s <= Math.min(aktuelleZeile, zeile - bier); s++) {
        var summe = 0;
        for (var i = s; i <= bier + s - 1; i++) {
            summe += map[i][aktuelleSpalte];
        }
        if (Math.abs(summe) == bier) {
            return Math.sign(summe);
        }
    }
    //Check Diagonale links unten->rechts oben
    //k = Verschiebung nach links unten
    for (var k = Math.max(0, bier - spalte + aktuelleSpalte, bier - zeile + aktuelleZeile); k <= Math.min(aktuelleSpalte, aktuelleZeile, bier - 1); k++) {
        var summe = 0;
        for (var j = 0; j <= bier - 1; j++) {
            //console.debug("lu->ro k: "+k+" j: "+j);
            summe += map[aktuelleZeile - k + j][aktuelleSpalte - k + j];
        }
        if (Math.abs(summe) == bier) {
            return Math.sign(summe);
        }
    }
    //Check Diagonale links oben->rechts unten
    //k = Verschiebung nach links unten
    for (var k = Math.max(0, bier - aktuelleZeile - 1, bier - spalte + aktuelleSpalte); k <= Math.min(aktuelleSpalte, zeile - aktuelleZeile - 1, bier - 1); k++) {
        var summe = 0;
        for (var j = 0; j <= bier - 1; j++) {
            //console.debug("lo->ru k: "+k+" j: "+j);
            summe += map[aktuelleZeile + k - j][aktuelleSpalte - k + j];
        }
        if (Math.abs(summe) == bier) {
            return Math.sign(summe);
        }
    }
    return 0;
}

function einzelnerZugKiMetrik(tmpMap, tiefe) {
    // Variable fuer noch nicht volle Spalten
    var moegSpalten = [];
    // Fuege jede mögliche Spalte zu meinem Array hinzu
    for (var j = 0; j < spalte; j++) {
        //console.debug(tmpMap);
        //console.debug(tmpMap[zeile-1]);
        //console.debug(tmpMap[zeile-1][j]);
        if (tmpMap[zeile - 1][j] == 0) {
            moegSpalten.push(j);
        }
    }
    // map kopieren/vervielfältigen
    var tmpMaps = [];
    for (var k1 = 0; k1 < moegSpalten.length; k1++) {
        tmpMaps[k1] = []
        for (var k2 = 0; k2 < moegSpalten.length; k2++) {
            tmpMaps[k1][k2] = []
            for (var i = 0; i < zeile; i++) {
                tmpMaps[k1][k2][i] = tmpMap[i].slice();
            }
        }
    }
    // ki zug
    for (var jKI = 0; jKI < moegSpalten.length; jKI++) {
        var spalteKI = moegSpalten[jKI];
        for (var jM = 0; jM < moegSpalten.length; jM++) {
            var spalteM = moegSpalten[jM];
            for (var i = 0; i < zeile; i++) {
                if (tmpMaps[jKI][jM][i][spalteKI] == 0) {
                    tmpMaps[jKI][jM][i][spalteKI] = -1;
                    break;
                }
            }
        }
    }
    // mensch zug
    for (var jKI = 0; jKI < moegSpalten.length; jKI++) {
        var spalteKI = moegSpalten[jKI];
        for (var jM = 0; jM < moegSpalten.length; jM++) {
            var spalteM = moegSpalten[jM];
            // noch platz in der spalte für den menschen?
            if (tmpMaps[jKI][jM][zeile - 1][spalteM] == 0) {
                for (var i = 0; i < zeile; i++) {
                    if (tmpMaps[jKI][jM][i][spalteM] == 0) {
                        tmpMaps[jKI][jM][i][spalteM] = 1;
                        break;
                    }
                }
            }
        }
    }
    // finden der besten menschzüge (maxima)
    besteWerteM = [];
    //besteSpaltenM = [];
    for (var jKI = 0; jKI < moegSpalten.length; jKI++) {
        var spalteKI = moegSpalten[jKI];
        besteWerteM[jKI] = -99999999;
        //besteSpaltenM[jKI] = [];
        for (var jM = 0; jM < moegSpalten.length; jM++) {
            var spalteM = moegSpalten[jM];
            var bewertung;
            if (tiefe > 0) {
                //einzelnerZugKiMetrik liefert den besten Zug der KI für den jeweils besten Antwortzug von M
                var tmp = einzelnerZugKiMetrik(tmpMaps[jKI][jM], tiefe - 1);
                bewertung = tmp[1] * (tiefe + 1);
            }
            else {
                bewertung = metrik(tmpMaps[jKI][jM]) * (tiefe + 1);
            }

            if (bewertung > besteWerteM[jKI]) {
                besteWerteM[jKI] = bewertung;
                //besteSpaltenM[jKI] = [spalteM];
            }
            else if (bewertung == besteWerteM[jKI]) {
                //besteSpaltenM[jKI].push(spalteM);
            }
        }
    }
    // findern des besten kizugs (minimum)
    besterWertKI = 99999999;
    besteSpalteKI = [];
    //besteSpalteM = -1
    for (var jKI = 0; jKI < moegSpalten.length; jKI++) {
        var spalteKI = moegSpalten[jKI];
        if (besteWerteM[jKI] < besterWertKI) {
            besterWertKI = besteWerteM[jKI];
            besteSpalteKI = [spalteKI];
            //var besteSpaltenMIndex = Math.round(Math.random() * (besteSpaltenM[jKI].length - 1));
            //besteSpalteM = besteSpaltenM[jKI][besteSpaltenMIndex];
        }
        else if (besteWerteM[jKI] == besterWertKI) {
            besteSpalteKI.push(spalteKI);
        }
    }

    var besteSpalteKIIndex = Math.round(Math.random() * (besteSpalteKI.length - 1));
    return [besteSpalteKI[besteSpalteKIIndex], besterWertKI];
}

// Zug der Ki
function zugKiMetrik() {
    //erster zug am menschen richtung mitte
    var anzahlNotNull = 0;
    for (var i = 0; i < Math.min(3, zeile); i++) {
        for (var j = 0; j < spalte; j++) {
            if (map[i][j] != 0) {
                anzahlNotNull++;
            }
        }
    }
    var naechsteSpalte;
    if (anzahlNotNull == 0) {
        naechsteSpalte = Math.round(Math.random() * (spalte - 1));
    }
    else if (anzahlNotNull == 1) {
        var spalteM = -1;
        for (var j = 0; j < spalte; j++) {
            if (map[0][j] == 1) {
                spalteM = j;
                break;
            }
        }
        if (spalteM == (spalte - 1) / 2) {
            var isLinks = Math.round(Math.random());
            if (isLinks) {
                naechsteSpalte = spalteM - 1;
            }
            else {
                naechsteSpalte = spalteM + 1;
            }
        }
        else if (spalteM < (spalte - 1) / 2) {
            naechsteSpalte = spalteM + 1;
        }
        else {
            naechsteSpalte = spalteM - 1;
        }
    }
    else {
        var tmp = einzelnerZugKiMetrik(map, 0);
        naechsteSpalte = tmp[0];
    }
    //console.debug("Nächste Spalte: "+ naechsteSpalte);
    // Fuege das Bild der Ki in die richtige Zelle ein
    for (var i = 0; i < zeile; i++) {
        if (map[i][naechsteSpalte] == 0) {
            map[i][naechsteSpalte] = -1;
            $("#bier tr:nth-child(" + (zeile - i) + ") td:eq(" + naechsteSpalte + ") img").attr("src", image_Ki.src);
            $("#bier tr:nth-child(" + (zeile - i) + ") td:eq(" + naechsteSpalte + ")").addClass("bier-highlight");
            //$("#bier tr:nth-child("+(zeile-i)+") td:eq("+naechsteSpalte+") img").addClass("bier-highlight");
            //Check ob es ein Gewinner gibt ausgehend vom letzten hinzugefuegten Stein
            var gewinner = checkGewinn(i, naechsteSpalte);
            ;
            if (gewinner == -1) {
                //alert("Sie sind ein Idiot!");
                toast(false, "Du hast leider verloren " + spielerName + ". Versuche es noch einmal! \n Es gibt auch eine einfache KI.");
            }
            else {
                // Spieler ist wieder am Zug
                isYourTurn = true;
            }
            break;
        }
    }
    //console.info("Bewertung: " + metrik(map));
}

function metrik(tmpMap) {
    var bewertung = [0, 0, 0, 10, 1000]
    var anzahl1 = [0, 0, 0, 0, 0]
    var anzahl2 = [0, 0, 0, 0, 0]
    /*for (var breite = 2; breite <= bier; breite++) {
     bewertung[breite] = Math.pow(5,breite-1);
     anzahl1[breite] = 0
     anzahl2[breite] = 0
     }*/
    //horizontal
    var stringS = "";
    for (var breite = 2; breite <= bier; breite++) {
        for (var i = 0; i < zeile; i++) {
            for (var s = 0; s < spalte - breite + 1; s++) {
                var summe = 0;
                for (var j = 0; j < breite; j++) {
                    summe += tmpMap[i][s + j]
                }
                if (Math.abs(summe) == breite) {
                    if (Math.sign(summe) == 1) {
                        anzahl1[breite] += 1;
                    }
                    if (Math.sign(summe) == -1) {
                        anzahl2[breite] += 1;
                    }
                }
            }
        }
    }
    stringS += anzahl1[2] + " + ";
    //vertikal
    for (var breite = 2; breite <= bier; breite++) {
        for (var j = 0; j < spalte; j++) {
            for (var s = 0; s < zeile - breite + 1; s++) {
                var summe = 0;
                for (var i = 0; i < breite; i++) {
                    summe += tmpMap[s + i][j]
                }
                if (Math.abs(summe) == breite) {
                    if (Math.sign(summe) == 1) {
                        anzahl1[breite] += 1;
                    }
                    if (Math.sign(summe) == -1) {
                        anzahl2[breite] += 1;
                    }
                }
            }
        }
    }
    stringS += anzahl1[2] + " + ";
    //lu-ro
    for (var breite = 2; breite <= bier; breite++) {
        for (var sj = 0; sj < spalte - breite + 1; sj++) {
            for (var si = 0; si < zeile - breite + 1; si++) {
                var summe = 0;
                for (var k = 0; k < breite; k++) {
                    summe += tmpMap[si + k][sj + k]
                }
                if (Math.abs(summe) == breite) {
                    if (Math.sign(summe) == 1) {
                        anzahl1[breite] += 1;
                    }
                    if (Math.sign(summe) == -1) {
                        anzahl2[breite] += 1;
                    }
                }
            }
        }
    }
    stringS += anzahl1[2] + " + ";
    //lo-ru
    for (var breite = 2; breite <= bier; breite++) {
        for (var sj = 0; sj < spalte - breite + 1; sj++) {
            for (var si = 0; si < zeile - breite + 1; si++) {
                var summe = 0;
                for (var k = 0; k < breite; k++) {
                    summe += tmpMap[zeile - 1 - (si + k)][sj + k]
                }
                if (Math.abs(summe) == breite) {
                    if (Math.sign(summe) == 1) {
                        anzahl1[breite] += 1;
                    }
                    if (Math.sign(summe) == -1) {
                        anzahl2[breite] += 1;
                    }
                }
            }
        }
    }
    var gesamtBewertungM = 0;
    var gesamtBewertungKI = 0;
    var gesamtBewertungM1 = 0;
    var gesamtBewertungKI1 = 0;
    for (var breite = 2; breite <= bier; breite++) {
        gesamtBewertungM += bewertung[breite] * anzahl1[breite];
        gesamtBewertungKI += bewertung[breite] * anzahl2[breite];
        gesamtBewertungM1 += " + " + bewertung[breite] + "*" + anzahl1[breite];
        gesamtBewertungKI1 += " + " + bewertung[breite] + "*" + anzahl2[breite];
    }
    return (10000 + gesamtBewertungM - gesamtBewertungKI);
}

function toast(gewonnen, nachricht) {
    // Get the snackbar DIV
    var x = document.getElementById("snackbar")
    if (gewonnen == true) {
        x.innerHTML = nachricht;
    } else if (gewonnen == false) {
        x.innerHTML = nachricht;
    }
    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function () {
        x.className = x.className.replace("show", "");
    }, 5000);
}