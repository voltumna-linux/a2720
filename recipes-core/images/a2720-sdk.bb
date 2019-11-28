require recipes-core/images/voltumna-sdk.bb
require recipes-devtools/cgtpru/include/cgtpru_2.3.2.inc
require include/a2720.inc

POPULATE_SDK_POST_TARGET_COMMAND += " install_cgtpru_into_sdk;"

append_to_osrelease() {
	cat <<-__EOF__ >> ${IMAGE_ROOTFS}/etc/os-release
	VARIANT_ID="${BPN}"
	VARIANT="A2720 Power-supply (Cross Development)"
	MACHINE="${MACHINE}"
	__EOF__
}
