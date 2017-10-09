package cn.shoppingmall.bean;

import java.util.List;

/**
 * Created by ${易淼} on 2017/10/9.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class CountyBean {

    /**
     * success : true
     * msg : 返回成功
     * data : [{"CountyId":0,"CountyName":"==请选择==","CityId":0},{"CountyId":19,"CountyName":"和平区","CityId":2},{"CountyId":20,"CountyName":"河东区","CityId":2},{"CountyId":21,"CountyName":"河西区","CityId":2},{"CountyId":22,"CountyName":"南开区","CityId":2},{"CountyId":23,"CountyName":"河北区","CityId":2},{"CountyId":24,"CountyName":"红桥区","CityId":2},{"CountyId":25,"CountyName":"塘沽区","CityId":2},{"CountyId":26,"CountyName":"汉沽区","CityId":2},{"CountyId":27,"CountyName":"大港区","CityId":2},{"CountyId":28,"CountyName":"东丽区","CityId":2},{"CountyId":29,"CountyName":"西青区","CityId":2},{"CountyId":30,"CountyName":"津南区","CityId":2},{"CountyId":31,"CountyName":"北辰区","CityId":2},{"CountyId":32,"CountyName":"武清区","CityId":2},{"CountyId":33,"CountyName":"宝坻区","CityId":2},{"CountyId":34,"CountyName":"宁河县","CityId":2},{"CountyId":35,"CountyName":"静海县","CityId":2},{"CountyId":36,"CountyName":"蓟县","CityId":2}]
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
         * CountyId : 0
         * CountyName : ==请选择==
         * CityId : 0
         */

        private String CountyId;
        private String CountyName;
        private String CityId;

        public void setCountyId(String CountyId) {
            this.CountyId = CountyId;
        }

        public void setCountyName(String CountyName) {
            this.CountyName = CountyName;
        }

        public void setCityId(String CityId) {
            this.CityId = CityId;
        }

        public String getCountyId() {
            return CountyId;
        }

        public String getCountyName() {
            return CountyName;
        }

        public String getCityId() {
            return CityId;
        }

        @Override
        public String toString() {
            return getCountyName();
        }
    }
}
