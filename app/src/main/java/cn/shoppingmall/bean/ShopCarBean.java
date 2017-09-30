package cn.shoppingmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${易淼} on 2017/9/28.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class ShopCarBean implements Serializable{

    /**
     * success : true
     * msg : 返回成功
     * data : {"TotalNum":3,"TotalPrice":26400,"Freight":0,"ShopCartList":[{"ID":18,"UserId":"U104046","ProdId":"65897489458548541","SnapPrice":8800,"SnapPrice3":0,"BuyNum":3,"SubTime":"2017-09-28T17:01:58.92","IsCheck":true,"ProdName":"和田白玉五件套","Spec":"五件套","Unit":"套盒","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg"}]}
     * {"success":true,"msg":"返回成功","data":{"TotalNum":3,"TotalPrice":1185.00,"Freight":0.0,"ShopCartList":[{"ID":68,"UserId":"U104046","ProdId":"12561651216165216","SnapPrice":265.00,"Price2":null,"SnapPrice3":0.00,"BuyNum":1,"SubTime":"2017-09-29T14:17:31.623","IsCheck":true,"ProdName":"清爽控油洁面冰晶","Spec":"300ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602155905_6559.jpg"},{"ID":66,"UserId":"U104046","ProdId":"6953234321645","SnapPrice":285.00,"Price2":null,"SnapPrice3":0.00,"BuyNum":1,"SubTime":"2017-09-29T14:17:24.173","IsCheck":true,"ProdName":"焕颜细致排毒精华","Spec":"35ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602170357_8942.jpg"},{"ID":65,"UserId":"U104046","ProdId":"689897874848744","SnapPrice":635.00,"Price2":null,"SnapPrice3":0.00,"BuyNum":1,"SubTime":"2017-09-29T14:17:21.363","IsCheck":true,"ProdName":"斑后巩固专用分解液","Spec":"300ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602171843_8267.jpg"}]},"errcode":""}
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

    public static class DataEntity implements Serializable{
        /**
         * TotalNum : 3
         * TotalPrice : 26400.0
         * Freight : 0.0
         * ShopCartList : [{"ID":18,"UserId":"U104046","ProdId":"65897489458548541","SnapPrice":8800,"SnapPrice3":0,"BuyNum":3,"SubTime":"2017-09-28T17:01:58.92","IsCheck":true,"ProdName":"和田白玉五件套","Spec":"五件套","Unit":"套盒","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg"}]
         *
         */

        private String TotalNum;
        private String TotalPrice;
        private String Freight;
        private List<ShopCartListEntity> ShopCartList;

        public void setTotalNum(String TotalNum) {
            this.TotalNum = TotalNum;
        }

        public void setTotalPrice(String TotalPrice) {
            this.TotalPrice = TotalPrice;
        }

        public void setFreight(String Freight) {
            this.Freight = Freight;
        }

        public void setShopCartList(List<ShopCartListEntity> ShopCartList) {
            this.ShopCartList = ShopCartList;
        }

        public String getTotalNum() {
            return TotalNum;
        }

        public String getTotalPrice() {
            return TotalPrice;
        }

        public String getFreight() {
            return Freight;
        }

        public List<ShopCartListEntity> getShopCartList() {
            return ShopCartList;
        }

        public static class ShopCartListEntity implements Serializable{
            /**
             * ID : 18
             * UserId : U104046
             * ProdId : 65897489458548541
             * SnapPrice : 8800.0
             * SnapPrice3 : 0.0
             * BuyNum : 3
             * SubTime : 2017-09-28T17:01:58.92
             * IsCheck : true
             * ProdName : 和田白玉五件套
             * Spec : 五件套
             * Unit : 套盒
             * PicUrl : http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg
             */
            private int pid;
            private String ID;
            private String UserId;
            private String ProdId;
            private String SnapPrice;
            private String SnapPrice3;
            private String BuyNum;
            private String SubTime;
            private String IsCheck;
            private String ProdName;
            private String Spec;
            private String Unit;
            private String PicUrl;

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public void setProdId(String ProdId) {
                this.ProdId = ProdId;
            }

            public void setSnapPrice(String SnapPrice) {
                this.SnapPrice = SnapPrice;
            }

            public void setSnapPrice3(String SnapPrice3) {
                this.SnapPrice3 = SnapPrice3;
            }

            public void setBuyNum(String BuyNum) {
                this.BuyNum = BuyNum;
            }

            public void setSubTime(String SubTime) {
                this.SubTime = SubTime;
            }

            public void setIsCheck(String IsCheck) {
                this.IsCheck = IsCheck;
            }

            public void setProdName(String ProdName) {
                this.ProdName = ProdName;
            }

            public void setSpec(String Spec) {
                this.Spec = Spec;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }

            public void setPicUrl(String PicUrl) {
                this.PicUrl = PicUrl;
            }

            public String getID() {
                return ID;
            }

            public String getUserId() {
                return UserId;
            }

            public String getProdId() {
                return ProdId;
            }

            public String getSnapPrice() {
                return SnapPrice;
            }

            public String getSnapPrice3() {
                return SnapPrice3;
            }

            public String getBuyNum() {
                return BuyNum;
            }

            public String getSubTime() {
                return SubTime;
            }

            public String getIsCheck() {
                return IsCheck;
            }

            public String getProdName() {
                return ProdName;
            }

            public String getSpec() {
                return Spec;
            }

            public String getUnit() {
                return Unit;
            }

            public String getPicUrl() {
                return PicUrl;
            }
        }
    }
}
