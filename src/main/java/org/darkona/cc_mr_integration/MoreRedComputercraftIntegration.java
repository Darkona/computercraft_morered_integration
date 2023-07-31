package org.darkona.cc_mr_integration.cc_mr_integration;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod(MoreRedComputercraftIntegration.MODID)
public class MoreRedComputercraftIntegration {
    public static final String MODID = "cc_mr_integration";
    public static MoreRedComputercraftIntegration INSTANCE;

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static RegistryObject<Block> ADAPTER_BLOCK = register("mrcc_adapter",
			() -> new MRCCAdapterBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE)),
			new Item.Properties().tab(CreativeModeTab.TAB_TRANSPORTATION));


    public static final RegistryObject<BlockEntityType<MRCCAdapterBlockEntity>> ADAPTER_ENTITY =
            TILE_ENTITY_TYPES.register("mrcc_adapter",
            () -> BlockEntityType.Builder.of(MRCCAdapterBlockEntity::new, ADAPTER_BLOCK.get()).build(null));

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier, Item.Properties properties) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        return block;
    }

    public MoreRedComputercraftIntegration() {
        INSTANCE = this;

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;



        // subscribe events to busses
        /*modBus.addListener(this::onCommonSetup);*/

        // subscribe client events
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientProxy.subscribeClientEvents(modBus, forgeBus);
        }
    }
	
	/*private void onCommonSetup(FMLCommonSetupEvent event)
	{
		// register adapters as being able to have more red cables connect to them
		Map<Block, WireConnector> cableConnectabilityRegistry = MoreRedAPI.getCableConnectabilityRegistry();
		//MRCCAdapterBlock adapter = this.ADAPTER_BLOCK.get();
		//cableConnectabilityRegistry.put(this.ADAPTER_BLOCK.get(), adapter::canConnectToAdjacentCable);
	}
	*/
	/*public static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> makeDeferredRegister(IEventBus modBus,
																							   IForgeRegistry<T>
																							   registry)
	{
		DeferredRegister<T> register = DeferredRegister.create(registry, MODID);
		register.register(modBus);;
		return register;
	}*/
}
