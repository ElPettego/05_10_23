run:
	javac src/Main.java && java -cp src Main

tcpserver:
	javac src/com/example/*.java && swg_tmr java -cp src com.example.Main -p 12345 -s -t tcp
	


