cd %~dp0
C:
cd %LibreOffice%
soffice.exe -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
EXIT