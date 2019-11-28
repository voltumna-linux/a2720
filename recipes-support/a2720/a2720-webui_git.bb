SUMMARY = "A2720 Web UI"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

DEPENDS =+ "nodejs-native"
RDEPENDS_${PN} += "a2720-utils nginx"

SRCREV = "1fc4d04f94fe2caf2da8219c92a2ee9346cb59ed"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-webui.git;protocol=https \
	file://a2720.service \
	file://a2720 \
	file://a2720.conf \
	file://a2720.sh \
	"

S = "${WORKDIR}/git"

SYSTEMD_SERVICE_${PN} = "a2720.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_compile() {
	npm install
	npm run build
}

FILES_${PN} += "${systemd_unitdir}/system ${WWWDIR}"
DIRFILES = "1"

do_install() {
	install -d ${D}${WWWDIR}
	cp -r ${S}/dist/* ${D}${WWWDIR}

	# TODO Compress (at the moment not compatible wit React router)
	# find ${D}${WWWDIR} -type f \( -iname "*.txt" -or -iname "*.css" -or -iname "*.js" -or -iname "*.html" \) -exec gzip {} \;
	
	# Make authentication works
	install -d ${D}${WWWDIR}/auth
	touch ${D}${WWWDIR}/auth/user ${D}${WWWDIR}/auth/root

	# a2720 service	
	install -d ${D}${base_sbindir}
	install -m 0755 ${WORKDIR}/a2720 ${WORKDIR}/a2720.sh \
		${D}${base_sbindir}
	
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/a2720.service \
		${D}${systemd_unitdir}/system

	# Nginx configuration
	install -d ${D}${sysconfdir}/nginx/location-conf.d
	install -m 0644 ${WORKDIR}/a2720.conf \
		${D}${sysconfdir}/nginx/location-conf.d
}

inherit systemd
