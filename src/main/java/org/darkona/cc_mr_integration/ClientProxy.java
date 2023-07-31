package org.darkona.cc_mr_integration.cc_mr_integration;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy
{
	public static void subscribeClientEvents(IEventBus modBus, IEventBus forgeBus)
	{
		modBus.addListener(ClientProxy::onClientSetup);
	}
	
	private static void onClientSetup(FMLClientSetupEvent event)
	{
		EntityRenderersEvent.RegisterRenderers registerRenderers =  new EntityRenderersEvent.RegisterRenderers();
		//registerRenderers.registerBlockEntityRenderer(MoreRedComputercraftIntegration.INSTANCE.ADAPTER_BLOCK);
		//RenderTypeLookup.setRenderLayer(.get(), RenderType.cutout());
	}
}
