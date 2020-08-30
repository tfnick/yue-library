package ai.yue.library.base.interactive;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 适用场景：设计跨系统级别的复杂异步交互API时
 * @param <T>
 */
public class RequestDto<T extends BaseBizRequest> {
    @NotEmpty
    private String requestId;
    @NotEmpty
    private String serviceName;

    private String serviceVersion;

    private String charset;

    private String signType;

    private String sign;
    @NotEmpty
    private String clientCode;

    private String notifyUrl;
    @Valid
    @NotNull
    private T bizContent;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public T getBizContent() {
        return bizContent;
    }

    public void setBizContent(T bizContent) {
        this.bizContent = bizContent;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
