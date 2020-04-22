package javase.enums;

/**
 * @author: CHNjerry
 * @description:
 * enum 相当于一种小型数据库
 * @date: 2020/03/26 18:59
 */
public enum CountryEnum {
    /**
     * 1 齐国
     * 2 楚国.....
     */
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");


    private Integer retCode;
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public static CountryEnum forEachCountryEnum(int index){
        CountryEnum[] myArrays= CountryEnum.values();
        for (CountryEnum element : myArrays) {
            if (index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return
                "retCode=" + retCode +
                ", retMessage='" + retMessage + '\'';
    }
}
