SUMMARY = "PRUSS Firmware for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

DEPENDS = "ti-cgt-pru-native"

require recipes-ti/includes/ti-paths.inc

SRCREV = "35a9ba7af9744b3d00f1ad8dff45fcc759d05ee7"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-fw.git;protocol=https;branch=optimize-firmware \
	file://a2720-fw.service \
	"

S = "${WORKDIR}/git"

# COMPATIBLE_MACHINE = "beaglebone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OEMAKE += " \
	CLPRU="${TI_CGT_PRU_INSTALL_DIR}/bin/clpru -i${STAGING_DIR_NATIVE}${root_prefix}/include/${TI_PDK_LIMIT_SOCS} -i ${TI_CGT_PRU_INSTALL_DIR}/include" \
	CLPRUFLAGS="--section_sizes --endian=little --silicon_version=3 -k --symdebug:none --hardware_mac=on" \
	CLPRULDFLAGS="-l ${TI_CGT_PRU_INSTALL_DIR}/lib/libc.a" \
	HEXPRU="${TI_CGT_PRU_INSTALL_DIR}/bin/hexpru" \
	"

FILES_${PN} = "${base_libdir}"
FILES_${PN}-dev = "${includedir}"

SYSTEMD_SERVICE_${PN} = "a2720-fw.service"

do_install() {
	oe_runmake DESTDIR=${D} install

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/a2720-fw.service ${D}${systemd_unitdir}/system
}

inherit systemd
