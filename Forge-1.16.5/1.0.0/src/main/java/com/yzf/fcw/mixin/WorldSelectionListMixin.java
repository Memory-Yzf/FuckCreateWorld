package com.yzf.fcw.mixin;

import net.minecraft.client.gui.screen.WorldSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(WorldSelectionList.class)
public class WorldSelectionListMixin {
    @Inject(method = "refreshList", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"
    ), cancellable = true)
    private void cancelOpenCreateWorld(Supplier<String> supplier, boolean reload, CallbackInfo ci) {
        ci.cancel();
    }
}