package com.mycompany.change_my_ui_text_color.mixin.client.ui;


import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(HandledScreens.class)
public class HandledScreensMixin {

    @ModifyVariable(method = "open", at = @At("HEAD"), argsOnly = true)
    private static Text modifyAllTitles(Text title) {
        // 修改所有 GUI 标题的通用逻辑
        return redirectTitleColor(title);
    }

    @Unique
    private static Text redirectTitleColor(Text originalTitle) {
        // 获取原始参数
        return Text.literal(originalTitle.getString()).formatted(Formatting.WHITE);
    }
}