.PHONY: build run clean

COMPILER = javac
RUNNER = java

FILES = \
	src/objects/*.java \
	src/raytracing/*.java \
	src/tools/*.java 
TARGET = build/

DEFAULT_RESOLUTION = 1920 1080
DEFAULT_DISTANCE = 1.0
DEFAULT_DEPTH = 3
DEFAULT_ARGS = $(DEFAULT_RESOLUTION) $(DEFAULT_DISTANCE) $(DEFAULT_DEPTH)

default : build

build:
	$(COMPILER) -d $(TARGET) $(FILES)

run: build
	@echo "Running raytracer..."
	@echo "Resolution: $(DEFAULT_RESOLUTION)"
	@echo "Distance: $(DEFAULT_DISTANCE)"
	@echo "Depth: $(DEFAULT_DEPTH)"
	$(RUNNER) -cp $(TARGET) raytracing.Raytracing $(DEFAULT_ARGS)

clean:
	rm -rf $(TARGET)
	rm images/*
	
