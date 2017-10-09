package cn.shoppingmall.bean;

import java.util.List;

/**
 * Created by ${易淼} on 2017/10/9.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class Provincebean {

    /**
     * success : true
     * msg : 返回成功
     * data : [{"ProId":0,"ProName":"==请选择=="},{"ProId":1,"ProName":"北京市"},{"ProId":2,"ProName":"天津市"},{"ProId":3,"ProName":"河北省"},{"ProId":4,"ProName":"山西省"},{"ProId":5,"ProName":"内蒙古自治区"},{"ProId":6,"ProName":"辽宁省"},{"ProId":7,"ProName":"吉林省"},{"ProId":8,"ProName":"黑龙江省"},{"ProId":9,"ProName":"上海市"},{"ProId":10,"ProName":"江苏省"},{"ProId":11,"ProName":"浙江省"},{"ProId":12,"ProName":"安徽省"},{"ProId":13,"ProName":"福建省"},{"ProId":14,"ProName":"江西省"},{"ProId":15,"ProName":"山东省"},{"ProId":16,"ProName":"河南省"},{"ProId":17,"ProName":"湖北省"},{"ProId":18,"ProName":"湖南省"},{"ProId":19,"ProName":"广东省"},{"ProId":20,"ProName":"海南省"},{"ProId":21,"ProName":"广西壮族自治区"},{"ProId":22,"ProName":"甘肃省"},{"ProId":23,"ProName":"陕西省"},{"ProId":24,"ProName":"新疆维吾尔自治区"},{"ProId":25,"ProName":"青海省"},{"ProId":26,"ProName":"宁夏回族自治区"},{"ProId":27,"ProName":"重庆市"},{"ProId":28,"ProName":"四川省"},{"ProId":29,"ProName":"贵州省"},{"ProId":30,"ProName":"云南省"},{"ProId":31,"ProName":"西藏自治区"},{"ProId":32,"ProName":"台湾省"},{"ProId":33,"ProName":"澳门特别行政区"},{"ProId":34,"ProName":"香港特别行政区"}]
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
         * ProId : 0
         * ProName : ==请选择==
         */

        private String ProId;
        private String ProName;

        public void setProId(String ProId) {
            this.ProId = ProId;
        }

        public void setProName(String ProName) {
            this.ProName = ProName;
        }

        public String getProId() {
            return ProId;
        }

        public String getProName() {

            return ProName;
        }
        @Override
        public String toString() {
            return getProName();
        }
    }


}
