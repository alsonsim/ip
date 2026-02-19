# Deo project template

This is a project template for a greenfield Java project. It's named after BruceDropEmOff. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```
___________   _______________________________________^__
 ___   ___ |||  ___   ___   ___    ___ ___  |   __  ,----\
|   | |   |||| |   | |   | |   |  |   |   | |  |  | |_____\
|___| |___|||| |___| |___| |___|  | O | O | |  |  |        \
           |||                    |___|___| |  |__|         )
___________|||______________________________|______________/
           |||     I'd rather be at DisneyWorld...    /--------
-----------'''---------------------------------------'

____________________________________________________________
 Hello! I'm Deo
 What can I do for you?
 ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
```
                         PROBLEM SOLVING
                          P R O C E S S


            YES   =============================   NO
     +-----------|| Does the Darn Thing work? ||-----------+
     |            =============================            |
     V                                                     V   
+----------+     +---------+                          +---------+
|   Don't  |  NO |   Does  |       +-------+     YES  | Did you |
|   mess   | +---|  anyone |<------|  YOU  |<---------|   mess  |
| with it! | |   |  know?  |       | MORON |          | with it |
+----------+ |   +---------+       +-------+          +---------+
     |       V        | YES                                |  NO
     |    +------+    +-----------+                        |
     |    | HIDE |                V                        V
     |    |  IT  |            +--------+             +-----------+
     |    +------+            |  YOU   |        YES  | WILL THEY |
     |       |       +------->|  POOR  |<------------| CATCH YOU?|
     |       |       |        |BASTARD!|             +-----------+
     |       |       |        |________|                   |  NO
     |       |       |             |                       |
     |       |       |             V                       V
     |       |       |      +---------------+        +-----------+
     |       |       |  NO  | CAN YOU BLAME |        |DESTROY THE|
     |       |       +------| SOMEONE ELSE? |        |  EVIDENCE |
     |       |              +---------------+        +-----------+
     |       |                     |  YES                  |
     |       |                     v                       |
     |       |      ============================           |
     |       +---->||           N O            ||<---------+
     +------------>||      P R O B L E M       ||
                    ============================
```
