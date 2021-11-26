# mvn clean package --add-modules java.smartcardio
mvn clean package
java -jar --add-modules java.smartcardio ./target/apdu-demo-1.0-SNAPSHOT.jar
