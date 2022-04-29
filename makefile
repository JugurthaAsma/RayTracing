default: compile

.SILENT:

#compile all java files
compile:
	@echo "To run all tests, type 'make tests'"
	@echo "=========================================================="
	@echo "To run by yourself, type 'java -cp bin Main <Width> <Height> <MaxDepth>'"
	@echo "=========================================================="
	javac -d bin -cp bin/ src/objects/*.java src/utils/*.java src/Main.java

#run the program with args
run:
	java -cp bin Main

tests: compile
	@echo "=========================================================="
	@echo "Test 1 : resolution 720x480 with depth 1"
	java -cp bin Main 720 480 1
	@echo "=========================================================="
	@echo "Test 2 : resolution 1080x720 with depth 2"
	java -cp bin Main 1080 720 2
	@echo "=========================================================="
	@echo "Test 3 : resolution 1080x720 with depth 3"
	java -cp bin Main 1080 720 3
	@echo "=========================================================="
	@echo "Test 4 : resolution 1920x1080 with depth 3"
	java -cp bin Main 1920 1080 3
	@echo "=========================================================="
	@echo "Test 5 : resolution 2560x1440 with depth 3"
	java -cp bin Main 2560 1440 3
	@echo "=========================================================="
	@echo "Test 6 : resolution 4096x2160 with depth 4"
	java -cp bin Main 4096 2160 4
	@echo "=========================================================="

#clean all files
clean:
	rm -rf bin
	rm *.tga