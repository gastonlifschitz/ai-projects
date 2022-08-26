#!/bin/bash

path="path=/Users/gastonlifschitz/ITBA/SIA/TP1/configFile1.txt"

heuristics="heuristic=1"

algorithm="algorithm=A"

deadlock="deadlock=TRUE"

maxDepth="maxDepth=3000"

unzip Sokoban.jar -d auxdir

cd auxdir

rm config.properties

echo $path >> config.properties

echo $heuristics >> config.properties

echo $algorithm >> config.properties

echo $deadlock >> config.properties

echo $maxDepth >> config.properties

jar -cvmf META-INF/MANIFEST.MF ../Sokoban.jar *

cd ..

rm -r auxdir
