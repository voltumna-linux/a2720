require recipes-core/images/voltumna-sde.inc
require include/a2720.inc
require include/a2720-sxe.inc

# IMAGE_INSTALL_append += "ti-cgt-pru"

append_to_osrelease() {
	cat <<-__EOF__ >> ${IMAGE_ROOTFS}/etc/os-release
	VARIANT_ID="${BPN}"
	VARIANT="A2720 Power-supply (Development)"
	MACHINE="${MACHINE}"
	__EOF__
}
