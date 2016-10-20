chcp 1252
cd %~dp0
pushd ..
set /p convertDoc=< paths/DocxTempFile.txt
@echo off
@echo ' The Word macro for exporting to PDF (the Word window closes after finishing)> MacroOffice/DocxToPDF.txt
@echo Sub DocxToPDF()>> MacroOffice/DocxToPDF.txt
@echo ChangeFileOpenDirectory ThisDocument.Path>> MacroOffice/DocxToPDF.txt
@echo     ActiveDocument.ExportAsFixedFormat _>> MacroOffice/DocxToPDF.txt
@echo         OutputFileName:="%cd%\%convertDoc%", _>> MacroOffice/DocxToPDF.txt
@echo         ExportFormat:=wdExportFormatPDF, _>> MacroOffice/DocxToPDF.txt
@echo         OpenAfterExport:=False, _>> MacroOffice/DocxToPDF.txt
@echo         OptimizeFor:=wdExportOptimizeForPrint, _>> MacroOffice/DocxToPDF.txt
@echo         Range:=wdExportAllDocument, _>> MacroOffice/DocxToPDF.txt
@echo         From:=1, _>> MacroOffice/DocxToPDF.txt
@echo         To:=1, _>> MacroOffice/DocxToPDF.txt
@echo         Item:=wdExportDocumentContent, _>> MacroOffice/DocxToPDF.txt
@echo         IncludeDocProps:=True, _>> MacroOffice/DocxToPDF.txt
@echo         KeepIRM:=True, _>> MacroOffice/DocxToPDF.txt
@echo         CreateBookmarks:=wdExportCreateNoBookmarks, _>> MacroOffice/DocxToPDF.txt
@echo         DocStructureTags:=True, _>> MacroOffice/DocxToPDF.txt
@echo         BitmapMissingFonts:=True, _>> MacroOffice/DocxToPDF.txt
@echo         UseISO19005_1:=False>> MacroOffice/DocxToPDF.txt
@echo     Application.Quit SaveChanges:=wdDoNotSaveChanges>> MacroOffice/DocxToPDF.txt
@echo End Sub>> MacroOffice/DocxToPDF.txt