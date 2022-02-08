SUMMARY = "The device tree for A2720"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

DEPENDS = "dtc-native virtual/kernel"

# We don't use devicetree class' services because we would like to build
# device-tree also outside of the yocto build process 
#inherit devicetree 

SRCREV = "8dcbdb4cc25bb5feed9759eddf04fdc56b83af6d"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-dts.git;protocol=https;branch=development"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "beaglebone"

FILES_${PN} = "usr"

do_compile() {
	oe_runmake KERNEL_SRC=${STAGING_KERNEL_DIR}
}

do_install() {
	oe_runmake DESTDIR=${D} install
}
