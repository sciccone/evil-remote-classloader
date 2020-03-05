# evil-remote-classloader
Simplified Java remote classloader to server a malicious class


## Compile

`mvn clean package`

## Usage

`java -jar target/evil-remote-classloader-jar-with-dependencies.jar -l <LISTEN_HOST> -p <HTTP_PORT> -c <OS_COMMAND>`
