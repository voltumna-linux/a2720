SUMMARY = "Tha kernel module driver and related files for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

inherit module

DEPENDS += "a2720-fw"
RDEPENDS_${PN} += "a2720-fw"

SRCREV = "6c2956202f6c9b9936fafacaba473afb5b4ff98f"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-mod.git;protocol=https;"

S = "${WORKDIR}/git"

RPROVIDES_${PN} += "kernel-module-a2720"

do_compile_prepend() {
	sed -i -e 's,#include <a2720.h>,#include "../recipe-sysroot/usr/include/a2720.h",g' a2720.c
}

FILES_${PN} += "etc"

do_install_append() {
	install -d ${D}/etc/modules-load.d
	install -m 0644 a2720.conf ${D}/etc/modules-load.d
	
	install -d ${D}/etc/udev/rules.d/
	install -m 0644 90-a2720.rules ${D}/etc/udev/rules.d
}
