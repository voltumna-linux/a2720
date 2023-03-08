SUMMARY = "Fast, reliable, and secure dependency management"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9afb19b302b259045f25e9bb91dd34d6"

# DEPENDS = "nodejs"
# RDEPENDS_${PN} = "nodejs-npm"

NPMDIR="npm"
SRC_URI = " \
	npm://registry.npmjs.org;package=yarn;version=${PV} \
	file://npm-shrinkwrap.json;subdir=${NPMDIR} \
	"
S = "${WORKDIR}/${NPMDIR}"

inherit npm

BBCLASSEXTEND = "nativesdk"
