# Trabajo practico: Algoritmos geneticos
Un trabajo que se basa en un motor de algoritmos geneticos para conseguir la mejor
configuracion de un tipo de personaje en un juego de rol.

## Instalacion

Clonar el repositorio git

```
git clone https://$USER@bitbucket.org/itba/sia-2020-1c-06.git
```

Configurar en config.properties (Ir al paso de cofiguracion)

Generar el .jar

```
mvn clean package
```

Descargar librerias

```
./lib.sh
```

Correr el programa

```
./run.sh
```

run hace los siguiente

```
- java -jar target/TP2-1.0-SNAPSHOT.jar

- python3 fitness.py
- python3 area_plot.py
```

es decir correr el jar que se encuentra en target/ y se autogenera al hacer mvn clean package.

luego los .py generan gráficos.

En el caso de querer las animaciones correr:

```
python3 animationBar.py
```

En el caso de querer los histogramas correr python3 python3 box_plot.py $ITEM_frecuencia.txt,
siendo $ITEM el caso particular que queremos estudiar.

## Uso y configuracion

Respecto del uso del archivo de configuracion: es importante tener en cuenta que cada vez que hacemos un 
cambio en el archivo de configuracion debemos hacer mvn clean package para que al hacer ./run.sh los
cambios impacten el el ejecutable.

En el archivo 'src/resources/config.properties' iran las configuracion del motor:

**Tipo de personaje**:

- TIPO_PERSONAJE=ARQUERO    --> Genera una poblacion de tipo arquero.
- TIPO_PERSONAJE=GUERRERO   --> Genera una poblacion de tipo guerrero.
- TIPO_PERSONAJE=ESPIA      --> Genera una poblacion de tipo espia.
- TIPO_PERSONAJE=DEFENSOR   --> Genera una poblacion de tipo defensor.

**Tipo de cruce**:

- CRUCE=DE_UN_PUNTO     --> Genera cruces de un punto
- CRUCE=DE_DOS_PUNTO    --> Genera cruces entre dos puntos
- CRUCE=ANULAR          --> Genera cruces anulares
- CRUCE=UNIFORME        --> Genera cruces uniform

**Tipo de mutacion**:

- MUTACION=GEN                  --> Genera mutaciones de tipo gen
- MUTACION=MULTIGEN_LIMITADA    --> Genera mutaciones multigen limitada
- MUTACION=MULTIGEN_UNIFORME    --> Genera mutaciones multigen uniforme
- MUTACION=COMPLETA             --> Genera mutaciones completa

**Tipo de seleccion**: Para esto van a ser utilizados 4 metodos (pueden repetirse).
Para el proceso de seleccion debera asignarse a cada metodo un tipo de seleccion.
Los metodos 1 y 2 ('METODO1' y 'METODO2') seran utilizados para la seleccion de padres, 
mientras que los metodos 3 y 4 ('METODO3' y 'METODO4') para el reemplazo de individuos.
PD: METODON representa METODO1/2/3/4

- METODON=ELITE                  
- METODON=RULETA    
- METODON=RANKING    
- METODON=UNIVERSAL           
- METODON=BOLTZMANN           
- METODON=TORNEO_A    --> Torneos deterministicos
- METODON=TORNEO_B    --> Torneos probabilisticos

**Tipo de implementacion**: Determina el tipo de implementacion para el reemplazo de 
inviduos para la proxima generacion

- IMPLEMENTACION=FILL-ALL
- IMPLEMENTACION=FILL-PARENT

**Criterio de corte**: 

- CRITERIO_DE_CORTE=TIEMPO                      -> En minutos debe ser el valor de criterio de corte
- CRITERIO_DE_CORTE=CANTIDAD_DE_GENERACIONES 

Esto debe ser acompañado por un valor de criterio de corte

- VALOR_CRITERIO_DE_CORTE={numero} -> numero debe ser un valor entero que representa los minutos/cantidad
de generaciones.

**Cantidad de poblacion**: Cuantos individuos seran por cada generacion

-SIZE={Numero}  -> Numero debe ser un valor enter

**Parametro adicionales(pero obligatorios)**: 

- K={Numero}    -> Numero entero que representa la cantidad de padres que van a ser seleccionados en cada metodo
- A={Numero}    -> Numero es un valor entre 0 y 1. La seleccion de padres esta dada por a*(met1) + (a-1)*met2
- B={Numero}    -> Numero es un valor entre 0 y 1. El reemplazo de indiv. esta dada por b*(met3) + (b-1)*met4

### Ejemplo de config.properties

Configuracion 1

```
K=150
CRUCE=ANULAR
MUTACION=MULTIGEN_LIMITADA
PROBABILIDAD_MUTACION=0.3
METODO1=ELITE
METODO2=UNIVERSAL
A=0.25
METODO3=TORNEO_A
METODO4=TORNEO_B
B=0.5
IMPLEMENTACION=FILL-ALL
CRITERIO_DE_CORTE=CANTIDAD_GENERACIONES
VALOR_CRITERIO_DE_CORTE=300
SIZE=1000
TIPO_PERSONAJE=ARQUERO
```

Configuracion 2

```
K=300
CRUCE=DE_UN_PUNTO
MUTACION=GEN
PROBABILIDAD_MUTACION=0.7
METODO1=ELITE
METODO2=UNIVERSAL
A=0.3
METODO3=RULETA
METODO4=TORNEO_B
B=0.25
IMPLEMENTACION=FILL-ALL
CRITERIO_DE_CORTE=TIEMPO
VALOR_CRITERIO_DE_CORTE=2
SIZE=1500
TIPO_PERSONAJE=ESPIA
```


## Developed by
* Lucio pagni
* Gaston Lifschitz
