package net.wides.wpcworld.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;

public class ModEffects {

    public static final StatusEffect OVERLOAD = registerStatusEffect("overload",
            new OverloadEffect(StatusEffectCategory.NEUTRAL, 0x8A2BE2));

    public static final StatusEffect RECHARGE = registerStatusEffect("recharge",
            new RechargeEffect(StatusEffectCategory.BENEFICIAL, 0x41D4CC));

    public static final StatusEffect SEA_SICKNESS = registerStatusEffect("sea_sickness",
            new SeaSicknessEffect(StatusEffectCategory.BENEFICIAL, 0x1E90FF));

    private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(WPCWorld.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        WPCWorld.LOGGER.info("Registering Mod Effects for " + WPCWorld.MOD_ID);
    }
}
