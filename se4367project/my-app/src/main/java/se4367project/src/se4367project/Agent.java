package se4367project;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Agent {
	public static void premain(String agentArgs, Instrumentation inst) {
		inst.addTransformer(new ClassFileTransformer(){
	
		@Override
		public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {

			if (s.startsWith("org/fuin/utils4j") || s.startsWith("org/joda/time") || s.startsWith("project") || s.startsWith("org/apache/commons/dbutils")){
				ClassReader reader = new ClassReader(bytes);
				ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
				ClassTransformVisitor classVisitor = new ClassTransformVisitor(writer);
				reader.accept(classVisitor, 0);
				return writer.toByteArray();
				}
			return new byte[0];
			}
		});
	}
}