package ai.yue.library.base.interactive;

public enum EnumRequestStatus {
    SUCCESS("S", "请求成功"),
    FAIL("F","请求失败");

    public String code;

    public String msg;

    private EnumRequestStatus(String code, String msg) {
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
