package com.yzf.fcw.mixin;

import net.minecraft.client.gui.screen.world.WorldListWidget;
import net.minecraft.world.level.storage.LevelSummary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mixin(WorldListWidget.class)
public class WorldListWidgetMixin {
	@Inject(
			method = "loadLevels",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/screen/world/CreateWorldScreen;create(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/gui/screen/Screen;)V"
			),
			cancellable = true
	)
	private void cancelCreateWorldScreen(CallbackInfoReturnable<CompletableFuture<List<LevelSummary>>> cir) {
		cir.setReturnValue(CompletableFuture.completedFuture(List.of()));
	}
}