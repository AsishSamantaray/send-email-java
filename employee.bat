set PROGRAM=Main
echo "compiling program %PROGRAM%.java ..."

javac -cp ".;mail.jar;activation.jar" %program%.java

java -cp ".;mail.jar;activation.jar" %program%

cmd.exe