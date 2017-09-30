package cn.shoppingmall.bean;

import java.util.List;

/**
 * Created by pc on 2017/9/7.
 */

public class OrderListBean {

    /**
     * success : true
     * msg : 返回成功
     * data : {"pageCount":1,"dataList":[{"ID":4,"OrderId":"17090004","OrderPrice":2635,"ProdNum":5,"OrderTime":"2017-09-30T17:33:15.54","LogisId":"1","AWB":"","OrderStatus":1,"StrOrderStatus":"未付款","OrderContent":[{"ID":11,"ProdId":"6859858948454145","Num":2,"Price1":290,"Price2":290,"ProdName":"迷迭香精油","Spec":"10ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172509_8180.jpg"},{"ID":10,"ProdId":"66998789984841","Num":3,"Price1":685,"Price2":685,"ProdName":"点状斑抽色专用分解液","Spec":"300ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602171319_1119.jpg"}]},{"ID":3,"OrderId":"17090003","OrderPrice":7940,"ProdNum":7,"OrderTime":"2017-09-30T17:17:45.093","LogisId":"1","AWB":"","OrderStatus":1,"StrOrderStatus":"未付款","OrderContent":[{"ID":9,"ProdId":"685989459848474","Num":1,"Price1":2680,"Price2":2680,"ProdName":"和田金镶玉","Spec":"个","Unit":"个","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602174021_0387.jpg"},{"ID":8,"ProdId":"6859978484747447","Num":1,"Price1":460,"Price2":460,"ProdName":"排毒养颜精油","Spec":"50ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172806_8356.jpg"},{"ID":7,"ProdId":"66898797484484784","Num":1,"Price1":460,"Price2":460,"ProdName":"刮痧专用精油","Spec":"50ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172207_6520.jpg"},{"ID":6,"ProdId":"6689484848444441","Num":1,"Price1":1085,"Price2":1085,"ProdName":"斑后巩固专用中药","Spec":"500g","Unit":"桶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602171532_0856.jpg"},{"ID":5,"ProdId":"68998787451414514541","Num":3,"Price1":1085,"Price2":1085,"ProdName":"点状斑抽色专用中药","Spec":"500g","Unit":"桶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602171101_6383.jpg"}]},{"ID":2,"OrderId":"17090002","OrderPrice":10530,"ProdNum":4,"OrderTime":"2017-09-30T15:43:51.86","LogisId":"1","AWB":"","OrderStatus":1,"StrOrderStatus":"未付款","OrderContent":[{"ID":4,"ProdId":"65897489458548541","Num":1,"Price1":8800,"Price2":8800,"ProdName":"和田白玉五件套","Spec":"五件套","Unit":"套盒","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg"},{"ID":3,"ProdId":"689989458948484","Num":1,"Price1":460,"Price2":460,"ProdName":"控油调理精油","Spec":"50ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172639_9743.jpg"},{"ID":2,"ProdId":"689897874848744","Num":2,"Price1":635,"Price2":635,"ProdName":"斑后巩固专用分解液","Spec":"300ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602171843_8267.jpg"}]}]}
     * errcode :
     */

    private String success;
    private String msg;
    private DataBean data;
    private String errcode;

    public String isSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public static class DataBean {
        /**
         * pageCount : 1
         * dataList : [{"ID":4,"OrderId":"17090004","OrderPrice":2635,"ProdNum":5,"OrderTime":"2017-09-30T17:33:15.54","LogisId":"1","AWB":"","OrderStatus":1,"StrOrderStatus":"未付款","OrderContent":[{"ID":11,"ProdId":"6859858948454145","Num":2,"Price1":290,"Price2":290,"ProdName":"迷迭香精油","Spec":"10ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172509_8180.jpg"},{"ID":10,"ProdId":"66998789984841","Num":3,"Price1":685,"Price2":685,"ProdName":"点状斑抽色专用分解液","Spec":"300ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602171319_1119.jpg"}]},{"ID":3,"OrderId":"17090003","OrderPrice":7940,"ProdNum":7,"OrderTime":"2017-09-30T17:17:45.093","LogisId":"1","AWB":"","OrderStatus":1,"StrOrderStatus":"未付款","OrderContent":[{"ID":9,"ProdId":"685989459848474","Num":1,"Price1":2680,"Price2":2680,"ProdName":"和田金镶玉","Spec":"个","Unit":"个","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602174021_0387.jpg"},{"ID":8,"ProdId":"6859978484747447","Num":1,"Price1":460,"Price2":460,"ProdName":"排毒养颜精油","Spec":"50ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172806_8356.jpg"},{"ID":7,"ProdId":"66898797484484784","Num":1,"Price1":460,"Price2":460,"ProdName":"刮痧专用精油","Spec":"50ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172207_6520.jpg"},{"ID":6,"ProdId":"6689484848444441","Num":1,"Price1":1085,"Price2":1085,"ProdName":"斑后巩固专用中药","Spec":"500g","Unit":"桶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602171532_0856.jpg"},{"ID":5,"ProdId":"68998787451414514541","Num":3,"Price1":1085,"Price2":1085,"ProdName":"点状斑抽色专用中药","Spec":"500g","Unit":"桶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602171101_6383.jpg"}]},{"ID":2,"OrderId":"17090002","OrderPrice":10530,"ProdNum":4,"OrderTime":"2017-09-30T15:43:51.86","LogisId":"1","AWB":"","OrderStatus":1,"StrOrderStatus":"未付款","OrderContent":[{"ID":4,"ProdId":"65897489458548541","Num":1,"Price1":8800,"Price2":8800,"ProdName":"和田白玉五件套","Spec":"五件套","Unit":"套盒","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602181011_6110.jpg"},{"ID":3,"ProdId":"689989458948484","Num":1,"Price1":460,"Price2":460,"ProdName":"控油调理精油","Spec":"50ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172639_9743.jpg"},{"ID":2,"ProdId":"689897874848744","Num":2,"Price1":635,"Price2":635,"ProdName":"斑后巩固专用分解液","Spec":"300ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602171843_8267.jpg"}]}]
         */

        private int pageCount;
        private List<DataListBean> dataList;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * ID : 4
             * OrderId : 17090004
             * OrderPrice : 2635.0
             * ProdNum : 5
             * OrderTime : 2017-09-30T17:33:15.54
             * LogisId : 1
             * AWB :
             * OrderStatus : 1
             * StrOrderStatus : 未付款
             * OrderContent : [{"ID":11,"ProdId":"6859858948454145","Num":2,"Price1":290,"Price2":290,"ProdName":"迷迭香精油","Spec":"10ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602172509_8180.jpg"},{"ID":10,"ProdId":"66998789984841","Num":3,"Price1":685,"Price2":685,"ProdName":"点状斑抽色专用分解液","Spec":"300ml","Unit":"瓶","PicUrl":"http://1024.zyldf.com/uploads/shop/20170602/20170602171319_1119.jpg"}]
             */

            private String ID;
            private String OrderId;
            private String OrderPrice;
            private String ProdNum;
            private String OrderTime;
            private String LogisId;
            private String AWB;
            private String OrderStatus;
            private String StrOrderStatus;
            private List<OrderContentBean> OrderContent;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getOrderId() {
                return OrderId;
            }

            public void setOrderId(String OrderId) {
                this.OrderId = OrderId;
            }

            public String getOrderPrice() {
                return OrderPrice;
            }

            public void setOrderPrice(String OrderPrice) {
                this.OrderPrice = OrderPrice;
            }

            public String getProdNum() {
                return ProdNum;
            }

            public void setProdNum(String ProdNum) {
                this.ProdNum = ProdNum;
            }

            public String getOrderTime() {
                return OrderTime;
            }

            public void setOrderTime(String OrderTime) {
                this.OrderTime = OrderTime;
            }

            public String getLogisId() {
                return LogisId;
            }

            public void setLogisId(String LogisId) {
                this.LogisId = LogisId;
            }

            public String getAWB() {
                return AWB;
            }

            public void setAWB(String AWB) {
                this.AWB = AWB;
            }

            public String getOrderStatus() {
                return OrderStatus;
            }

            public void setOrderStatus(String OrderStatus) {
                this.OrderStatus = OrderStatus;
            }

            public String getStrOrderStatus() {
                return StrOrderStatus;
            }

            public void setStrOrderStatus(String StrOrderStatus) {
                this.StrOrderStatus = StrOrderStatus;
            }

            public List<OrderContentBean> getOrderContent() {
                return OrderContent;
            }

            public void setOrderContent(List<OrderContentBean> OrderContent) {
                this.OrderContent = OrderContent;
            }

            public static class OrderContentBean {
                /**
                 * ID : 11
                 * ProdId : 6859858948454145
                 * Num : 2
                 * Price1 : 290.0
                 * Price2 : 290.0
                 * ProdName : 迷迭香精油
                 * Spec : 10ml
                 * Unit : 瓶
                 * PicUrl : http://1024.zyldf.com/uploads/shop/20170602/20170602172509_8180.jpg
                 */

                private String ID;
                private String ProdId;
                private String Num;
                private String Price1;
                private String Price2;
                private String ProdName;
                private String Spec;
                private String Unit;
                private String PicUrl;

                public String getID() {
                    return ID;
                }

                public void setID(String ID) {
                    this.ID = ID;
                }

                public String getProdId() {
                    return ProdId;
                }

                public void setProdId(String ProdId) {
                    this.ProdId = ProdId;
                }

                public String getNum() {
                    return Num;
                }

                public void setNum(String Num) {
                    this.Num = Num;
                }

                public String getPrice1() {
                    return Price1;
                }

                public void setPrice1(String Price1) {
                    this.Price1 = Price1;
                }

                public String getPrice2() {
                    return Price2;
                }

                public void setPrice2(String Price2) {
                    this.Price2 = Price2;
                }

                public String getProdName() {
                    return ProdName;
                }

                public void setProdName(String ProdName) {
                    this.ProdName = ProdName;
                }

                public String getSpec() {
                    return Spec;
                }

                public void setSpec(String Spec) {
                    this.Spec = Spec;
                }

                public String getUnit() {
                    return Unit;
                }

                public void setUnit(String Unit) {
                    this.Unit = Unit;
                }

                public String getPicUrl() {
                    return PicUrl;
                }

                public void setPicUrl(String PicUrl) {
                    this.PicUrl = PicUrl;
                }
            }
        }
    }
}
