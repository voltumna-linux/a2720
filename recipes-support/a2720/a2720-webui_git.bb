SUMMARY = "A2720 Web UI"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f6412ce0400baf1d8177bd65d0f33550"

DEPENDS =+ "nodejs-native"
RDEPENDS_${PN} += "a2720-utils nginx python3-requests"

SRCREV = "88e3c3b4fc3902f3c82967a08b2ee5e28ff24288"
SRC_URI = "git://gitlab.elettra.eu/a2720/a2720-webui.git;protocol=https;branch=development \
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

	rm -fr ${D}${WWWDIR}/images

	install -d ${D}${WWWDIR}/login/assets/fonts/
	lnr ${D}${WWWDIR}/fonts ${D}${WWWDIR}/login/assets/fonts/overpass-webfont 

	# Pre-compress files
	find ${D}${WWWDIR} -type f \( -iname "*.css" \
		-or -iname "*.js" -or -iname "*.html" \
		-or -iname "*.jpg" \) -exec gzip -9 {} \;
	gunzip ${D}${WWWDIR}/index.html.gz
	
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
