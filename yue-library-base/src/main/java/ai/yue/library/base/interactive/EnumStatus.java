package ai.yue.library.base.interactive;

/**
 * @author liuwenxue
 * @Description: todo
 * @date 2020/6/8 19:35
 */
public enum EnumStatus {
    WAITING("00", "待处理"),
    PROCESSING("10","处理中"),
    SUCCESS("20", "成功"),
    FAIL("30", "失败");

    public String code;

    public String msg;

    private EnumStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
