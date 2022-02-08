#!/usr/bin/env bash

set -e
# set -x

WORKDIR=`pwd`
URL=http://127.0.0.1/api/a2720/publish

shopt -s extglob

if [ -f '/etc/a2720.cfg' ]; then
	zfilledsn="$(sed -n 's/^serial-number.*A2720-\([0-9]*\)/\1/p' /etc/a2720.cfg)"
	sn="${zfilledsn##+(0)}"
	curl -k -s ${URL}/sn -d ${sn}
	curl -k -s ${URL}/psconfig -T /etc/a2720.cfg
else
	curl -k -s ${URL}/sn -d'undefined'
	curl -k -s ${URL}/psconfig -d'undefined'
#	curl -k -s ${URL}/current -d'undefined'
#	curl -k -s ${URL}/voltage -d'undefined'
#	curl -k -s ${URL}/state -d'undefined'
#	curl -k -s ${URL}/kp -d'undefined'
#	curl -k -s ${URL}/ki -d'undefined'
#	curl -k -s ${URL}/kd -d'undefined'
#	curl -k -s ${URL}/slewrate -d'undefined'
fi
