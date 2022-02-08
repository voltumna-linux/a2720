FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append += " \
	file://disable_useless_gettimeofday.patch;patchdir=${WORKDIR} \
	"
