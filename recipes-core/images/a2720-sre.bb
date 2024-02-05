require recipes-core/images/voltumna-sre.inc
require include/runtime.inc
VARIANT = "A2720 Power-supply (Runtime)"

IMAGE_INSTALL_append += " os-update"
