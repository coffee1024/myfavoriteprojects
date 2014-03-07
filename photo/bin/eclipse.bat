@echo off
echo [INFO] Package the war in target dir.

cd %~dp0
cd ..
call mvn eclipse:eclipse
cd bin
pause