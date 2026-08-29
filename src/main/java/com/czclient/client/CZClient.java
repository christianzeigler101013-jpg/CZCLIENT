package com.czclient.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class CZClient implements ClientModInitializer {
    public static final String MOD_ID = "czclient";
    public static final KeyBinding OPEN_GUI = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.czclient.open_gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, KeyBinding.Category.MISC));
    public static boolean showFps = true, showCps = true, showCoords = true, showKeystrokes = true, showArmor = true;
    public static boolean fullbright = false, zoom = false, sprintToggle = false;
    public static int leftCps, rightCps;
    private static int leftClicks, rightClicks;
    private static long clickWindowStart = System.currentTimeMillis();

    @Override public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_GUI.wasPressed() && client.currentScreen == null) client.setScreen(new CzClickGuiScreen());
            long now = System.currentTimeMillis();
            if (now - clickWindowStart >= 1000) {
                leftCps = leftClicks; rightCps = rightClicks; leftClicks = rightClicks = 0; clickWindowStart = now;
            }
            if (sprintToggle && client.player != null && client.options.sprintKey.isPressed()) client.player.setSprinting(true);
        });
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null && !client.options.hudHidden) CzHudRenderer.render(drawContext, client);
        });
    }
    public static void registerLeftClick() { leftClicks++; }
    public static void registerRightClick() { rightClicks++; }
}
