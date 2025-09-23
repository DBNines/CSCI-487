AStar.class: AStar.java Node.class
	javac -g AStar.java

Main.class: Main.java Node.class
	javac -g Main.java

Node.class: Node.java
	javac -g Node.java

run: Main.class
	java Main

alt: AStar.class
	java AStar

clean:
	rm *.class