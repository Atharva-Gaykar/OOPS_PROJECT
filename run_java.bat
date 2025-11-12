@echo off
REM Compile all Java files with gson in the classpath
javac -cp gson-2.10.1.jar Main.java ClipTestQuery.java Calorie.java ClipImageTestQuery.java

REM Run the Main class if compilation succeeds
IF %ERRORLEVEL% EQU 0 (
    java -cp ".;gson-2.10.1.jar" Main
) ELSE (
    echo Compilation failed. Fix errors before running.
)