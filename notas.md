# NOTAS DEL DIA 4 DE MARZO

Los sistemas de basados en conocimientos dentro de un paradigma de programacion basada en conocimientos son aquellos que utilizan estas tecnologias

- CLIPS
- Java with JADE -> Java libraries

Entonces vamos a poder integrar estos 2 tipos de sistemas para generar sistemas inteligentes para poder generar un asistente.

Los sistemas basados en agentes con conocimientos podemos observar que problemas vamos a modelar.

## EJEMPLO DE MODELADO

En este momento vamos a generar un analisis rapido con el problema de un mono que quiere unas bananas, dichas bananas estan en un techo.

### ABSTRACCION

- OBJETOS
    - MONO
    - CAJA
    - BANANAS
    - PISO
    - PAREDES 
    - SUELO

- Hechos que vamos a observar
    - Mono esta en el cuarto
    - Caja esta en el cuarto
    - El cuarto tiene techo
    - La caja esta en el piso
    - Las bananas cuelgan del techo
    - La caja puede moverse
    - El mono puede moverse

- Reglas o cosas que pueden suceder
    - El mono agarra la banana
    - Las bananas estan colgadas
    - El mono esta arriba de la caja
    - El mono se mueve hacia la caja
    - El mono esta frente, a la izquierda/derecha, arriba, detras, debajo de la caja
    - El mono empuja la caja
    - El mono levanta la caja
    - El mono desplaza la caja 
    - El mono sube a la caja

Este es un ejemplo de todo lo que puede hacer el mono, nosotros debemos expresar todas las diferentes alternativas que podemos hacer para que el mono obtenga las bananas,
sin embargo, puede haber una manera de automatizar todo esto dentro de un modelo de programacion basada en reglas. 

Clips nos permite almacenar estas reglas como un cerebro realmente artificial.


Esto podemos trasportarlo a diferentes aspectos como analizar una imagen y poder observar que procedimientos se pueden generar, vamos a intentar generar todo este proceso en lenguaje C con el framework CUDA Q deNVIDIA, para que nosotros podamos tener algunas conseciones de rendimiento, ya que python es un lenguaje un poco mas lento, debemos investigar si existe una api de clips en C o en CUDA para que podamos integrar estos procesos de bases de conocimiento para hacer mas eficientes las diferentes cosas que podemos generar.

Todos estos movimientos podemos generar los conocimientos dependiendo de un problema, necesitamos generar estos ejercicios para lograr hacer nuestras IA's
