require recipes-core/images/voltumna-sdk.inc
require recipes-devtools/ti-cgt-pru/include/cgtpru_2.3.2.inc
require include/development.inc
require include/cross-development.inc

POPULATE_SDK_POST_TARGET_COMMAND += " install_cgtpru_into_sdk;"
