# Prices Calculator
## _The API to calculate your prices with_

[![Api](https://seeklogo.com/images/S/spring-boot-logo-9D6125D4E7-seeklogo.com.png)](https://seeklogo.com/images/S/spring-boot-logo-9D6125D4E7-seeklogo.com.png)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://pricescalculator.fly.dev/swagger-ui/#/Price)



Este proyecto fué creado para el calculo de precios de un ecommerce.
El calculo se realiza dependiendo de unos datos de entrada y se construyó
pensando en estos requisitos:

- Diseño y construcción del servicio.
- Calidad de Código.
- Resultados correctos en los test.


## Features

- Puede parametrizar los datos de sus productos
- No requiere grandes configuraciones
- La Api se encuentra desplegada en la nube
- Realiza el calculo con datos cargados en una db H2
- Rapido y fácil de usar

Para la construccion de este proyecto se utilizaron varias tecnologias según los requisitos solicitados , estas son algunas de las utilizadas:
## Tech

Por acá alguna de las tecnologias que utilicé para la construcción del proyecto:
- [SpringBoot] - Construccion de la Api.
- [Maven] - Gestion de Dependencias y creacion del Jar.
- [GitHub] - Alojar nuestro codigo y hacer control de versiones.
- [Flyio] - Servicio gratuito para desplegar nuestra aplicacion.
- [Docker] - Construcción de la imagen para poder desplegar en Flyio

## Developent
Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

    Acepte como parámetros de entrada: 
        {
            "applicationDate": "2020-06-15T00:50:00",
            "brandId": 1,
            "productId": 35455
        }
    Devuelva como datos de salida: 
    {
        "rate": 3,
        "brandId": 1,
        "productId": 35455,
        "applicationDate": "2020-06-15T00:50:00",
        "finalPrice": 30.5
    }
## Requisitos previos

- Debes tener instalado Java
- Debes tener algún IDE  para descargar el proyecto y ejecutarlo usando maven

## Instalation

- Descargar el repositorio a tu maquina local.[(aqui)](https://github.com/Andresdavid2310/Api-Price-Calculator-for-Eco)
- Debes ejecutar el build con maven y esperar qué se descarguen las dependencias
- Debes ejecutar el proyecto en tu local ejecutando el Run , necesitas la version 17 en adelante de java para ejecutarlo.
- Tambien puedes ejecutar los test unitarios y de integración alojados en la carpeta src/test

## Uso

Una vez tengas ejecutando el proyecto en tu local puedes ver la documentación y la base de datos.

1.Puedes revisar los endpoint de la documentacion en swagger.

    Para el local la URL =  http://localhost:8080/swagger-ui/#/Price 
    Para la nube la URL = https://pricescalculator.fly.dev/swagger-ui/#/Price

2.Use H2 Console para acceder a la BD:

    URL = http://localhost:8080/h2-console/     
    El ingreso a la bd es con:
        usuario:admin 
        contraseña: admin

3.Por defecto y para ser mas practicos se tiene un script qué crea los datos en las tablas de la BD cuando se arranca la aplicacion, esos datos son:

### BRAND

| BRAN_ID | NAME |
|--------|-------------|
|   1    | ZARA        |
|   2    | ZARA HOME   |

### PRODUCT

| PRODUCT_ID | NAME |
|--------|-------------|
|   1    | Producto 1  |
|   2    | Producto 2  |

### PRICE DETAIL

|   PRICE_LIST   |     CURR    |       END_DATE        |    PRICE    | PRIORITY | START_DATE   |
|----------------|-------------|-----------------------|-------------|-------------|-------------|
|        1       | EUR         |   2020-12-31-23.59.59 |   35.50     |      0      |      2020-06-14-00.00.00     |
|        2       | EUR         |   2020-06-14-18.30.00 |   25.45     |      1      |      2020-06-14-15.00.00       | 
|        3       | EUR         |   2020-06-15-11.00.00 |   30.50     |      1      |     2020-06-15-00.00.00        | 
|        4       | EUR         |   2020-12-31-23.59.59 |   38.95     |      1      |2020-06-15-16.00.00       | 

### PRICE
|   PRICE_ID   |     BRAND_ID    |       PRICE_LIST        |    PRODUCT_ID    | 
|----------------|-------------|-----------------------|-------------|
|        1       | 1           |           1           |    35455    |      
|        2       | 1           |           2           |   35455     |      
|        3       | 1           |           3           |   35455     |        
|        4       | 1           |           4           |   35455     | 

    Si necesita modificar o agregar algun dato se puede hacer actualizando el archivo: src/main/resources/data.sql

4.Puedes Empezar a probar el endpoint de calculo de precios,  por acá la colección de postman con la información para probar en local y produccion :

    https://api.postman.com/collections/11985200-30040781-5644-47c1-a518-f4cd5b87094e?access_key=PMAT-01HCW3EBRMW978YRWKAJ59KCSJ

## Contacto

Andrés Benavides
Email: dandavi2310@hotmail.com
