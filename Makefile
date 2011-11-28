JFLAGS = -g
JC = javac
ARG=
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  Gui.java \
		  Field.java \
		  Element.java \
		  Animate.java \
		  Rectangle.java \
		  Ball.java

default: classes

run: classes
	java -classpath . Gui

classes: $(CLASSES:.java=.class)

debug: $(CLASSES:.java=.class)
	jdb Test $(ARG)

clean:
	$(RM) *.class
