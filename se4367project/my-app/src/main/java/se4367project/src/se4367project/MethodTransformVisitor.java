package se4367project;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {

    String mName;
    public int line;

    @Override
    public void visitLabel(Label label) {
        if(line != 0) {
            mv.visitLdcInsn(mName + ":" + line + "\n");
            //mv.visitLdcInsn(line);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            mv.visitMethodInsn(INVOKESTATIC, "se4367project/CollectCoverage", "lineExecuted", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        }
        super.visitLabel(label);
    }
    
    @Override
    public void visitLineNumber(int line, Label start) {
        if(line != 0) {
            this.line = line;
            mv.visitLdcInsn(mName + ":" + line + "\n");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            mv.visitMethodInsn(INVOKESTATIC, "se4367project/CollectCoverage", "lineExecuted", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        }
        super.visitLineNumber(line, start);
    }
    
    public MethodTransformVisitor(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        this.mName = name;
    }
}