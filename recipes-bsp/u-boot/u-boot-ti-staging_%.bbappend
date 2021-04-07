do_install_append() {
	sed -i -e "s,.*fdtfile.*,fdtfile=am335x-a2720.dtb,g" ${DEPLOY_DIR_IMAGE}/uEnv.txt
}
