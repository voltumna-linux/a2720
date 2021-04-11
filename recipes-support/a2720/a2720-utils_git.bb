SUMMARY = "Main utilities for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

DEPENDS += "a2720-fw gsl"
RDEPENDS_${PN} += "a2720-mod"

SRCREV = "e31f956d22e2528a645c08f5a656fb7918d3b91f"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-utils.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}"

do_install() {
	oe_runmake DESTDIR=${D} install
}
