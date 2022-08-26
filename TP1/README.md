# SIA-TP1

Generador de Soluciones de Sokoban

## Configurar el sistema

Dados los archivos Sokoban.jar y config.sh.

En el archivo config.sh setear a elección:

* El path del archivo de configuración

* Los valores válidos son: cualquier path válido que lleve a un archivo de configuración

* El valor de la heurística. Sus valores pueden ser:
	* 0 - Heuristica admisible 1
	* 1 - Heuristica admisible 2
	* 2 - Heuristica admisible 3
	* 3 - Heuristica no admisible

	Nota: para ver que hace cada heuritica leer la presentacion.

* El algoritmo a correr. Sus valores pueden ser:
	* BFS
	* DFS 
	* IDDFS  
	* GLOBAL_GREEDY  
	* A  
	* IDA  
	* CUSTOM_IDA_STAR
		* Este es una mejora a la implementacion de IDA star en la que no se vuelven a visitar los nodos en un camino.

- El flag deadlock

Los valores posibles son TRUE/FALSE

- El valor de MAXDEPTH, utilizado en IDDFS

El valor debe ser un entero

## Configuración Oportuna 1

configA.sh

```
#!/bin/bash

path="path='CURRENT-WORKING-DIRECTORY'/configFile1.txt"

heuristics="heuristic=1"

algorithm="algorithm=GLOBAL_GREEDY"

deadlock="deadlock=FALSE"

maxDepth="maxDepth=3000"

...

```

## Configuración Oportuna 2

configB.sh

```
#!/bin/bash

path="path='CURRENT-WORKING-DIRECTORY'/configFile1.txt"

heuristics="heuristic=1"

algorithm="algorithm=A"

deadlock="deadlock=TRUE"

maxDepth="maxDepth=3000"

...

```

## Ejecutar el sistema

1. En archivo de configuracion (config.sh) setear el path: Para esto

	```
	foo@bar:~$ pwd
	```
	
	La respuesta de este comando ira dentro de path en config.sh en el path
	
2. Elegir la heuristica, el algoritmo, si se desea realizar la busqueda con deadlocks y un max depth que sera considerado para los algoritmos recursivos.

3. Finalmente:
	```
	foo@bar:~$ java -jar Sokoban.jar
	```

## Authors

* **Lucio Pagni** 		- Legajo: 58571
* **Gaston Lifschitz** 	- Legajo: 58225



