package ai.yue.library.base.interactive;

public enum EnumSubStatus {
    //默认值300
    FAIL300("300", "业务拒绝，不可重试"),
    //第三方正常处理完成，结果为拒绝，但明确告知我方可重试
    FAIL301("301", "第三方处理失败，可重试"),
    //第三方网络异常，我方置为拒绝，但需告知客户方可重试
    FAIL302("302", "第三方网络异常，可重试"),
    //第三方系统异常，不可重试
    FAIL391("391", "第三方系统异常，不可重试"),
    //第三方系统异常，不可重试
    FAIL392("392", "系统内部异常，不可重试");

    public String code;

    public String msg;

    private EnumSubStatus(String code, String msg) {
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
