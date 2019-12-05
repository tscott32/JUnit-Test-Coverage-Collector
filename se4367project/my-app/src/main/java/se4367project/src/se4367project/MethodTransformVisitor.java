package se4367project;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
//import org.objectweb.asm.tree.MethodNode;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	protected int line;
	protected String cName;

	public MethodTransformVisitor(final MethodVisitor mv, String className) {

		super(ASM5, mv);
		this.cName = className;
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		 this.line = line;
		 if (0 != line) {
			  mv.visitLdcInsn(cName);
			  mv.visitLdcInsn(line);
			  mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
			  mv.visitMethodInsn(INVOKESTATIC, "se4367project/CollectCoverage", "visitLine",
			  "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
			  super.visitLineNumber(line, start);
		  }
	}
	 @Override
	 public void visitEnd(){
		super.visitEnd();
	 }
}
