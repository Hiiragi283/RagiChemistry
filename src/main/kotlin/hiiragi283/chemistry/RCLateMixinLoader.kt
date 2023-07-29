package hiiragi283.chemistry

import zone.rong.mixinbooter.ILateMixinLoader

class RCLateMixinLoader: ILateMixinLoader {

    override fun getMixinConfigs(): MutableList<String> = mutableListOf("mixin.rc_late.json")

}