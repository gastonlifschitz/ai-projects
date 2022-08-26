#!/bin/bash

echo "SIA TP2"

java -jar target/TP2-1.0-SNAPSHOT.jar

python3 fitness.py
python3 area_plot.py
