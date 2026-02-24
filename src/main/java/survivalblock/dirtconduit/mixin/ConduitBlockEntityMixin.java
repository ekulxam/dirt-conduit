package survivalblock.dirtconduit.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Group;
import survivalblock.dirtconduit.Blockdoor;

import java.lang.reflect.Array;
import java.util.Objects;

//@Debug(export = true)
@Pseudo
@Mixin(targets = {"net/minecraft/class_2597", "net/minecraft/world/level/block/entity/ConduitBlockEntity"})
public class ConduitBlockEntityMixin {

    // BEGIN ----- LENGTH ONE ARRAY -----
    @Group(name = "lengthOneArray", min = 1, max = 1)
    @Coerce
    @ModifyExpressionValue(method = "method_11069", at = @At(value = "FIELD", target = "Lnet/minecraft/class_2597;field_11931:[Lnet/minecraft/class_2248;", opcode = Opcodes.GETSTATIC), remap = false)
    private static Blockdoor[] onlyOneObf(@Coerce Blockdoor[] original) {
        return dirt_conduit$onlyOne(original);
    }

    @Group(name = "lengthOneArray")
    @Coerce
    @ModifyExpressionValue(method = "updateShape", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/entity/ConduitBlockEntity;VALID_BLOCKS:[Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.GETSTATIC), remap = false)
    private static Blockdoor[] onlyOneDeobf(@Coerce Blockdoor[] original) {
        return dirt_conduit$onlyOne(original);
    }

    @Unique
    private static Blockdoor[] dirt_conduit$onlyOne(Blockdoor[] original) {
        Blockdoor[] modified = (Blockdoor[]) Array.newInstance(Objects.requireNonNull(original).getClass().getComponentType(), 1);
        modified[0] = original[0];
        return modified;
    }
    // END ----- LENGTH ONE ARRAY -----

    // BEGIN ----- FORCE CONFIRM -----
    @Group(name = "forceConfirm", min = 1, max = 1)
    @Definition(id = "getBlock", method = "Lnet/minecraft/class_2680;method_11614()Lnet/minecraft/class_2248;", remap = false)
    @Expression("?.getBlock() == ?")
    @WrapOperation(method = "method_11069", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static boolean alwaysConfirmEquals(Object left, Object right, Operation<Boolean> original) {
        return true;
    }

    @Group(name = "forceConfirm")
    @ModifyExpressionValue(method = "method_11069", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2680;method_27852(Lnet/minecraft/class_2248;)Z"), remap = false)
    private static boolean alwaysConfirmIsOf(boolean original) {
        return true;
    }

    @Group(name = "forceConfirm")
    @ModifyExpressionValue(method = "updateShape", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z"), remap = false)
    private static boolean alwaysConfirmDeobf(boolean original) {
        return true;
    }
    // END ----- FORCE CONFIRM -----
}