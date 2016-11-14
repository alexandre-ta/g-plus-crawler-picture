== README ==
2013/11/29 v1

== Description ==
This project contains two parts :
- sources package containing all src files
- test package to test io tools

== Project ==

Main :
Initialise all folders and files
If the user ids file doesn't exist, the program parse the file names.txt and retrieve from google api all corresponding users.

Backup :
Retrieve from log user file all user ids and eliminate them in ids file.

StartProcess :
Run defined number of thread from config file
Download all photos and store them into server folder (/IMGS/<user id>.<photo id>.ext)
Save information in log file : [<photo file name>, <content>, <nb like>]

Config file :
eg. javadoc

Any issues are logged into log_error file