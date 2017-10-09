package cn.shoppingmall.bean;

import java.util.List;

/**
 * Created by ${易淼} on 2017/10/9.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class CityBean {

    /**
     * success : true
     * msg : 返回成功
     * data : [{"CityId":0,"CityName":"==请选择==","ProId":0},{"CityId":2,"CityName":"天津市","ProId":2}]
     * errcode :
     */

    private String success;
    private String msg;
    private String errcode;
    private List<DataEntity> data;

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public String getErrcode() {
        return errcode;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * CityId : 0
         * CityName : ==请选择==
         * ProId : 0
         */

        private String CityId;
        private String CityName;
        private String ProId;

        public void setCityId(String CityId) {
            this.CityId = CityId;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public void setProId(String ProId) {
            this.ProId = ProId;
        }

        public String getCityId() {
            return CityId;
        }

        public String getCityName() {
            return CityName;
        }

        public String getProId() {
            return ProId;
        }

        @Override
        public String toString() {
            return getCityName();
        }
    }
}
