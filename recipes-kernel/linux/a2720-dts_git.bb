SUMMARY = "Tha device tree for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

inherit devicetree 

SRCREV = "530b080ad7c9223cc106e249694832bb9eff113d"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-dts.git;protocol=https;"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "beaglebone"

KERNEL_INCLUDE_append = " \
        ${STAGING_KERNEL_DIR}/include \
        "

FILES_${PN} = "usr"

do_install_append() {
	install -d ${D}/usr/boot/
	install -m 0644 ${D}/boot/devicetree/*.dtb ${D}/usr/boot/
	rm  -fr ${D}/boot/
}
