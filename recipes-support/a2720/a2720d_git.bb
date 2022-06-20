SUMMARY = "TCP/IP daemon for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

DEPENDS += "a2720-mod"
RDEPENDS_${PN} += "a2720-utils"

SRCREV = "2b9ff779d16bf36862f47861260f98771031f964"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720d.git;protocol=https;branch=development \
	file://a2720d.service \
	"

S = "${WORKDIR}/git"

SYSTEMD_SERVICE_${PN} = "a2720d.service"

do_install() {
	oe_runmake DESTDIR=${D} release install
	
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/a2720d.service \
		${D}${systemd_unitdir}/system
}

inherit systemd
