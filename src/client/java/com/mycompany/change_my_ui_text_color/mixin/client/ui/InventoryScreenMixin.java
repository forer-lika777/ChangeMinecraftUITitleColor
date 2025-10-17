package com.mycompany.change_my_ui_text_color.mixin.client.ui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends HandledScreen<ScreenHandler> {
    public InventoryScreenMixin(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    @Redirect(
            method = "drawForeground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"
            )
    )
    private int redirectCreativeInventoryTitle(
            DrawContext context,
            TextRenderer textRenderer,
            Text originalText,
            int x,
            int y,
            int color,
            boolean shadow
    ) {
        Text customTitle = Text.literal(originalText.getString()).formatted(Formatting.WHITE);
        return context.drawText(textRenderer, customTitle, x, y, color, shadow);
    }

}
