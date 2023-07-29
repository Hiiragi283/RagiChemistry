package hiiragi283.chemistry.api.tile

import hiiragi283.chemistry.api.capability.RCCapabilityProvider
import net.minecraftforge.energy.IEnergyStorage
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.IItemHandler


object RCTIleProvider {

    interface Inventory {

        fun createInventory(): RCCapabilityProvider<IItemHandler>

    }

    interface Tank {

        fun createTank(): RCCapabilityProvider<IFluidHandler>

    }

    interface Energy {

        fun createBattery(): RCCapabilityProvider<IEnergyStorage>

    }
}