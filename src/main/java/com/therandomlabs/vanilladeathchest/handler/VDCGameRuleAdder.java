package com.therandomlabs.vanilladeathchest.handler;

import com.therandomlabs.vanilladeathchest.VDCConfig;
import com.therandomlabs.vanilladeathchest.VanillaDeathChest;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = VanillaDeathChest.MOD_ID)
public final class VDCGameRuleAdder {
	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event) {
		if(VDCConfig.misc.gameRuleName.isEmpty()) {
			return;
		}

		final World world = event.getWorld();

		if(world.isRemote) {
			return;
		}

		final GameRules gameRules = world.getGameRules();

		if(!gameRules.hasRule(VDCConfig.misc.gameRuleName)) {
			gameRules.setOrCreateGameRule(
					VDCConfig.misc.gameRuleName,
					Boolean.toString(VDCConfig.misc.gameRuleDefaultValue)
			);
		}
	}
}