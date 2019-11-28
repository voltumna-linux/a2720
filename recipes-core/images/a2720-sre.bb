require recipes-core/images/voltumna-sre.bb
require include/a2720.inc
require include/a2720-sxe.inc

append_to_osrelease() {
	cat <<-__EOF__ >> ${IMAGE_ROOTFS}/etc/os-release
	VARIANT_ID="${BPN}"
	VARIANT="A2720 Power-supply (Runtime)"
	MACHINE="${MACHINE}"
	__EOF__
}
