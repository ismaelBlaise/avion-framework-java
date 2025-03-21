@echo off

REM Déclaration des variables
    set nom_projet=avion-framework
    set temp=C:\Users\ismael\Documents\GitHub\avion-framework-java\temp
    set web=C:\Users\ismael\Documents\GitHub\avion-framework-java\web
    set xml=C:\Users\ismael\Documents\GitHub\avion-framework-java\xml
    set lib=C:\Users\ismael\Documents\GitHub\avion-framework-java\lib
    set war=C:\Users\ismael\Documents\GitHub\avion-framework-java\
    set src=C:\Users\ismael\Documents\GitHub\avion-framework-java\src
    set conf=C:\Users\ismael\Documents\GitHub\avion-framework-java\conf
REM Suppression de temp si il existe
    rmdir /s /q %temp%

REM Création du nouveau temp
    mkdir %temp%

REM Création du sous-répertoire temp/WEB-INF/lib
    mkdir %temp%"\WEB-INF\lib" 

REM Création du sous-répertoire temp/WEB-INF/classes
    mkdir %temp%"\WEB-INF\classes"

REM Copie des web de notre espace de travail initial vers le répertoire temporaire temp
    xcopy /s /e /q %web% %temp%

REM Copie des fichiers .xml de notre espace de travail initial vers temp/WEB-INF
    xcopy /s /e /q %xml% %temp%"\WEB-INF\"

REM Copie des fichiers conf de notre espace de travail initial vers temp/WEB-INF
    xcopy /s /e /q %conf% %temp%"\WEB-INF\"

REM Copie des *.jar de notre espace de travail initial ver temp/WEB-INF/lib
    xcopy /s /e /q %lib% %temp%"\WEB-INF\lib"

REM Compilation des fichiers Java dans des packages
    for /r %src% %%i in (*.java) do (
        javac -cp "%lib%\*;" -sourcepath %src% -d %temp%"\WEB-INF\classes" "%%i"
    )

REM Convertir le répertoire temp en .war
    jar -cvf %nom_projet%.war -C %temp% .

REM Copie du fichier war vers tomcat/webapps
    copy /y %nom_projet%.war "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"

REM Supprimer le fichier WAR temporaire
    del %nom_projet%.war



