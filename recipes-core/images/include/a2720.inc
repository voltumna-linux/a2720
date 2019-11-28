# TODO Controllare se c'e' cgt-pru nelle depepend di pru-icss
# TODO Controllare dove installa rispetto a cgt-pru
# IMAGE_INSTALL_append += "pru-icss pru-icss-staticdev"

ROOTFS_POSTPROCESS_COMMAND_append += " append_to_osrelease;"
