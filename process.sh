#!/usr/bin/env bash

export consumerKey="IuyRbIuFMdi7Qr2K3C1OARJD"
export consumerSecret="UAeLMrQDDrHaAgPcoNa2I9Xdlx0pujW9LAy6AYTY8bqidzLs0"
export accessToken="894411261248548864-ihvuaiU28qQqV6N1N24hMasVvVXuLL"
export accessTokenSecret="rLqHbPC1r5fIUJc3aSvcDyYDDuaflAOZkzArdHTB8S7B"

rm -f src/main/resources/yaml/twitter-config.yaml temp.yml
( echo "cat <<EOF >src/main/resources/yaml/twitter-config.yaml";
  cat src/main/resources/yaml/config-template.yaml;
) >temp.yml
. temp.yml
cat src/main/resources/yaml/twitter-config.yaml
rm -f temp.yml

java -jar target/twitter-1.0-SNAPSHOT-shaded.jar server src/main/resources/yaml/twitter-data.yaml