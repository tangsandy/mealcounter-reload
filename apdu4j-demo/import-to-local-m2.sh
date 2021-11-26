set -e
mkdir -pv ./lib
curl -L https://github.com/martinpaljak/apdu4j/releases/download/v21.04.12/apdu4j.jar -o ./lib/apdu4j.jar
mvn install:install-file \
   -Dfile=./lib/apdu4j.jar \
   -DgroupId=com.github.martinpaljak \
   -DartifactId=apdu4j \
   -Dversion=21.04.12 \
   -Dpackaging=jar \
   -DgeneratePom=true
rm -rf ./lib

# https://stackoverflow.com/questions/4955635/how-to-add-local-jar-files-to-a-maven-project