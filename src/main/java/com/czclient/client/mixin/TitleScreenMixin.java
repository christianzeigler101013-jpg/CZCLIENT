package com.czclient.client.mixin;

import com.czclient.client.CzTitleScreen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin {
    @Inject(method="render",at=@At("HEAD"),cancellable=true)
    private void czclient$render(DrawContext ctx,int mouseX,int mouseY,float delta,CallbackInfo ci){ CzTitleScreen.renderVanillaScreenReplacement(ctx,mouseX,mouseY,delta); ci.cancel(); }
    @Inject(method="mouseClicked",at=@At("HEAD"),cancellable=true)
    private void czclient$mouseClicked(Click click,boolean doubled,CallbackInfoReturnable<Boolean> cir){ if(CzTitleScreen.handleClick(click)) cir.setReturnValue(true); }
}
