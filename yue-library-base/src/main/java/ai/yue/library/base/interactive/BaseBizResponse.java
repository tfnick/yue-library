package ai.yue.library.base.interactive;

public class BaseBizResponse {
    /**
     * 业务处理过程中的错误编码
     */
    String errorCode;
    /**
     * 业务处理过程中的错误信息
     */
    String errorMessage;

    /**
     * 任务编号,无业务含义
     */
    String missionNo;

    /**
     * 业务流水号
     */
    String bizNo;

    /**
     * 任务状态
     */
    String status;

    /**
     * 子状态
     */
    String subStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMissionNo() {
        return missionNo;
    }

    public void setMissionNo(String missionNo) {
        this.missionNo = missionNo;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }
}
