IMAGE_INSTALL_append += "a2720-dts a2720d a2720-webui pru-icss"

ROOTFS_POSTPROCESS_COMMAND_append += " append_to_osrelease;"
