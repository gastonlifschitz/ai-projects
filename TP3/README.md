# Trabajo práctico: Perceptrones

El trabajo práctico consiste de tres ejercicios.Los ejercicios 1.a y 1.b son un perceptron simple sencillo con función escalón que aprende las funciones lógicas AND y OR. El ejercicio 2 es un perceptron no lineal. El ejercicio 3.a aprende la función lógica XOR. El ejercicio 3.b esta relacionado con primalidad.

## Instalación

Clonar el repositorio git

```
git clone https://$USER@bitbucket.org/itba/sia-2020-1c-06.git
```

Configurar en config.properties (Ir al paso de cofiguracion)

Compilar el programa

```
./compile.sh
```

Correr el programa

```
./run.sh
```

Módulo de visualización

```
./vis.sh
```

## Uso y configuración

Respecto del uso del archivo de configuracion: es importante tener en cuenta que cada vez que hacemos un cambio en el archivo de configuracion debemos hacer ./compile.sh para que al hacer ./run.sh los cambios impacten el el ejecutable.

En el archivo 'src/resources/config.properties' iran las configuraciones.

### Ejemplos de config.properties

Configuración 1

```
ej1a=TRUE
ej1a_epochs=10
ej1a_learningrate=0.03
ej1a_ploteachepoch=TRUE
ej1a_stopearly=TRUE
ej1b=TRUE
ej1b_epochs=10
ej1b_learningrate=0.5
ej1b_ploteachepoch=TRUE
ej1b_stopearly=TRUE
ej2=FALSE
ej3a=FALSE
ej3b=FALSE
num_epochs=10000
learning_rate=0.5
beta=0.4
hidden_perceptrons=80
max_error_ej3=0.01
```

Configuración 2

```
ej1a=FALSE
ej1a_epochs=10
ej1a_learningrate=0.03
ej1a_ploteachepoch=TRUE
ej1a_stopearly=TRUE
ej1b=FALSE
ej1b_epochs=10
ej1b_learningrate=0.5
ej1b_ploteachepoch=TRUE
ej1b_stopearly=TRUE
ej2=FALSE
ej3a=TRUE
ej3b=TRUE
num_epochs=10000
learning_rate=0.5
beta=0.4
hidden_perceptrons=80
max_error_ej3=0.01
```


## Desarrolladores
* Lucio Pagni
* Gaston Lifschitz
