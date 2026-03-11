package com.dark2932.thirst_was_tweaked.mixin.thirst;

import com.dark2932.thirst_was_tweaked.content.config.ThirstTweakConfig;
import com.dark2932.thirst_was_tweaked.content.registry.ThirstTweakEffects;
import dev.ghen.thirst.content.purity.WaterPurity;
import dev.ghen.thirst.foundation.config.CommonConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

/**
 * @author Dark2932
 */
@Mixin(value = WaterPurity.class)
public class MixinWaterPurity {

    /**
     * @author Dark2932
     * @reason 原方法过于粗糙，且不符合大众逻辑
     * 你可以在补水操作的条件判断式中调用该方法，以此来使玩家中水毒后不补充水量。
     * 若仅想给予玩家对应效果且正常补充水量，请将quenchThirstWhenDebuffed为true，或将此方法移出条件判断式。
     * quenchThirstWhenDebuffed为true时，无论此方法是否在条件判断式中，玩家补水都不受水毒效果影响。
     */
    @Overwrite(remap = false)
    public static boolean givePurityEffects(Player entity, int purity) {

        //此变量与config/thirst/common.toml中的quenchThirstWhenDebuffed选项配合生效。
        //当quenchThirstWhenDebuffed为true时（即默认值），该变量不起作用，玩家补水不受药水效果影响。
        //当quenchThirstWhenDebuffed为false时，该变量生效，玩家中水毒效果后不会恢复相应的水量。
        boolean shouldDrink = true;

        //根据摄入水源的纯净度获取配置文件中对应的中水毒的概率
        float chance = switch (purity) {
            case 0 -> ThirstTweakConfig.DIRTY_HYDROTOXIN_CHANCE.get();
            case 1 -> ThirstTweakConfig.SLIGHTLY_DIRTY_HYDROTOXIN_CHANCE.get();
            case 2 -> ThirstTweakConfig.ACCEPTABLE_HYDROTOXIN_CHANCE.get();
            default -> 0;
        };

        if (entity instanceof ServerPlayer player && (new Random().nextFloat()) < (chance / 100.0F)) {
            player.addEffect(new MobEffectInstance(ThirstTweakEffects.HYDROTOXIN.get(), 60 * 20, 0, false, true, true));
            shouldDrink = false;
        }

        return shouldDrink || CommonConfig.QUENCH_THIRST_WHEN_DEBUFFED.get();
    }

}
