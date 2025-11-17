package net.louis.overhaulmod.component;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.math.Fraction;

@Environment(EnvType.CLIENT)
public class CustomBundleTooltipComponent implements TooltipComponent {
    private static final Identifier BACKGROUND_TEXTURE = Identifier.ofVanilla("container/bundle/background");
    private static final int field_32381 = 4;
    private static final int field_32382 = 1;
    private static final int WIDTH_PER_COLUMN = 18;
    private static final int HEIGHT_PER_ROW = 20;
    private final CustomBundleContentsComponent bundleContents;

    public CustomBundleTooltipComponent(CustomBundleContentsComponent bundleContents) {
        this.bundleContents = bundleContents;
    }

    @Override
    public int getHeight() {
        return this.getRowsHeight() + 4;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return this.getColumnsWidth();
    }

    private int getColumnsWidth() {
        return this.getColumns() * 18 + 2;
    }

    private int getRowsHeight() {
        return this.getRows() * 20 + 2;
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
        int i = this.getColumns();
        int j = this.getRows();
        context.drawGuiTexture(BACKGROUND_TEXTURE, x, y, this.getColumnsWidth(), this.getRowsHeight());
        boolean bl = this.bundleContents.getOccupancy().compareTo(Fraction.ONE) >= 0;
        int k = 0;

        for (int l = 0; l < j; l++) {
            for (int m = 0; m < i; m++) {
                int n = x + m * 18 + 1;
                int o = y + l * 20 + 1;
                this.drawSlot(n, o, k++, bl, context, textRenderer);
            }
        }
    }

    private void drawSlot(int x, int y, int index, boolean shouldBlock, DrawContext context, TextRenderer textRenderer) {
        if (index >= this.bundleContents.size()) {
            this.draw(context, x, y, shouldBlock ? CustomBundleTooltipComponent.SlotSprite.BLOCKED_SLOT : CustomBundleTooltipComponent.SlotSprite.SLOT);
        } else {
            ItemStack itemStack = this.bundleContents.get(index);
            this.draw(context, x, y, CustomBundleTooltipComponent.SlotSprite.SLOT);
            context.drawItem(itemStack, x + 1, y + 1, index);
            context.drawItemInSlot(textRenderer, itemStack, x + 1, y + 1);
            if (index == 0) {
                HandledScreen.drawSlotHighlight(context, x + 1, y + 1, 0);
            }
        }
    }

    private void draw(DrawContext context, int x, int y, CustomBundleTooltipComponent.SlotSprite sprite) {
        context.drawGuiTexture(sprite.texture, x, y, 0, sprite.width, sprite.height);
    }

    private int getColumns() {
        return Math.max(2, (int)Math.ceil(Math.sqrt(this.bundleContents.size() + 1.0)));
    }

    private int getRows() {
        return (int)Math.ceil((this.bundleContents.size() + 1.0) / this.getColumns());
    }

    @Environment(EnvType.CLIENT)
    static enum SlotSprite {
        BLOCKED_SLOT(Identifier.ofVanilla("container/bundle/blocked_slot"), 18, 20),
        SLOT(Identifier.ofVanilla("container/bundle/slot"), 18, 20);

        public final Identifier texture;
        public final int width;
        public final int height;

        private SlotSprite(final Identifier texture, final int width, final int height) {
            this.texture = texture;
            this.width = width;
            this.height = height;
        }
    }
}
