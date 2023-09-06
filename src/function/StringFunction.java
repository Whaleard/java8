package function;

/**
 * @author Mr.MC
 */
@FunctionalInterface
public interface StringFunction {

    /**
     * 将传入字符串转换为大写，再将第2和第4个索引位置进行截取子串
     *
     * @param str
     * @return
     */
    String getValue(String str);
}
