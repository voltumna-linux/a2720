SUMMARY = "PRUSS Firmware for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

DEPENDS = "ti-cgt-pru-native pru-icss"

require recipes-ti/includes/ti-paths.inc

SRCREV = "87e21a071575637f7184a50d9e58ada02099e361"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-fw.git;protocol=https;branch=development"

S = "${WORKDIR}/git"

# COMPATIBLE_MACHINE = "beaglebone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OEMAKE += " \
	CLPRU="${TI_CGT_PRU_INSTALL_DIR}/bin/clpru --include_path=${STAGING_DIR_TARGET}${root_prefix}/include/${TI_PDK_LIMIT_SOCS} --include_path=${TI_CGT_PRU_INSTALL_DIR}/include --include_path=${STAGING_DIR_TARGET}${root_prefix}/include" \
	CLPRUFLAGS="--section_sizes --endian=little --silicon_version=3 --opt_level=2 --keep_asm --display_error_number --verbose_diagnostics --symdebug:none --hardware_mac=on" \
	CLPRULDFLAGS="--warn_sections --search_path=${TI_CGT_PRU_INSTALL_DIR}/lib --library=libc.a --library=${STAGING_DIR_TARGET}${root_prefix}/lib/rpmsg_lib.lib" \
	HEXPRU="${TI_CGT_PRU_INSTALL_DIR}/bin/hexpru" \
	"

INSANE_SKIP_${PN} += "arch"

FILES_${PN} = "${base_libdir}"

SYSTEMD_SERVICE_${PN} = "a2720-fw.service"

do_install() {
	oe_runmake DESTDIR=${D} install
}

inherit systemd
