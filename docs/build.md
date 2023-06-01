# How to Build KickRPC from Jar into EXE

1. Download the source code directly from this [Here](https://github.com/MistyKnives/Kick-Discord-RPC/archive/refs/heads/main.zip)
2. Download Apache Maven from [Here](https://maven.apache.org/)
3. Put Apache Maven into your main drive e.g. c:/apache-maven
4. Go to the source code you downloaded, put it into a folder from the zip file
5. Open a command prompt inside the folder and run "mvn clean install". This will create a folder called "target" where you can get the compiled jar from. It will be called "KickRPC-v4.0.0-jar-with-dependencies.jar"
6. Download Launch4J from [Here](https://launch4j.sourceforge.net/)
7. Configure Launch4J accordingly to build the EXE from the JAR.
   - Project Requires MIN Java Version: 20
   - Here is a basic video on building a [Launch4J Program.](https://www.youtube.com/watch?v=NRptmWyrvvo)
8. You should successfully be able to run it.

Any programs, feel free to contact me on Discord: Misty#0666