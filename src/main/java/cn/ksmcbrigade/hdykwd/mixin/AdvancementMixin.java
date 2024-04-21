package cn.ksmcbrigade.hdykwd.mixin;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(Advancement.class)
public class AdvancementMixin {
    @Mutable
    @Shadow @Final private boolean sendsTelemetryEvent;

    @Inject(method = "<init>",at = @At("TAIL"))
    public void init(ResourceLocation p_286878_, Advancement p_286496_, DisplayInfo p_286499_, AdvancementRewards p_286389_, Map<String, Criterion> p_286635_, String[][] p_286882_, boolean p_286478_, CallbackInfo ci){
        this.sendsTelemetryEvent = true;
    }

    @Inject(method = "sendsTelemetryEvent",at = @At("RETURN"), cancellable = true)
    public void get(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }
}
