chcp 1252
pushd ..
set /p officePatch=< paths/officePatch.txt
set /p docxConvertTemp=< paths/DocxTempFile.txt
set /p convertFile=< paths/convertFilePatch.txt
set convertFile= "%convertFile%"
C:
cd %officePatch%
WINWORD.EXE /mDocxToPDF /q %convertFile%
pause
EXIT