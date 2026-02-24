package survivalblock.dirtconduit.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import survivalblock.dirtconduit.Blockdoor;

@Pseudo
@Mixin(targets = {"net/minecraft/class_2248", "net/minecraft/world/level/block/Block"})
public class BlockMixin implements Blockdoor {
}
