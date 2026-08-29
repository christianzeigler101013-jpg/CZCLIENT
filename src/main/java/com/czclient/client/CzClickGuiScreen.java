package com.czclient.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class CzClickGuiScreen extends Screen {
    public CzClickGuiScreen(){super(Text.literal("CZCLIENT"));}
    @Override protected void init(){
        int w=460,h=350,x=(width-w)/2,y=(height-h)/2,bw=200,bh=34;
        add("Keystrokes",CZClient.showKeystrokes,x+20,y+65,bw,bh,v->CZClient.showKeystrokes=v);
        add("FPS Counter",CZClient.showFps,x+240,y+65,bw,bh,v->CZClient.showFps=v);
        add("CPS Counter",CZClient.showCps,x+20,y+109,bw,bh,v->CZClient.showCps=v);
        add("Coordinates",CZClient.showCoords,x+240,y+109,bw,bh,v->CZClient.showCoords=v);
        add("Armor HUD",CZClient.showArmor,x+20,y+153,bw,bh,v->CZClient.showArmor=v);
        add("Fullbright",CZClient.fullbright,x+240,y+153,bw,bh,v->CZClient.fullbright=v);
        add("Toggle Sprint",CZClient.sprintToggle,x+20,y+197,bw,bh,v->CZClient.sprintToggle=v);
        add("Zoom",CZClient.zoom,x+240,y+197,bw,bh,v->CZClient.zoom=v);
        addDrawableChild(ButtonWidget.builder(Text.literal("CLOSE"),b->close()).dimensions(x+110,y+h-48,240,34).build());
    }
    private interface Setter{void set(boolean v);}
    private void add(String name,boolean enabled,int x,int y,int w,int h,Setter s){
        ButtonWidget b=ButtonWidget.builder(label(name,enabled),btn->{boolean n=!enabled; s.set(n); btn.setMessage(label(name,n));}).dimensions(x,y,w,h).build();
        addDrawableChild(b);
    }
    private static Text label(String n,boolean e){return Text.literal(n+"  "+(e?"ON":"OFF"));}
    private static void border(DrawContext c,int x,int y,int w,int h,int color){
        c.fill(x,y,x+w,y+1,color); c.fill(x,y+h-1,x+w,y+h,color);
        c.fill(x,y,x+1,y+h,color); c.fill(x+w-1,y,x+w,y+h,color);
    }
    @Override public void render(DrawContext c,int mx,int my,float d){
        c.fill(0,0,width,height,0xD9080810); int w=460,h=350,x=(width-w)/2,y=(height-h)/2;
        c.fill(x,y,x+w,y+h,0xFF11111B); border(c,x,y,w,h,0xFF8A5CFF);
        c.drawTextWithShadow(textRenderer,"CZCLIENT",x+22,y+18,0xFFBFA7FF);
        c.drawTextWithShadow(textRenderer,"CLIENT MENU  •  RIGHT SHIFT",x+22,y+38,0xFF888899);
        super.render(c,mx,my,d);
    }
    @Override public boolean shouldPause(){return false;}
}
