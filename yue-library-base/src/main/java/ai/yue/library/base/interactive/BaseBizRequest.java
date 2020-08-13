package ai.yue.library.base.interactive;

import java.io.Serializable;
import java.util.Map;

public class BaseBizRequest implements Serializable {
    /**
     * 任务编号
     */
    String missionNo;
    /**
     * 业务流水号
     */
    String bizNo;
    /**
     * 保留字段
     */
    Map reserve;

    public Map getReserve() {
        return reserve;
    }

    public void setReserve(Map reserve) {
        this.reserve = reserve;
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
