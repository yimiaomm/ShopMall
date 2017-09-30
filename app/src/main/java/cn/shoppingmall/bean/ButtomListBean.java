package cn.shoppingmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${易淼} on 2017/8/29.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class ButtomListBean {

    /**
     * success : true
     * msg : 返回成功
     * data : [{"ID":603,"ProdId":"65897489458548541","ProdName":"和田白玉五件套","Price1":8800,"Price2":8800,"PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg"},{"ID":602,"ProdId":"685989459848474","ProdName":"和田金镶玉","Price1":2680,"Price2":2680,"PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602174021_0387.jpg"},{"ID":601,"ProdId":"6897787484447478","ProdName":"益生元青汁","Price1":298,"Price2":298,"PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602173005_3912.jpg"},{"ID":600,"ProdId":"6859978484747447","ProdName":"排毒养颜精油","Price1":460,"Price2":460,"PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172806_8356.jpg"},{"ID":599,"ProdId":"689989458948484","ProdName":"控油调理精油","Price1":460,"Price2":460,"PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172639_9743.jpg"},{"ID":598,"ProdId":"6859858948454145","ProdName":"迷迭香精油","Price1":290,"Price2":290,"PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172509_8180.jpg"},{"ID":597,"ProdId":"685987945984584","ProdName":"腰背理疗精油","Price1":460,"Price2":460,"PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172351_3072.jpg"},{"ID":596,"ProdId":"66898797484484784","ProdName":"刮痧专用精油","Price1":460,"Price2":460,"PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172207_6520.jpg"}]
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

    public static class DataEntity implements Serializable{
        /**
         * ID : 603
         * ProdId : 65897489458548541
         * ProdName : 和田白玉五件套
         * Price1 : 8800.0
         * Price2 : 8800.0
         * PicUrl : http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg
         */

        private String ID;
        private String ProdId;
        private String ProdName;
        private String Price1;
        private String Price2;
        private String PicUrl;

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setProdId(String ProdId) {
            this.ProdId = ProdId;
        }

        public void setProdName(String ProdName) {
            this.ProdName = ProdName;
        }

        public void setPrice1(String Price1) {
            this.Price1 = Price1;
        }

        public void setPrice2(String Price2) {
            this.Price2 = Price2;
        }

        public void setPicUrl(String PicUrl) {
            this.PicUrl = PicUrl;
        }

        public String getID() {
            return ID;
        }

        public String getProdId() {
            return ProdId;
        }

        public String getProdName() {
            return ProdName;
        }

        public String getPrice1() {
            return Price1;
        }

        public String getPrice2() {
            return Price2;
        }

        public String getPicUrl() {
            return PicUrl;
        }
    }
}
