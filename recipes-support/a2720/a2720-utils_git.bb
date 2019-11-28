SUMMARY = "Main utilities for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

DEPENDS += "a2720-fw"
RDEPENDS_${PN} += "a2720-mod"

SRCREV = "ced0d51990a6831ebe255692ce38762307667dbb"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-utils.git;protocol=https"

S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}"

do_install() {
	oe_runmake DESTDIR=${D} install
}
