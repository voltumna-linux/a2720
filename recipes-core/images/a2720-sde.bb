require recipes-core/images/voltumna-sde.bb
require recipes-devtools/ti-cgt-pru/include/cgtpru_2.3.2.inc
require include/runtime.inc
require include/development.inc
VARIANT = "A2720 Power-supply (Development)"

IMAGE_PREPROCESS_COMMAND += " install_cgtpru_into_sde;"
