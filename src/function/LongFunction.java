package function;

/**
 * @author Mr.MC
 */
public interface LongFunction<T, R> {

    /**
     * 获取两个入参运算后的值
     * @param l1
     * @param l2
     * @return
     */
    R getValue(T l1, T l2);
}
