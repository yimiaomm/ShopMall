package cn.shoppingmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${易淼} on 2017/9/11.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class ProductDetailsBean   implements Serializable{


    /**
     * "data":{"ID":599,"ProdId":"689989458948484","ProdName":"控油调理精油","Spec":"50ml","Unit":"瓶","Price1":460.00,"Price2":460.00,"Price3":0.00
     * ,"Price4":0.00,"Integral1":0,"Integral2":0,"Amount_Stock":100,"Amount_Sale":0,"CategoryId":20,"BrandId":1,"BasicParam":"控油调理精油","ProdText":null
     * ,"PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172639_9743.jpg","Comm_Good":0,"Comm_Middle":0,"Comm_Bad":0,"PushLocation":"0,1,2,3,"
     * ,"ProdKeys":"控油调理精油","SubUserId":"admin","SubTime":"2017-06-02T17:26:50","ProdStatus":3,"ProdType":1,"IsSettle":0,"Tops":0,
     * "toURL":"http://1024.zyldf.com/m/client/prodview.aspx?id=599","StrProdStatus":"已上架","CategoryName":"化妆品","BrandName":"默认品牌"
     * ,"ProdImgs":[{"ID":0,"ProdId":"689989458948484","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172639_9743.jpg",
     * "UpTime":"2017-06-02T17:26:50"}]},"errcode":""}
     * success : true
     * msg : 返回成功
     * data : {"ID":603,"ProdId":"65897489458548541","ProdName":"和田白玉五件套","Spec":"五件套","Unit":"套盒","Price1":8800,"Price2":8800,"Price3":0,"Price4":0,"Integral1":0,"Integral2":0,"Amount_Stock":100,"Amount_Sale":0,"CategoryId":8,"BrandId":1,"BasicParam":"和田白玉五件套","ProdText":null,"PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg","Comm_Good":0,"Comm_Middle":0,"Comm_Bad":0,"PushLocation":"0,1,2,3,","ProdKeys":"和田白玉五件套","SubUserId":"admin","SubTime":"2017-06-02T18:07:58","ProdStatus":3,"ProdType":1,"IsSettle":0,"Tops":0,"toURL":"http://1024.zyldf.com/m/client/prodview.aspx?id=603","StrProdStatus":"已上架","CategoryName":"珠宝玉器","BrandName":"默认品牌","ProdImgs":[{"ID":0,"ProdId":"65897489458548541","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg","UpTime":"2017-06-02T18:07:58"}]}
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
         * ID : 603
         * ProdId : 65897489458548541
         * ProdName : 和田白玉五件套
         * Spec : 五件套
         * Unit : 套盒
         * Price1 : 8800.0
         * Price2 : 8800.0
         * Price3 : 0.0
         * Price4 : 0.0
         * Integral1 : 0
         * Integral2 : 0
         * Amount_Stock : 100
         * Amount_Sale : 0
         * CategoryId : 8
         * BrandId : 1
         * BasicParam : 和田白玉五件套
         * ProdText : null
         * PicUrl : http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg
         * Comm_Good : 0
         * Comm_Middle : 0
         * Comm_Bad : 0
         * PushLocation : 0,1,2,3,
         * ProdKeys : 和田白玉五件套
         * SubUserId : admin
         * SubTime : 2017-06-02T18:07:58
         * ProdStatus : 3
         * ProdType : 1
         * IsSettle : 0
         * Tops : 0
         * toURL : http://1024.zyldf.com/m/client/prodview.aspx?id=603
         * StrProdStatus : 已上架
         * CategoryName : 珠宝玉器
         * BrandName : 默认品牌
         * ProdImgs : [{"ID":0,"ProdId":"65897489458548541","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg","UpTime":"2017-06-02T18:07:58"}]
         */

        private String ID;
        private String ProdId;
        private String ProdName;
        private String Spec;
        private String Unit;
        private String Price1;
        private String Price2;
        private String Price3;
        private String Price4;
        private String Integral1;
        private String Integral2;
        private String Amount_Stock;
        private String Amount_Sale;
        private String CategoryId;
        private String BrandId;
        private String BasicParam;
        private String ProdText;
        private String PicUrl;
        private String Comm_Good;
        private String Comm_Middle;
        private String Comm_Bad;
        private String PushLocation;
        private String ProdKeys;
        private String SubUserId;
        private String SubTime;
        private String ProdStatus;
        private String ProdType;
        private String IsSettle;
        private String Tops;
        private String toURL;
        private String StrProdStatus;
        private String CategoryName;
        private String BrandName;
        private List<ProdImgsEntity> ProdImgs;



        public void setProdId(String ProdId) {
            this.ProdId = ProdId;
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


        public void setBasicParam(String BasicParam) {
            this.BasicParam = BasicParam;
        }


        public void setPicUrl(String PicUrl) {
            this.PicUrl = PicUrl;
        }



        public void setPushLocation(String PushLocation) {
            this.PushLocation = PushLocation;
        }

        public void setProdKeys(String ProdKeys) {
            this.ProdKeys = ProdKeys;
        }

        public void setSubUserId(String SubUserId) {
            this.SubUserId = SubUserId;
        }

        public void setSubTime(String SubTime) {
            this.SubTime = SubTime;
        }



        public void setToURL(String toURL) {
            this.toURL = toURL;
        }

        public void setStrProdStatus(String StrProdStatus) {
            this.StrProdStatus = StrProdStatus;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public void setProdImgs(List<ProdImgsEntity> ProdImgs) {
            this.ProdImgs = ProdImgs;
        }


        public String getProdId() {
            return ProdId;
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






        public String getBasicParam() {
            return BasicParam;
        }

        public Object getProdText() {
            return ProdText;
        }

        public String getPicUrl() {
            return PicUrl;
        }



        public String getPushLocation() {
            return PushLocation;
        }

        public String getProdKeys() {
            return ProdKeys;
        }

        public String getSubUserId() {
            return SubUserId;
        }

        public String getSubTime() {
            return SubTime;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPrice1() {
            return Price1;
        }

        public void setPrice1(String price1) {
            Price1 = price1;
        }

        public String getPrice2() {
            return Price2;
        }

        public void setPrice2(String price2) {
            Price2 = price2;
        }

        public String getPrice3() {
            return Price3;
        }

        public void setPrice3(String price3) {
            Price3 = price3;
        }

        public String getPrice4() {
            return Price4;
        }

        public void setPrice4(String price4) {
            Price4 = price4;
        }

        public String getIntegral1() {
            return Integral1;
        }

        public void setIntegral1(String integral1) {
            Integral1 = integral1;
        }

        public String getIntegral2() {
            return Integral2;
        }

        public void setIntegral2(String integral2) {
            Integral2 = integral2;
        }

        public String getAmount_Stock() {
            return Amount_Stock;
        }

        public void setAmount_Stock(String amount_Stock) {
            Amount_Stock = amount_Stock;
        }

        public String getAmount_Sale() {
            return Amount_Sale;
        }

        public void setAmount_Sale(String amount_Sale) {
            Amount_Sale = amount_Sale;
        }

        public String getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(String categoryId) {
            CategoryId = categoryId;
        }

        public String getBrandId() {
            return BrandId;
        }

        public void setBrandId(String brandId) {
            BrandId = brandId;
        }

        public void setProdText(String prodText) {
            ProdText = prodText;
        }

        public String getComm_Good() {
            return Comm_Good;
        }

        public void setComm_Good(String comm_Good) {
            Comm_Good = comm_Good;
        }

        public String getComm_Middle() {
            return Comm_Middle;
        }

        public void setComm_Middle(String comm_Middle) {
            Comm_Middle = comm_Middle;
        }

        public String getComm_Bad() {
            return Comm_Bad;
        }

        public void setComm_Bad(String comm_Bad) {
            Comm_Bad = comm_Bad;
        }

        public String getProdStatus() {
            return ProdStatus;
        }

        public void setProdStatus(String prodStatus) {
            ProdStatus = prodStatus;
        }

        public String getProdType() {
            return ProdType;
        }

        public void setProdType(String prodType) {
            ProdType = prodType;
        }

        public String getIsSettle() {
            return IsSettle;
        }

        public void setIsSettle(String isSettle) {
            IsSettle = isSettle;
        }

        public String getTops() {
            return Tops;
        }

        public void setTops(String tops) {
            Tops = tops;
        }

        public String getToURL() {
            return toURL;
        }

        public String getStrProdStatus() {
            return StrProdStatus;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public String getBrandName() {
            return BrandName;
        }

        public List<ProdImgsEntity> getProdImgs() {
            return ProdImgs;
        }

        public static class ProdImgsEntity implements Serializable{
            /**
             * ID : 0
             * ProdId : 65897489458548541
             * PicUrl : http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg
             * UpTime : 2017-06-02T18:07:58
             */

            private int ID;
            private String ProdId;
            private String PicUrl;
            private String UpTime;

            public void setID(int ID) {
                this.ID = ID;
            }

            public void setProdId(String ProdId) {
                this.ProdId = ProdId;
            }

            public void setPicUrl(String PicUrl) {
                this.PicUrl = PicUrl;
            }

            public void setUpTime(String UpTime) {
                this.UpTime = UpTime;
            }

            public int getID() {
                return ID;
            }

            public String getProdId() {
                return ProdId;
            }

            public String getPicUrl() {
                return PicUrl;
            }

            public String getUpTime() {
                return UpTime;
            }
        }
    }
}
