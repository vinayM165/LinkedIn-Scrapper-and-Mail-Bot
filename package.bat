@echo off
echo Preparing LinkedIn Scraper Package...

REM Create dist directory
mkdir dist 2>nul
mkdir dist\jre 2>nul

REM Download Zulu JRE
echo Downloading JRE...
powershell -Command "$ProgressPreference = 'SilentlyContinue'; [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://cdn.azul.com/zulu/bin/zulu8.72.0.17-ca-jre8.0.382-win_x64.zip' -OutFile 'dist\jre.zip'"

REM Extract JRE
echo Extracting JRE...
cd dist
powershell -Command "Expand-Archive -Force jre.zip ."
move zulu* jre
del jre.zip

REM Copy executable and dependencies
echo Copying application files...
copy ..\target\LinkedInScraper.exe .
mkdir lib
xcopy /E /Y ..\target\lib lib\

REM Create final package
echo Creating final package...
echo Done! Your application package is ready in the 'dist' folder.
echo You can distribute the entire 'dist' folder - it contains everything needed to run the application.
pause
