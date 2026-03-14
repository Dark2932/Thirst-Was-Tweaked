package com.dark2932.thirst_was_tweaked.content.effect;

import dev.ghen.thirst.foundation.common.capability.ModCapabilities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dark2932
 */
public class HydratoxinEffect extends MobEffect {

    public HydratoxinEffect() {
        super(MobEffectCategory.HARMFUL, 0x5E2791);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
            player.getCapability(ModCapabilities.PLAYER_THIRST).ifPresent((cap) -> {
                if (!player.hasEffect(MobEffects.CONFUSION) || (player.hasEffect(MobEffects.CONFUSION) && player.getEffect(MobEffects.CONFUSION).getDuration() < 10 * 20)) {
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 10 * 20, 0, false, false, false));
                }
            });
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int time = (12 * 20) >> amplifier;
        return (time > 0) ? (duration % time == 0) : true;
    }

}