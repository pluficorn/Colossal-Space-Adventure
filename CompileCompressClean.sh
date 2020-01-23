#!/bin/sh

# Compile
javac GameMain.java

# Compress
jar cmf manifest.mf Colossal-Space-Adventure.jar *.class

# Clean compiled files leftover from compiling
rm *.class

