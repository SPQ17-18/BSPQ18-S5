# BSPQ18-S5
Repository for Team BSPQ18-S5

DESCRIPCIÓN:

Nuestro proyecto consiste en una compra de comida online. Está estructurada como un proyecto de maven Cliente-Servidor.

La parte de cliente se encuentra en la carpeta: NeverEmptyClient.

La parte servidor se encuentra en la carpeta: NeverEmptyServer.

Los servicios con los que se conecta el servidor son: EroskiService, FacebookService, GoogleService, PaypalService y VisaService.

PARA EJECUTAR EL PROYECTO:

1º Para compilar: mvn clean compile

2º  2.1) Para ejecutar cualquier servicio ir a la carpeta x y ejecutar: mvn exec:java -Pserver 

	2.2) Para ejecutar el servidor ir a la carpeta NeverEmptyServer y ejecutar: mvn exec:java -Pserver
	
	2.3) Para ejecutar el cliente ir a la carpeta NeverEmptyClient y ejecutar: mvn exec:java -Pclient
   
3º Para los test: mvn test

   Este comando generará el report Contiperf
   
4º Para generar el report Cobertura: mvn cobertura:cobertura

   Para consultar el porcentaje testeado: mvn cobertura:check
