CALL compile.bat
CALL jar.bat
DEL ..\Barcode\libs\*.jar
DEL ..\ISBNTest\libs\*.jar
COPY *.jar ..\Barcode\libs
COPY *.jar ..\ISBNTest\libs