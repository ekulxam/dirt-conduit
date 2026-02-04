package survivalblock.dirtconduit.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//@Debug(export = true)
@Mixin(ConduitBlockEntity.class)
public class ConduitBlockEntityMixin {

    @ModifyExpressionValue(method = "updateActivatingBlocks", at = @At(value = "FIELD", target = "Lnet/minecraft/block/entity/ConduitBlockEntity;ACTIVATING_BLOCKS:[Lnet/minecraft/block/Block;"))
    private Block[] onlyOne(Block[] original) {
        return new Block[]{original[0]};
    }

    @Definition(id = "getBlock", method = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;")
    @Definition(id = "block", local = @Local(type = Block.class))
    @Expression("?.getBlock() == block")
    @WrapOperation(method = "updateActivatingBlocks", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean alwaysConfirm(Object left, Object right, Operation<Boolean> original) {
        return true;
    }
}