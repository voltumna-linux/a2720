SUMMARY = "TCP/IP daemon for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

DEPENDS += "a2720-fw"
RDEPENDS_${PN} += "a2720-utils"

SRCREV = "d9c2408213e6fd9f994e95fd3e9255e345799bd3"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720d.git;protocol=https \
	file://a2720d.service \
	"

S = "${WORKDIR}/git"

SYSTEMD_SERVICE_${PN} = "a2720d.service"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
	oe_runmake DESTDIR=${D} install
	
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/a2720d.service \
		${D}${systemd_unitdir}/system
}

inherit systemd
