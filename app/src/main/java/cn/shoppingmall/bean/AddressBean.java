package cn.shoppingmall.bean;


import java.io.Serializable;
import java.util.List;


/**
 * Created by ${易淼} on 2017/9/1.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class AddressBean implements Serializable {

    /**
     * success : true
     * msg : 返回成功
     * data : [{"ID":7,"UserId":"U104046","Name":"yimiao","Tel":"15036145858","Postcode":null,"Addr":"Dong站","Province":"河南省","City":"郑州市","County":"中原区","IsDefault":0}]
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

    public static class DataEntity implements Serializable {
        /**
         * ID : 7
         * UserId : U104046
         * Name : yimiao
         * Tel : 15036145858
         * Postcode : null
         * Addr : Dong站
         * Province : 河南省
         * City : 郑州市
         * County : 中原区
         * IsDefault : 0
         */

        private String ID;
        private String UserId;
        private String Name;
        private String Tel;
        private String Postcode;
        private String Addr;
        private String Province;
        private String City;
        private String County;
        private String IsDefault;

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public void setPostcode(String Postcode) {
            this.Postcode = Postcode;
        }

        public void setAddr(String Addr) {
            this.Addr = Addr;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public void setCounty(String County) {
            this.County = County;
        }

        public void setIsDefault(String IsDefault) {
            this.IsDefault = IsDefault;
        }

        public String getID() {
            return ID;
        }

        public String getUserId() {
            return UserId;
        }

        public String getName() {
            return Name;
        }

        public String getTel() {
            return Tel;
        }

        public String getPostcode() {
            return Postcode;
        }

        public String getAddr() {
            return Addr;
        }

        public String getProvince() {
            return Province;
        }

        public String getCity() {
            return City;
        }

        public String getCounty() {
            return County;
        }

        public String getIsDefault() {
            return IsDefault;
        }
    }
}
