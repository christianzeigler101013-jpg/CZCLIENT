package com.czclient.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class CzTitleScreen {
    private static ButtonWidget singleplayer,multiplayer,options,quit;
    private CzTitleScreen(){}
    public static void renderVanillaScreenReplacement(DrawContext ctx,int mouseX,int mouseY,float delta){
        MinecraftClient client=MinecraftClient.getInstance(); int w=ctx.getScaledWindowWidth(),h=ctx.getScaledWindowHeight(); ensureButtons(client);
        ctx.fill(0,0,w,h,0xFF080912);
        long t=System.currentTimeMillis()/35L;
        for(int i=-h;i<w+h;i+=56){int shift=(int)(t%56);ctx.fill(i+shift,0,i+shift+18,h,0x121A1030);}
        ctx.fill(0,0,w,3,0xFF8A5CFF);
        int cx=w/2;
        ctx.drawCenteredTextWithShadow(client.textRenderer,Text.literal("CZCLIENT"),cx,58,0xFFBFA7FF);
        ctx.drawCenteredTextWithShadow(client.textRenderer,Text.literal("ALL-IN-ONE MINECRAFT CLIENT"),cx,79,0xFF888899);
        ctx.drawCenteredTextWithShadow(client.textRenderer,Text.literal("1.21.11  •  FABRIC"),cx,96,0xFF6F6F82);
        renderButton(ctx,singleplayer,mouseX,mouseY);renderButton(ctx,multiplayer,mouseX,mouseY);renderButton(ctx,options,mouseX,mouseY);renderButton(ctx,quit,mouseX,mouseY);
        ctx.drawCenteredTextWithShadow(client.textRenderer,Text.literal("Right Shift  •  Open CZCLIENT Menu"),cx,h-28,0xFF77778A);
    }
    private static void ensureButtons(MinecraftClient client){
        int w=client.getWindow().getScaledWidth(),h=client.getWindow().getScaledHeight(),bw=300,bh=38,x=(w-bw)/2,y=122;
        singleplayer=ButtonWidget.builder(Text.literal("SINGLEPLAYER"),b->client.setScreen(new SelectWorldScreen(new TitleScreen()))).dimensions(x,y,bw,bh).build();
        multiplayer=ButtonWidget.builder(Text.literal("MULTIPLAYER"),b->client.setScreen(new MultiplayerScreen(new TitleScreen()))).dimensions(x,y+48,bw,bh).build();
        options=ButtonWidget.builder(Text.literal("OPTIONS"),b->client.setScreen(new OptionsScreen(new TitleScreen(),client.options))).dimensions(x,y+96,bw,bh).build();
        quit=ButtonWidget.builder(Text.literal("QUIT GAME"),b->client.scheduleStop()).dimensions(x,y+144,bw,bh).build();
    }
    public static boolean handleClick(Click click){if(click.button()!=0)return false; ensureButtons(MinecraftClient.getInstance()); return singleplayer.mouseClicked(click,false)||multiplayer.mouseClicked(click,false)||options.mouseClicked(click,false)||quit.mouseClicked(click,false);}
    private static void renderButton(DrawContext ctx,ButtonWidget b,int mx,int my){b.render(ctx,mx,my,0);}
}
