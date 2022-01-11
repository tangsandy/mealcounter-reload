#!/bin/bash


sudo echo $help > /tmp/ip.txt

random=$RANDOM
while ! nc -z dynupdate.no-ip.com 80
do
    echo "waiting for godot.." >> /tmp/ip.txt
    sleep 1
done
help=$(hostname -I  | awk '{print $1}')

/usr/bin/curl -vs "http://tangsandy:Klaw9Om7@dynupdate.no-ip.com/nic/update?hostname=newnfcmeal.ddns.net&q=$random&myip=$help" >> /tmp/ip.txt 2>&1

