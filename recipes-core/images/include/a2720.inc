IMAGE_INSTALL_append += "pru-icss pru-icss-staticdev"

ROOTFS_POSTPROCESS_COMMAND_append += " append_to_osrelease;"
