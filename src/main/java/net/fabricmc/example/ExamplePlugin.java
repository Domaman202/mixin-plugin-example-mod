package net.fabricmc.example;

import org.objectweb.asm.Label;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

import static org.objectweb.asm.Opcodes.*;

public class ExamplePlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        if (targetClassName.equals("net.fabricmc.example.ExampleMod")) {
            var node = targetClass.methods.stream().filter(method -> method.name.equals("onInitialize") && method.desc.equals("()V")).findFirst().orElseThrow();
            node.instructions.clear();
            node.visitCode();
            Label label0 = new Label();
            node.visitLabel(label0);
            node.visitLineNumber(10, label0);
            node.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            node.visitLdcInsn("Transformed hi!");
            node.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label label1 = new Label();
            node.visitLabel(label1);
            node.visitLineNumber(11, label1);
            node.visitInsn(RETURN);
            Label label2 = new Label();
            node.visitLabel(label2);
            node.visitLocalVariable("this", "Lnet/fabricmc/example/ExampleMod;", null, label0, label2, 0);
            node.visitMaxs(2, 1);
            node.visitEnd();
        }
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
