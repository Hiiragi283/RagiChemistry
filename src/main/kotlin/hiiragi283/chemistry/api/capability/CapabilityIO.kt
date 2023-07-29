package hiiragi283.chemistry.api.capability

interface CapabilityIO<T : Any> {

    var ioType: IOType

    fun getIOType(): IOType

    fun setIOType(type: IOType): T

}