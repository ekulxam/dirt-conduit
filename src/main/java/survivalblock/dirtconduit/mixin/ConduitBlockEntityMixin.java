package survivalblock.dirtconduit.mixin;

import com.google.common.collect.Iterables;
import net.minecraft.block.Block;
import net.minecraft.block.entity.ConduitBlockEntity;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ConduitBlockEntity.class)
public class ConduitBlockEntityMixin {
	@Shadow @Final @Mutable
	private static Block[] ACTIVATING_BLOCKS;

	static {
        ACTIVATING_BLOCKS = Iterables.toArray(Registry.BLOCK, Block.class);
	}
}