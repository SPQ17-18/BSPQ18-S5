# BSPQ18-S5
Repository for Team BSPQ18-S5

DESCRIPCION:

Nuestro proyecto consiste en una compra de comida online. Esta estructura como un proyecto de maven Cliente-Servidor.

La parte cliente la delimita la carpeta: NeverEmptyCliet.

La parte servidora la delimitan las carpetas: NeverEmptyServer, EroskiService, FacebookService, GoogleService, PaypalService y VisaService.

PARA EJECUTAR EL PROYECTO:

1º Para compilar: mvn clean compile

2º 

   2.1) Para ejecutar el cliente: mvn exec:java -Pclient

   2.2) Para ejecutar el servidor: mvn exec:java -Pserver

3º Para los test: mvn test

   Este comando generará el report Contiperf
   
4º Para generar el report Cobertura: mvn cobertura:cobertura

   Para consultar el porcentaje testeado: mvn cobertura:check
