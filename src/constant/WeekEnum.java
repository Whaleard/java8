package constant;

/**
 * @author Mr.MC
 */

public enum WeekEnum {
    MON("星期一", 1), TUE("星期二", 2), WED("星期三", 3){
        public boolean isRest() {
            return true;
        }
    };

    private String name;
    private int index;
    private WeekEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getNameByIndex(int index) {
        for (WeekEnum weekEnum : WeekEnum.values()) {
            if (weekEnum.getIndex() == index) {
                return weekEnum.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
