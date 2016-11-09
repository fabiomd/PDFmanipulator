@echo off
chcp 1252
cd %~dp0
pushd ..
@echo off
@echo ' This Macro will merge all files inside the folder> MacroOffice/MergeDocx.txt
@echo Sub MergeDocx()>> MacroOffice/MergeDocx.txt
@echo Dim fname As String, FI() As String>> MacroOffice/MergeDocx.txt
@echo Dim i As Long>> MacroOffice/MergeDocx.txt
@echo Dim dx As Document>> MacroOffice/MergeDocx.txt
@echo Directory = "%cd%\TEST\">> MacroOffice/MergeDocx.txt
@echo ReDim FI(1000)>> MacroOffice/MergeDocx.txt
@echo fname = Dir$(Directory + "*.docx")>> MacroOffice/MergeDocx.txt
@echo Do While fname ^<^> "": i = i + 1: FI(i) = Directory ^& fname: fname = Dir$: Loop>> MacroOffice/MergeDocx.txt
@echo ReDim Preserve FI(i)>> MacroOffice/MergeDocx.txt
@echo Dim target As Document>> MacroOffice/MergeDocx.txt
@echo Dim docRange As Range>> MacroOffice/MergeDocx.txt
@echo ' Create a blank document>> MacroOffice/MergeDocx.txt
@echo If UBound(FI) ^> 0 Then>> MacroOffice/MergeDocx.txt
@echo     Set target = Documents.Open(FI(1))>> MacroOffice/MergeDocx.txt
@echo     Set docRange = target.Range>> MacroOffice/MergeDocx.txt
@echo     docRange.Collapse wdCollapseEnd>> MacroOffice/MergeDocx.txt
@echo     ' docRange.MoveEnd Unit:=wdCharacter, Count:=-1>> MacroOffice/MergeDocx.txt
@echo     ' Insert a page breaker to maintain format>> MacroOffice/MergeDocx.txt
@echo     docRange.InsertBreak Type:=wdSectionBreakNextPage>> MacroOffice/MergeDocx.txt
@echo     For i = 2 To UBound(FI)>> MacroOffice/MergeDocx.txt
@echo         Set dx = Documents.Open(FI(i), Visible:=False)>> MacroOffice/MergeDocx.txt
@echo         Set docRange = dx.Range>> MacroOffice/MergeDocx.txt
@echo         docRange.Collapse wdCollapseEnd>> MacroOffice/MergeDocx.txt
@echo         ' Insert a page breaker to maintain format>> MacroOffice/MergeDocx.txt
@echo         docRange.InsertBreak Type:=wdSectionBreakNextPage>> MacroOffice/MergeDocx.txt
@echo         Set targetRange = target.Range>> MacroOffice/MergeDocx.txt
@echo         targetRange.Collapse wdCollapseEnd>> MacroOffice/MergeDocx.txt
@echo         ' targetRange.MoveEnd Unit:=wdCharacter, Count:=-2>> MacroOffice/MergeDocx.txt
@echo         Set docRange = dx.Range>> MacroOffice/MergeDocx.txt
@echo         docRange.MoveEnd Unit:=wdCharacter, Count:=-1>> MacroOffice/MergeDocx.txt
@echo         targetRange.FormattedText = docRange.FormattedText>> MacroOffice/MergeDocx.txt
@echo         dx.Close wdDoNotSaveChanges>> MacroOffice/MergeDocx.txt
@echo     Next i>> MacroOffice/MergeDocx.txt
@echo End If>> MacroOffice/MergeDocx.txt
@echo End Sub>> MacroOffice/MergeDocx.txt