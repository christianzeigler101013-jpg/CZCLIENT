package com.czclient.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public final class CzHudRenderer {
    private CzHudRenderer() {}
    public static void render(DrawContext ctx, MinecraftClient client) {
        int x = 10, y = 10;
        if (CZClient.showFps) { box(ctx,x,y,115,24); text(ctx,client,"FPS  "+client.getCurrentFps(),x+8,y+7,0xFFFFFFFF); y+=29; }
        if (CZClient.showCps) { box(ctx,x,y,115,40); text(ctx,client,"CPS",x+8,y+5,0xFFBFA7FF); text(ctx,client,"L "+CZClient.leftCps+"   R "+CZClient.rightCps,x+8,y+21,0xFFFFFFFF); y+=45; }
        if (CZClient.showCoords && client.player != null) { box(ctx,x,y,180,40); text(ctx,client,"XYZ",x+8,y+5,0xFFBFA7FF); text(ctx,client,String.format("%.1f  %.1f  %.1f",client.player.getX(),client.player.getY(),client.player.getZ()),x+8,y+21,0xFFFFFFFF); }
        if (CZClient.showKeystrokes) {
            int bx=ctx.getScaledWindowWidth()-118, by=ctx.getScaledWindowHeight()-112;
            key(ctx,client,"W",bx+38,by,36,30,client.options.forwardKey.isPressed());
            key(ctx,client,"A",bx,by+34,36,30,client.options.leftKey.isPressed());
            key(ctx,client,"S",bx+38,by+34,36,30,client.options.backKey.isPressed());
            key(ctx,client,"D",bx+76,by+34,36,30,client.options.rightKey.isPressed());
            key(ctx,client,"SPACE",bx+38,by+68,74,30,client.options.jumpKey.isPressed());
        }
    }
    private static void border(DrawContext c,int x,int y,int w,int h,int color){
        c.fill(x,y,x+w,y+1,color); c.fill(x,y+h-1,x+w,y+h,color);
        c.fill(x,y,x+1,y+h,color); c.fill(x+w-1,y,x+w,y+h,color);
    }
    private static void key(DrawContext c,MinecraftClient m,String s,int x,int y,int w,int h,boolean p){ c.fill(x,y,x+w,y+h,p?0xFF8A5CFF:0xB81A1A22); border(c,x,y,w,h,0xFFBFA7FF); text(c,m,s,x+(w-m.textRenderer.getWidth(s))/2,y+10,0xFFFFFFFF); }
    private static void box(DrawContext c,int x,int y,int w,int h){c.fill(x,y,x+w,y+h,0xB8101018);border(c,x,y,w,h,0xFF8A5CFF);}
    private static void text(DrawContext c,MinecraftClient m,String s,int x,int y,int color){c.drawTextWithShadow(m.textRenderer,s,x,y,color);}
}
