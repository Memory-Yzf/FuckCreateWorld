package com.yzf.fcw.mixin;

import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import net.minecraft.world.level.storage.LevelSummary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mixin(WorldSelectionList.class)
public class WorldSelectionListMixin {
    @Inject(
            method = "loadLevels",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/worldselection/CreateWorldScreen;openFresh(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/screens/Screen;)V"
            ),
            cancellable = true
    )
    private void cancelOpenFresh(CallbackInfoReturnable<CompletableFuture<List<LevelSummary>>> cir) {
        cir.setReturnValue(CompletableFuture.completedFuture(List.of()));
    }
}