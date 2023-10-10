# Taller 7 AREP - Daniel Sebastian Ochoa Urrego

En este taller implementaremos una arquitectura segura en todos sus niveles, creando servidores HTTPS lo que añadira integridad, aspectos de autorizacion y confidencialidad a nuestra arquitectura. Ademas para asegurar una autenticacion bien construida se añadio una funcionalidad de login donde las constraseñas se guardan y comparan con sus hashes

## Iniciando

Estas instrucciones te ayudarán a tener una copia de este proyecto corriendo en tu máquina local, en donde podras hacer pruebas o desarrollar sobre él 

### Prerrequisitos

* Git 
* Java
* Maven

Si aún no tienes instaladas estas tecnologias, los siguientes tutoriales te pueden ayudar

* Git: https://www.youtube.com/watch?v=4xqVv2lTo40
* Java: https://www.youtube.com/watch?v=BG2OSaxWX4E
* Maven: https://www.youtube.com/watch?v=1QfiyR_PWxU

### Instalando el proyecto

Para hacer una copia local del proyecto, debes abrir tu terminal, dirigirte al directorio donde quieras que este el proyecto y usar el siguiente comando

```
git clone https://github.com/DanielOchoa1214/Lab7-AREP.git
```

Luego muevete al directorio creado y desde ahi ejecuta los siguientes comandos

```
 java -cp "target/classes:target/dependency/*" org.example.facade.SecureFacade
 java -cp "target/classes:target/dependency/*" org.example.login.SecureLogin
```

Ya que la aplicación haya iniciado, puedes dirigirte a tu navegador de preferencia y entrar en https://localhost:37000 para ver la app corriendo, en ella encontraras una muy bonita página que cree con mucho esfuerzo donde puedes hacer login

<img width="486" alt="Screenshot 2023-10-10 at 10 43 01 AM" src="https://github.com/DanielOchoa1214/Lab7-AREP/assets/77862016/2a0f57a4-c061-4f9b-a640-249fadd257c8">

## Corriendo los tests

### Test de integración

Para probar que el desarrollo de la aplicación fuera correcto sé probo cada funcionalidad en ella corriendo, para ello usamos un usuario y contraseña no validos y otro que fuera valido

Usuario no registrado: 
<img width="471" alt="Screenshot 2023-10-10 at 10 44 07 AM" src="https://github.com/DanielOchoa1214/Lab7-AREP/assets/77862016/7f80bf3c-2052-4184-9c45-039fd85e94e6">

Usuario registrado: 
<img width="553" alt="Screenshot 2023-10-10 at 10 44 50 AM" src="https://github.com/DanielOchoa1214/Lab7-AREP/assets/77862016/dce12180-17b0-404a-94d6-a4bce551ca74">

## Documentacion

Para visualizar la documentación del proyecto solo debes correr el siguiente comando desde el directorio raiz del proyecto 

```
mvn javadoc:javadoc
```

Y en la siguiente ruta encontrarás el archivo index.html en donde si lo abres desde el navegador podras ver toda la documentación

```
./target/site/apidocs
```

## Construido con

* Amor
* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/) - IDE de desarrollo

## Version

1.0-SNAPSHOT

## Autores

Daniel Sebastián Ochoa Urrego - [DanielOchoa1214](https://github.com/DanielOchoa1214)

## Licencia

GNU General Public License family

## Diseño

Para simular desde un mismo proyecto la arquitectura propuesta se separaron pro paquetes los componentes, un componente de fachada encargado de mostrar la pagina web y de hacer la conexion con el componente del login, y un paquete de login que contiene toda la logica del login.

## Arquitectura

La arquitectura de este taller es presentada con el siguiente diagrama 



En donde se especifican los siguientes componentes: 

Security Group: Grupo de seguridad de la instancia en AWS que lproteje los puertos de la maquina (firewal de nivel 1)
AWS-EC2: Instancia basica de una maquina virtual en la nube de Amazon
Docker Engine: Motor de los coontenedores instalado en la instancia
APP-LB-RoundRobin: Balanceador de cargas que implementa un algoritmo estatico de RoundRobin donde se rotan los servidorespor cada peticion
LogService: Servidor que almacenara en la base de datos el log, y devolvera los ultimos 10
MongoDB: Contenedor con la base de datos de mongo

## Como crear la imagenes y desplegar el proyecto

El proceso para crear las imagenes y el despliegue es el mismo, solo que se debe hacer en la instancia de la maquina virtual ya creada en el taller

### Creacion de las imagenes

Para automatizar la creacion de las imagenes y los contenedores se creo el archivo docker-compose.yml con l¡a siguiente informacion

<img width="1000" alt="Screenshot 2023-09-29 at 7 33 25 PM" src="https://github.com/DanielOchoa1214/Lab6-AREP/assets/77862016/3e02ae27-0d47-492f-8a62-f8470d7ed155">
<img width="1010" alt="Screenshot 2023-09-29 at 7 33 39 PM" src="https://github.com/DanielOchoa1214/Lab6-AREP/assets/77862016/b700b7f4-ed1b-44a2-8ec4-f143f3fcd3e8">

En este archivo especificamos como se crean las imagenes, en el hay 2 metodos de creacion, especificando la imagen con un Dockerfile y otro, trayendo la imagen de docker hub, aqui no se ahondara mucho ya que se explico en detalle en el taller anterior.

Lo nuevo que aparece en el archivo es la seccion de "network" en esta estamos especificando la red virtual que crearemos donde estaran los contenedores, en este especificamos el tipo de red (atributo driver), el id de la red y el gateway de esta. La especificacion de la red se hace para que los contenedores puedan tener una IP estatica y sea facilmente desplegable en cualquier PC.

Ya con el archivo lo unico que debemos hacer es correr el siguiente comando

```
docker-compose up -d
```

Y si entras en la URL http://localhost:8080 encontraras (ojala) el app corriendo

### Despliegue

Para crear una instancia EC2 en AWS puedes solo seguir el siguiente [tutorial](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EC2_GetStarted.html). Ya cuando la hayas creado y te hayas conectado, simplemente clona el proyecto en la instancia y sigue el tutorial de la seccion anterior para desplegar. Puede que haga falta instalar git, docker y el plugin de compose en la instancia para poder correr el lab, pero para ello puedes seguir los tutoriales del inicio del README.

## Video

https://github.com/DanielOchoa1214/Lab6-AREP/assets/77862016/c95c1af3-9679-45ff-a306-4f50bff2e8b1

## Agradecimientos

* A nuestro querido profesor de Arquitectura empresariales Daniel Benavides
* Jorge, el mejor monitor 
* Figo

