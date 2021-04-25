# IbanExtractor
Java-based REST service that extracts IBAN accounts from a text.

## Getting Started
To download project:
```
git clone https://github.com/KozlovKA/IbanExtractor.git
```
### Installing
To install all libraries you need, print in `IbanExtractor` directory:
```sh
gradle build
```
### Quickstart
If you want to quickstart service you can use:
```sh
gradle build bootRun
open https://localhost:8443/ in browser 
```
Docker:
```sh
docker build --build-arg JAR_FILE=build/libs/*.jar -t myorg/myapp .
docker run -p 443:8443 myorg/myapp 
```

###Swagger Documentation
```sh
https://localhost:8443/swagger-ui.html#/iban-controller/showInputFormUsingGET
```
## Built With

* [Spring](https://spring.io/) - The web framework used
* [Docker](https://www.docker.com/) - A set of platform as a service (PaaS) products that use OS-level virtualization to deliver software in packages called containers.
* [Swagger](https://swagger.io/) - For creating documentation

## Authors

* **Kirill Kozlov** - 
[LinkedIn](https://www.linkedin.com/in/kozlovka/) | 
[GitHub](https://github.com/KozlovKA)
