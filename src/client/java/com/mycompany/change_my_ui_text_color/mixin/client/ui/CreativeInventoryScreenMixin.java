package com.mycompany.change_my_ui_text_color.mixin.client.ui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin extends HandledScreen<CreativeInventoryScreen.CreativeScreenHandler> {
    public CreativeInventoryScreenMixin(CreativeInventoryScreen.CreativeScreenHandler handler, PlayerInventory inventory, Text title) {
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
        // 检查是否是绘制标题（而不是其他文本）
        if (y == this.titleY && x == this.titleX) {
            // 修改创造模式物品栏标题
            Text customTitle = Text.literal(originalText.getString()).formatted(Formatting.WHITE);
            return context.drawText(textRenderer, customTitle, x, y, color, shadow);
        }

        // 对于其他文本，使用原始行为
        return context.drawText(textRenderer, originalText, x, y, Colors.RED, shadow);
    }
}
