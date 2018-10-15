#!/usr/bin/env bash

source keys.sh

rm -f src/main/resources/yaml/twitter-config.yaml temp.yml
( echo "cat <<EOF >src/main/resources/yaml/twitter-config.yaml";
  cat src/main/resources/yaml/config-template.yaml;
) >temp.yml
. temp.yml
cat src/main/resources/yaml/twitter-config.yaml
rm -f temp.yml

java -jar target/twitter-1.0-SNAPSHOT-shaded.jar server src/main/resources/yaml/twitter-data.yaml
rm -f src/main/resources/yaml/twitter-config.yaml