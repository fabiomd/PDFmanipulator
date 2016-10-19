cd %~dp0
pushd ..
set /p directory=[promptString]
echo %directory%> paths/officePatch.txt