
def get_filespath_extra(d, subpath):
	metaroot = next((p for p in d.getVar('BBPATH').split(':') if os.path.basename(p) == 'meta'), None)
	if metaroot:
		return os.path.join(metaroot, subpath) + ":"
	return ""

# TODO: improve this, since it is very hacky that this recipe need to build tunctl.
# include the existing qemu-helper-native
require recipes-devtools/qemu/qemu-helper-native_1.0.bb
# get the path to tunctl.c
FILESEXTRAPATHS_prepend := "${@get_filespath_extra(d, 'recipes-devtools/qemu/qemu-helper')}"

# provide it, to replace the existing
PROVIDES += "qemu-helper-native"

# replace qemu with qemu-xilinx
DEPENDS_remove = "qemu-native"
DEPENDS_append = " qemu-xilinx-native"

