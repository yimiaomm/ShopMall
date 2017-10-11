package cn.shoppingmall.bean;

import java.util.List;

/**
 * author：Anumbrella
 * Date：16/5/26 上午10:43
 */
public class ListProductContentModel{


    /**
     * success : true
     * msg : 返回成功
     * data : {"pageCount":8,"dataList":[{"ID":603,"ProdId":"65897489458548541","ProdName":"和田白玉五件套","Price1":8800,"Price2":8800,"PicUrl":"/uploads/shop/20170602/20170602181011_6110.jpg"},{"ID":602,"ProdId":"685989459848474","ProdName":"和田金镶玉","Price1":2680,"Price2":2680,"PicUrl":"/uploads/shop/20170602/20170602174021_0387.jpg"},{"ID":601,"ProdId":"6897787484447478","ProdName":"益生元青汁","Price1":298,"Price2":298,"PicUrl":"/uploads/shop/20170602/20170602173005_3912.jpg"},{"ID":600,"ProdId":"6859978484747447","ProdName":"排毒养颜精油","Price1":460,"Price2":460,"PicUrl":"/uploads/shop/20170602/20170602172806_8356.jpg"},{"ID":599,"ProdId":"689989458948484","ProdName":"控油调理精油","Price1":460,"Price2":460,"PicUrl":"/uploads/shop/20170602/20170602172639_9743.jpg"},{"ID":598,"ProdId":"6859858948454145","ProdName":"迷迭香精油","Price1":290,"Price2":290,"PicUrl":"/uploads/shop/20170602/20170602172509_8180.jpg"},{"ID":597,"ProdId":"685987945984584","ProdName":"腰背理疗精油","Price1":460,"Price2":460,"PicUrl":"/uploads/shop/20170602/20170602172351_3072.jpg"},{"ID":596,"ProdId":"66898797484484784","ProdName":"刮痧专用精油","Price1":460,"Price2":460,"PicUrl":"/uploads/shop/20170602/20170602172207_6520.jpg"},{"ID":595,"ProdId":"689897874848744","ProdName":"斑后巩固专用分解液","Price1":635,"Price2":635,"PicUrl":"/uploads/shop/20170602/20170602171843_8267.jpg"},{"ID":594,"ProdId":"6689484848444441","ProdName":"斑后巩固专用中药","Price1":1085,"Price2":1085,"PicUrl":"/uploads/shop/20170602/20170602171532_0856.jpg"}]}
     * errcode :
     */

    private String success;
    private String msg;
    private DataEntity data;
    private String errcode;

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public String getErrcode() {
        return errcode;
    }

    public static class DataEntity {
        /**
         * pageCount : 8
         * dataList : [{"ID":603,"ProdId":"65897489458548541","ProdName":"和田白玉五件套","Price1":8800,"Price2":8800,"PicUrl":"/uploads/shop/20170602/20170602181011_6110.jpg"},{"ID":602,"ProdId":"685989459848474","ProdName":"和田金镶玉","Price1":2680,"Price2":2680,"PicUrl":"/uploads/shop/20170602/20170602174021_0387.jpg"},{"ID":601,"ProdId":"6897787484447478","ProdName":"益生元青汁","Price1":298,"Price2":298,"PicUrl":"/uploads/shop/20170602/20170602173005_3912.jpg"},{"ID":600,"ProdId":"6859978484747447","ProdName":"排毒养颜精油","Price1":460,"Price2":460,"PicUrl":"/uploads/shop/20170602/20170602172806_8356.jpg"},{"ID":599,"ProdId":"689989458948484","ProdName":"控油调理精油","Price1":460,"Price2":460,"PicUrl":"/uploads/shop/20170602/20170602172639_9743.jpg"},{"ID":598,"ProdId":"6859858948454145","ProdName":"迷迭香精油","Price1":290,"Price2":290,"PicUrl":"/uploads/shop/20170602/20170602172509_8180.jpg"},{"ID":597,"ProdId":"685987945984584","ProdName":"腰背理疗精油","Price1":460,"Price2":460,"PicUrl":"/uploads/shop/20170602/20170602172351_3072.jpg"},{"ID":596,"ProdId":"66898797484484784","ProdName":"刮痧专用精油","Price1":460,"Price2":460,"PicUrl":"/uploads/shop/20170602/20170602172207_6520.jpg"},{"ID":595,"ProdId":"689897874848744","ProdName":"斑后巩固专用分解液","Price1":635,"Price2":635,"PicUrl":"/uploads/shop/20170602/20170602171843_8267.jpg"},{"ID":594,"ProdId":"6689484848444441","ProdName":"斑后巩固专用中药","Price1":1085,"Price2":1085,"PicUrl":"/uploads/shop/20170602/20170602171532_0856.jpg"}]
         */

        private int pageCount;
        private List<DataListEntity> dataList;

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public void setDataList(List<DataListEntity> dataList) {
            this.dataList = dataList;
        }

        public int getPageCount() {
            return pageCount;
        }

        public List<DataListEntity> getDataList() {
            return dataList;
        }

        public static class DataListEntity {
            /**
             * ID : 603
             * ProdId : 65897489458548541
             * ProdName : 和田白玉五件套
             * Price1 : 8800.0
             * Price2 : 8800.0
             * PicUrl : /uploads/shop/20170602/20170602181011_6110.jpg
             */

            private int ID;
            private String ProdId;
            private String ProdName;
            private double Price1;
            private double Price2;
            private String PicUrl;

            public void setID(int ID) {
                this.ID = ID;
            }

            public void setProdId(String ProdId) {
                this.ProdId = ProdId;
            }

            public void setProdName(String ProdName) {
                this.ProdName = ProdName;
            }

            public void setPrice1(double Price1) {
                this.Price1 = Price1;
            }

            public void setPrice2(double Price2) {
                this.Price2 = Price2;
            }

            public void setPicUrl(String PicUrl) {
                this.PicUrl = PicUrl;
            }

            public int getID() {
                return ID;
            }

            public String getProdId() {
                return ProdId;
            }

            public String getProdName() {
                return ProdName;
            }

            public double getPrice1() {
                return Price1;
            }

            public double getPrice2() {
                return Price2;
            }

            public String getPicUrl() {
                return PicUrl;
            }
        }
    }
}
