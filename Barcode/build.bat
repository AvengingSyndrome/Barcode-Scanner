DEL bin\Android*
CALL compileLibrary.bat
CALL compile.bat
CALL createdex.bat
CALL createapk.bat
CALL sign.bat
CALL zip
CALL install.bat