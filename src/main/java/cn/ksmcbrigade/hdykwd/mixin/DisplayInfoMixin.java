package cn.ksmcbrigade.hdykwd.mixin;

import com.google.gson.JsonObject;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(DisplayInfo.class)
public class DisplayInfoMixin {
    @Mutable
    @Shadow @Final private boolean hidden;

    @Mutable
    @Shadow @Final private boolean announceChat;

    @Mutable
    @Shadow @Final private boolean showToast;

    @Inject(method = "isHidden",at = @At("RETURN"), cancellable = true)
    public void ret(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }

    @Inject(method = "shouldAnnounceChat",at = @At("RETURN"), cancellable = true)
    public void get(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
    }

    @Inject(method = "shouldShowToast",at = @At("RETURN"), cancellable = true)
    public void tost(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
    }

    @Inject(method = "<init>",at = @At("TAIL"))
    public void ret(ItemStack p_14969_, Component p_14970_, Component p_14971_, ResourceLocation p_14972_, FrameType p_14973_, boolean p_14974_, boolean p_14975_, boolean p_14976_, CallbackInfo ci){
        this.hidden = true;
        this.announceChat = false;
        this.showToast = false;
    }

    @Inject(method = "fromJson",at = @At(value = "RETURN"),locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void jsonFrom(JsonObject p_14982_, CallbackInfoReturnable<DisplayInfo> cir, Component component, Component component1, ItemStack itemstack, ResourceLocation resourcelocation, FrameType frametype, boolean flag, boolean flag1, boolean flag2){
        cir.setReturnValue(new DisplayInfo(itemstack,component,component1,resourcelocation,frametype,false,false,true));
    }

    @Inject(method = "fromNetwork",at = @At(value = "RETURN"),locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void NetworkFrom(FriendlyByteBuf p_14989_, CallbackInfoReturnable<DisplayInfo> cir, Component component, Component component1, ItemStack itemstack, FrameType frametype, int i, ResourceLocation resourcelocation, boolean flag, boolean flag1, DisplayInfo displayinfo){
        cir.setReturnValue(new DisplayInfo(itemstack,component,component1,resourcelocation,frametype,false,false,true));
    }
}
