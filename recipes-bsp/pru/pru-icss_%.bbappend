FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append += " \
	file://increase-rpmsg-buffer-size.patch \
	"

PACKAGE_ARCH = "${TUNE_PKGARCH}"
