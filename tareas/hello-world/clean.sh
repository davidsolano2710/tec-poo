#!/bin/bash

echo -e "\n"
echo "=================="
echo "CLEAN"
echo "=================="

directory="./bin"

#verificar si el directorio "bin" existe
if [ -d "$directory" ]; then
    echo "Directory already exists. Removing..."
    rm -r "$directory"
    echo "Directory removed."
fi
