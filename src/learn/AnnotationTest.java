package learn;

import annotation.MyAnnotation;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author Mr MC
 */
public class AnnotationTest {

    @Test
    public void test01() throws Exception {
        Class<AnnotationTest> clazz = AnnotationTest.class;
        Method method = clazz.getMethod("show");

        MyAnnotation[] myAnnotations = method.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : myAnnotations) {
            System.out.println(myAnnotation.value());
        }
    }

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show(@MyAnnotation("abc") String str) {

    }
}
