#!/bin/bash

# Compila o arquivo java
javac Main.java

# Loop para executar todos os arquivos .txt no diretório atual
for file in ./testCases/*.txt
do
  echo "Running test case: $file"
  java Main $file
done