package function;

/**
 * @author Mr.MC
 */
public interface LongFunction<T, R> {

    /**
     * ��ȡ�������������ֵ
     * @param l1
     * @param l2
     * @return
     */
    R getValue(T l1, T l2);
}
