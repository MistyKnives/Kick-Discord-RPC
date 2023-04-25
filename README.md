# Kick-Discord-RPC
A Simple Java Program to display your Kick Livestream on your Discord Profile!
# Getting Started
1. Start by updating the config.json to setup the Kick and OBS Side</br>
    * You can get the OBS Websocket information from the following images:
      <img src="https://raw.githubusercontent.com/MistyKnives/Kick-Discord-RPC/main/images/find_websocket.png"></img>
    * Make sure you Tick "Enable WebSocket server" for this to work.
      <img src="https://raw.githubusercontent.com/MistyKnives/Kick-Discord-RPC/main/images/connection_info.png"></img>
    * Open the .jar file with your favourite ZIP/RAR editor
    * go into the "config.json" files with a text editor and replace username, host, port, and password with the information you've got
    * username = Your Kick username
    * host, port, and password = From OBS Websocket</br>
      <img src="https://raw.githubusercontent.com/MistyKnives/Kick-Discord-RPC/main/images/find_config.png"></img>
      <img src="https://raw.githubusercontent.com/MistyKnives/Kick-Discord-RPC/main/images/config.png"></img>
      </br>
2. After that you can run the program using the following line "java -cp .\KickRPC.jar uk.co.mistyknives.kickrpc.Client"
   <img src="https://raw.githubusercontent.com/MistyKnives/Kick-Discord-RPC/main/images/run_program.png"></img>
   </br>
3. Once the program is running, the output should look like this</br>
   * the colour is broken at the moment so i'll fix it soon!
   <img src="https://raw.githubusercontent.com/MistyKnives/Kick-Discord-RPC/main/images/running.png"></img>
4. Now go ahead and start your stream!
   <img src="https://raw.githubusercontent.com/MistyKnives/Kick-Discord-RPC/main/images/obs.png"></img>
5. Then it will look like this on your profile, enjoy!
</br>
   <img src="https://raw.githubusercontent.com/MistyKnives/Kick-Discord-RPC/main/images/profile.png"></img>
</br>
<h1>Errors</h1>
If you receive any errors while testing my project, please don't be afraid to put them in the <a href="https://github.com/MistyKnives/Kick-Discord-RPC/issues">Issues Section</a> as this will help me detect issues easier!
