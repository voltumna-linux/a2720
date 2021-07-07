SUMMARY = "Tha kernel module driver and related files for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

inherit module

RDEPENDS_${PN} += "a2720-fw"

SRCREV = "8c5ae1d992edf3d4a6d5d2b462e0728c9f4e286b"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-mod.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

RPROVIDES_${PN} += "kernel-module-a2720"

FILES_${PN} += "${sysconfdir}"
FILES_${PN}-dev = "${includedir}"

do_install_append() {
	install -d ${D}/etc/modules-load.d
	install -m 0644 a2720.conf ${D}/etc/modules-load.d
	
	install -d ${D}/etc/udev/rules.d/
	install -m 0644 90-a2720.rules ${D}/etc/udev/rules.d
	
	install -d ${D}${includedir}
	install -m 0644 a2720.h ${D}${includedir}
}
