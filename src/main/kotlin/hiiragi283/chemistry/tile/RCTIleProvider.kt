package hiiragi283.chemistry.tile

import hiiragi283.chemistry.capability.RCCapabilityProvider
import hiiragi283.chemistry.capability.energy.RCEnergyStorage
import hiiragi283.chemistry.capability.fluid.RCFluidTank
import hiiragi283.chemistry.capability.item.RCItemHandler


object RCTIleProvider {

    interface Inventory {

        fun createInventory(): RCCapabilityProvider<RCItemHandler<*>>

    }

    interface Tank {

        fun createTank(): RCCapabilityProvider<RCFluidTank>

    }

    interface Energy {

        fun createBattery(): RCCapabilityProvider<RCEnergyStorage>

    }
}