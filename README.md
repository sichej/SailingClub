This is the final project for Software Engineering class attended in 2022 at Univeristy of Parma (UniPr), it's a client-server application to handle a sailing club. 
A complete italian guide can be found in the PDF `Funzionamento_progetto.pdf`.
For all the rest of the work who doesn't speak italian, here how it works:

### Projects creations

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f5f6dde9fb2848058eea67885729fe55)](https://app.codacy.com/gh/sichej/SailingClub?utm_source=github.com&utm_medium=referral&utm_content=sichej/SailingClub&utm_campaign=Badge_Grade_Settings)

The easiest way is to create two new Java projects, for the Server one copy the Server folder inside the new project, same for the Client. After that remains only to include some libraries as specified in the following paragraphs.
For both of them is necessary to include `JUnit5` because the include tests.

### Server
Everything starts form `Server.java` (sailingclub.server.Server.java) that contains the main class, it doesn't require any command line parameters but only the configuration file located in config/srv_config.json. Inside the configuration file it's possible to specify some parametrs as Database credentials and port number.

To handle json file is necessary the GSON library, include gson-2.8.2.jar file (included in the code) to the project's build path, also because the server connects to a Database, is necessary to include the mySql connector mysql-connector-java-8.0.27.jar, both of them are included and you can find them under libs folder.

### Client
Everything starts form `Client.java` (sailingclub.client.Client.java) that contains the main class and also the method to launch JavaFX. The client requires to be lauched with two command line arguements, one of them is the server IP and the other the port number.
Once connected with the server it's possible to login with the credential (username and password), passwords are hashed on the database.

#### Member GUI
Once logged as Member you can see three menu on the left side:
On the Boats managment you can see all the user's boats, onece cliked on the specific boat you enter the boat panel where appear all the boat's characteristic.
On the Profile managment it's possible to logout, add payment methods and pay the club annual fee.
On the Race managment it's possible to sign up for a race with a specific boat.
On the Notification panel are shown all the notifications sended by the employees.

#### Employee GUI
It is possible to add or remove races, add or remove members and send notifications for the fee payemnt too.
A cool function is the possibility to see all the payment the members have done in the past.

