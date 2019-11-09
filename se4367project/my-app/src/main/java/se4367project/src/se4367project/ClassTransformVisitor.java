package se4367project;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassWriter;


public class ClassTransformVisitor extends ClassVisitor implements Opcodes {

    protected String className;

    public ClassTransformVisitor(ClassWriter writer) {
        super(Opcodes.ASM5, writer);
    }
    
    @Override
    public void visit(int ver, int access, String name, String sig, String supName, String[] interfaces) {
        this.className = name;
        super.visit(ver, access, name, sig, supName, interfaces);
    }
    
    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        return methodVisitor == null ? null : new MethodTransformVisitor(methodVisitor, className);
    }
}