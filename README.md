# jv-retrofit-get-users
# Parse site StackOverflow Users using retrofit library

This application works with api "stackexchange.com"
gets a list of users of the site "stackoverflow" and displays data only those who meet the given requirements
- location    - list
- reputation - not lower than the specified
- tags - list
- answers - at least one



## To build and start the project you need:
- build the application with the command "gradle clean shadowJar"
- for start "java -jar build/libsjv-retrofit-get-users-0.0.1-SNAPSHOT-all.jar"
