package cn.shoppingmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 2017/9/28.
 */

public class ClasslflyBean implements Serializable{

    /**
     * success : true
     * msg : 返回成功
     * data : [{"CategoryId":1,"CategoryName":"图书音响","CategoryPicture":null,"ParentId":0,"ChildCategory":[{"CategoryId":11,"CategoryName":"少儿/文艺","CategoryPicture":null,"ParentId":1,"ChildCategory":null},{"CategoryId":12,"CategoryName":"生活/科技","CategoryPicture":null,"ParentId":1,"ChildCategory":null},{"CategoryId":14,"CategoryName":"中外名著","CategoryPicture":null,"ParentId":1,"ChildCategory":null},{"CategoryId":13,"CategoryName":"人文自然","CategoryPicture":null,"ParentId":1,"ChildCategory":null}]},{"CategoryId":7,"CategoryName":"女性生活","CategoryPicture":null,"ParentId":0,"ChildCategory":[{"CategoryId":20,"CategoryName":"化妆品","CategoryPicture":null,"ParentId":7,"ChildCategory":null},{"CategoryId":21,"CategoryName":"面膜","CategoryPicture":null,"ParentId":7,"ChildCategory":null}]},{"CategoryId":2,"CategoryName":"家用电器","CategoryPicture":null,"ParentId":0,"ChildCategory":[{"CategoryId":19,"CategoryName":"小家电","CategoryPicture":null,"ParentId":2,"ChildCategory":null}]},{"CategoryId":3,"CategoryName":"生活超市","CategoryPicture":null,"ParentId":0,"ChildCategory":[{"CategoryId":17,"CategoryName":"酒水茶品","CategoryPicture":null,"ParentId":3,"ChildCategory":null},{"CategoryId":18,"CategoryName":"日常用品","CategoryPicture":null,"ParentId":3,"ChildCategory":null}]},{"CategoryId":4,"CategoryName":"手机数码","CategoryPicture":null,"ParentId":0,"ChildCategory":[]},{"CategoryId":8,"CategoryName":"珠宝玉器","CategoryPicture":null,"ParentId":0,"ChildCategory":[]},{"CategoryId":5,"CategoryName":"服务鞋帽","CategoryPicture":null,"ParentId":0,"ChildCategory":[]},{"CategoryId":6,"CategoryName":"运动户外","CategoryPicture":null,"ParentId":0,"ChildCategory":[]},{"CategoryId":9,"CategoryName":"备用分类","CategoryPicture":null,"ParentId":0,"ChildCategory":[]},{"CategoryId":10,"CategoryName":"备用分类","CategoryPicture":null,"ParentId":0,"ChildCategory":[]}]
     * errcode :
     */

    private String success;
    private String msg;
    private String errcode;
    private List<DataBean> data;

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

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * CategoryId : 1
         * CategoryName : 图书音响
         * CategoryPicture : null
         * ParentId : 0
         * ChildCategory : [{"CategoryId":11,"CategoryName":"少儿/文艺","CategoryPicture":null,"ParentId":1,"ChildCategory":null},{"CategoryId":12,"CategoryName":"生活/科技","CategoryPicture":null,"ParentId":1,"ChildCategory":null},{"CategoryId":14,"CategoryName":"中外名著","CategoryPicture":null,"ParentId":1,"ChildCategory":null},{"CategoryId":13,"CategoryName":"人文自然","CategoryPicture":null,"ParentId":1,"ChildCategory":null}]
         */

        private String CategoryId;
        private String CategoryName;
        private String CategoryPicture;
        private String ParentId;
        private List<ChildCategoryBean> ChildCategory;

        public String getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(String CategoryId) {
            this.CategoryId = CategoryId;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }

        public String getCategoryPicture() {
            return CategoryPicture;
        }

        public void setCategoryPicture(String CategoryPicture) {
            this.CategoryPicture = CategoryPicture;
        }

        public String getParentId() {
            return ParentId;
        }

        public void setParentId(String ParentId) {
            this.ParentId = ParentId;
        }

        public List<ChildCategoryBean> getChildCategory() {
            return ChildCategory;
        }

        public void setChildCategory(List<ChildCategoryBean> ChildCategory) {
            this.ChildCategory = ChildCategory;
        }

        public static class ChildCategoryBean implements Serializable{
            /**
             * CategoryId : 11
             * CategoryName : 少儿/文艺
             * CategoryPicture : null
             * ParentId : 1
             * ChildCategory : null
             */

            private String CategoryId;
            private String CategoryName;
            private String CategoryPicture;
            private String ParentId;
            private String ChildCategory;

            public String getCategoryId() {
                return CategoryId;
            }

            public void setCategoryId(String CategoryId) {
                this.CategoryId = CategoryId;
            }

            public String getCategoryName() {
                return CategoryName;
            }

            public void setCategoryName(String CategoryName) {
                this.CategoryName = CategoryName;
            }

            public String getCategoryPicture() {
                return CategoryPicture;
            }

            public void setCategoryPicture(String CategoryPicture) {
                this.CategoryPicture = CategoryPicture;
            }

            public String getParentId() {
                return ParentId;
            }

            public void setParentId(String ParentId) {
                this.ParentId = ParentId;
            }

            public Object getChildCategory() {
                return ChildCategory;
            }

            public void setChildCategory(String ChildCategory) {
                this.ChildCategory = ChildCategory;
            }
        }
    }
}
