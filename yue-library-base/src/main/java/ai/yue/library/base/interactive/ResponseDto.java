package ai.yue.library.base.interactive;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseDto<T extends BaseBizResponse> {
    //请求级状态
    private String resCode;
    //请求级提示信息
    private String resMsg;
    //响应时间yyyy-MM-dd HH:mm:ss
    private String resTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"));
    //业务返回
    private T resContent;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getResTime() {
        return resTime;
    }

    public void setResTime(String resTime) {
        this.resTime = resTime;
    }

    public T getResContent() {
        return resContent;
    }

    public ResponseDto setResContent(T resContent) {
        this.resContent = resContent;
        return this;
    }

    public static <T extends BaseBizResponse> ResponseDto<T> success(){
        ResponseDto response = new ResponseDto();
        response.setResCode(EnumStatus.SUCCESS.getCode());
        response.setResMsg(EnumStatus.SUCCESS.getMsg());
        return  response;
    }

    public static <T extends BaseBizResponse> ResponseDto<T> success(T bizContent){
        ResponseDto response = new ResponseDto();
        response.setResCode(EnumStatus.SUCCESS.getCode());
        response.setResMsg(EnumStatus.SUCCESS.getMsg());
        response.setResContent(bizContent);
        return  response;
    }

    public static <T extends BaseBizResponse> ResponseDto<T> failed(String msg){
        ResponseDto response = new ResponseDto();
        response.setResCode(EnumStatus.FAIL.getCode());
        response.setResMsg(msg);
        return  response;
    }

    public static <T extends BaseBizResponse> ResponseDto<T> failed(){
        ResponseDto response = new ResponseDto();
        response.setResCode(EnumStatus.FAIL.getCode());
        response.setResMsg(EnumStatus.FAIL.getMsg());
        return  response;
    }

}
