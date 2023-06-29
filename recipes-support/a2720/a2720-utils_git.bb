SUMMARY = "Main utilities for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

DEPENDS += "a2720-mod libwebsockets"
RDEPENDS_${PN} += "a2720-mod libwebsockets"

SRCREV = "d1d0e493098958cb18eff6645fedefa0e9907996"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-utils.git;protocol=https;branch=development"

S = "${WORKDIR}/git"

SYSTEMD_SERVICE_${PN} = "pru0tows.service pru1tows.service"

FILES_${PN} += "${datadir}"

do_install() {
	oe_runmake DESTDIR=${D} release install
}

inherit systemd
