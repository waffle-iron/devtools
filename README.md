devtool
===================

Sammlung diverser Eclipse Plugins und Features

**Build on Jenkins**: [![Build Status](https://travis-ci.org/FunThomas424242/devtools.png?branch=master)](https://travis-ci.org/FunThomas424242/devtools)

**Project Site:** http://funthomas424242.github.com/devtool/


workgroup
-------------------

Ein Projekt zum Herstellen identischer Entwicklungsumgebungen. Es realisiert den Export und Import von ausgewählten (fest vorgegebenen) Preferences.
Leider war es nicht möglich die zu exportierenden Preferences dynamisch über eine Preference Page des Plugins festzulegen. Idealerweise kann 
der Nutzer festlegen von welchen Plugins er die Einstellungen exportieren bzw. importieren möchte. Das ist mit dem aktuellen Eclipse API leider 
nicht möglich. Daher wurden feste Sets implementiert. Diese können beim Export und Import ausgewählt werden. Aktuell werden unterstützt:
* M2E Einstellungen
* Editor Einstellungen
* Resources Einstellungen

(Nach Eintragen von Enhancement Requests können gern weitere Einstellungen aufgenommen werden)
Generell werden alle Einstellungen nur exportiert, wenn sie vom Default Wert abweichen. 

Wer mit dieser einfachen Lösung nicht auskommt, dem seien folgende Projekte empfohlen:
* [Workspace Mechanic] (http://code.google.com/a/eclipselabs.org/p/workspacemechanic/)
* [Eclipse Team Etceteras] (http://pellaton.github.com/eclipse-team-etceteras/)

Sowie eine kleine Lektüre zur Herbeiführung identischer Eclipse Konfigurationen im Arbeitsteam: 
(http://www.netcetera.com/de/dms/documents/presentations-and-articles/econ2011-pellaton-eclipse-in-teams.pdf)




