#!/bin/bash

echo -e "\n"

directory="./bin"

# java -cp $directory tec.poo.hello.HelloWorld
# java -cp $directory tec.poo.hello.HelloWorldSwing

# Correr clase desde JAR
# java -cp $directory/hello-world.jar tec.poo.hello.HelloWorld
# Correr de modo "ejecutable"
java -jar $directory/hello-world.jar