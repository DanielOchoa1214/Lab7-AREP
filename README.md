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

<img width="795" alt="Screenshot 2023-10-10 at 10 50 15 AM" src="https://github.com/DanielOchoa1214/Lab7-AREP/assets/77862016/62f1c43b-6362-48fe-9561-addcc7d717f5">

En donde se especifican los siguientes componentes: 

Browser: Representa al cliente
LoginService: En la arquitectura que se creo en el taller este componente corresponderia con la fachada del servidor
Otherservice: En la arquitectura que se creo en el taller este componente corresponderia con el login

### Despliegue

Ademas el proyecto java fue desplegado de 2 maquinas virtuales de AWS

<img width="1438" alt="Screenshot 2023-10-10 at 10 56 22 AM" src="https://github.com/DanielOchoa1214/Lab7-AREP/assets/77862016/418ef369-9da7-44a3-b2d9-5efebd56687b">

Y hay un videito para que me creas que lo desplegue en AWS, esta en el root del proyecto ;) (Era muy pesado para poner en el readme 😣)

## Agradecimientos

* A nuestro querido profesor de Arquitectura empresariales Daniel Benavides
* Jorge, el mejor monitor 
* Figo

