@echo off
chcp 1252
cd %~dp0
pushd ..
set /p officePatch=< paths/officePatch.txt
set /p docxConvertTemp=< paths/DocxTempFile.txt
set /p convertFile=< paths/convertFilePatch.txt
set convertFile= "%cd%\%convertFile%"
C:
cd %officePatch%
WINWORD.EXE /mDocxToPDF /q %convertFile%