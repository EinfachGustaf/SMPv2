package live.einfachgustaf.mods.smp.mixins;

import live.einfachgustaf.mods.smp.event.PlayerEatEvent;
import me.obsilabor.alert.EventManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodProperties.class)
public class MixinFoodProperties {

    @Inject(at = @At("HEAD"), method = "onConsume")
    public void injectOnConsume(Level level, LivingEntity livingEntity, ItemStack itemStack, Consumable consumable, CallbackInfo ci) {
        if (livingEntity instanceof Player player) {
            EventManager.callEvent(new PlayerEatEvent(player, level, itemStack, (FoodProperties) (Object) this));
        }
    }
}
