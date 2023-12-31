package hiiragi283.chemistry.api.capability

enum class IOType(val canInput: Boolean, val canOutput: Boolean) {
    INPUT(true, false),
    OUTPUT(false, true),
    GENERAL(true, true),
    CATALYST(false, false)
}