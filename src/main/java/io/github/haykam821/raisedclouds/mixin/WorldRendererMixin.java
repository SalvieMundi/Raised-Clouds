package io.github.haykam821.raisedclouds.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.haykam821.raisedclouds.ClientMain;
import io.github.haykam821.raisedclouds.config.RaisedCloudsConfig;
import net.minecraft.client.render.SkyProperties;

@Mixin(SkyProperties.class)
public class WorldRendererMixin {
	@Unique
	private RaisedCloudsConfig CONFIG = ClientMain.getConfig();
	
	@Shadow @Final
	private float cloudsHeight;
	
	@Shadow @Final
	private SkyProperties.SkyType skyType;

	@Inject(method = "getCloudsHeight", at = @At(value = "HEAD"), cancellable = true)
	private void getCloudY(CallbackInfoReturnable<Float> cir) {
		cir.setReturnValue(CONFIG.overrideBaseY && skyType == SkyProperties.SkyType.NORMAL ? CONFIG.baseY : cloudsHeight);
	}
}