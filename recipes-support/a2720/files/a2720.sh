#!/usr/bin/env bash

set -e
# set -x

WORKDIR=`pwd`
URL=http://127.0.0.1/api/a2720/publish

shopt -s extglob

if [ -f '/etc/a2720.cfg' ]; then
	link="$(basename $(readlink /etc/a2720.cfg))"
	substring="$(echo ${link} | sed -e 's/a2720-\(.*\).cfg/\1/g')"
	sn="${substring##+(0)}"
	curl -k -s ${URL}/sn -d"${sn}"
else
	curl -k -s ${URL}/sn -d'undefined'
	curl -k -s ${URL}/current -d'undefined'
	curl -k -s ${URL}/voltage -d'undefined'
	curl -k -s ${URL}/state -d'undefined'
fi
