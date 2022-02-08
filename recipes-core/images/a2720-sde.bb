require recipes-core/images/voltumna-sde.bb
require recipes-devtools/ti-cgt-pru/include/cgtpru_2.3.2.inc
require include/a2720.inc

# Remove os-update asap
IMAGE_INSTALL_append += "ti-cgt-pru pru-icss-staticdev os-update"
IMAGE_PREPROCESS_COMMAND += " install_cgtpru_into_sde;"

append_to_osrelease() {
	cat <<-__EOF__ >> ${IMAGE_ROOTFS}/etc/os-release
	VARIANT_ID="${BPN}"
	VARIANT="A2720 Power-supply (Development)"
	MACHINE="${MACHINE}"
	__EOF__
}
