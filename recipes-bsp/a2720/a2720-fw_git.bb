SUMMARY = "PRUSS Firmware for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

DEPENDS = "ti-cgt-pru-native"

require recipes-ti/includes/ti-paths.inc

SRCREV = "6fc48791ec1f3f24bbc716b3fec73aa25b42936a"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-fw.git;protocol=https \
	file://a2720-fw.service \
	"

S = "${WORKDIR}/git"

# COMPATIBLE_MACHINE = "beaglebone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OEMAKE += " \
	CLPRU="${TI_CGT_PRU_INSTALL_DIR}/bin/clpru -i ${TI_CGT_PRU_INSTALL_DIR}/include" \
	CLPRULDFLAGS="-l ${TI_CGT_PRU_INSTALL_DIR}/lib/libc.a" \
	HEXPRU="${TI_CGT_PRU_INSTALL_DIR}/bin/hexpru" \
	"

FILES_${PN} = "${base_libdir}"
FILES_${PN}-dev = "${includedir}"

SYSTEMD_SERVICE_${PN} = "a2720-fw.service"

do_install() {
	install -d ${D}${includedir}
	install -m 0644 *.h ${D}${includedir}

	install -d ${D}${base_libdir}/firmware
	install -m 0644 text*.bin ${D}${base_libdir}/firmware
	
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/a2720-fw.service ${D}${systemd_unitdir}/system
}

inherit systemd
