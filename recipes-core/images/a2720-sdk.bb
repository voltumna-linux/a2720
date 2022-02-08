require recipes-core/images/voltumna-sdk.inc
require recipes-devtools/ti-cgt-pru/include/cgtpru_2.3.2.inc
require include/a2720.inc

IMAGE_INSTALL_append += "pru-icss-staticdev"
TOOLCHAIN_HOST_TASK_append += "nativesdk-ti-cgt-pru"
POPULATE_SDK_POST_TARGET_COMMAND += " install_cgtpru_into_sdk;"

append_to_osrelease() {
	cat <<-__EOF__ >> ${IMAGE_ROOTFS}/etc/os-release
	VARIANT_ID="${BPN}"
	VARIANT="A2720 Power-supply (Cross Development)"
	MACHINE="${MACHINE}"
	__EOF__
}
