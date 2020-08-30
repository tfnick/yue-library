package ai.yue.library.data.mp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;

public class BaseVersionEntity extends BaseEntity{
    @TableField(value = "version",fill = FieldFill.INSERT)
    @Version
    protected Integer version;
    
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
