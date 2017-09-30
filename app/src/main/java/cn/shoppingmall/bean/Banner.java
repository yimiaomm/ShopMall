package cn.shoppingmall.bean;

import java.util.List;

/**
 * Created by Ivan on 15/10/2.
 */
public class Banner extends BaseBean {


    /**
     * success : true
     * msg : 返回成功
     * data : [{"ID":148,"LinkTitle":"2","LinkPic":"http://1024.zyldf.com/shop/img2/img02.jpg","LinkUrlMobile":"javascript:;"},{"ID":147,"LinkTitle":"1","LinkPic":"http://1024.zyldf.com/shop/img2/img01.jpg","LinkUrlMobile":"javascript:;"}]
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
         * ID : 148
         * LinkTitle : 2
         * LinkPic : http://1024.zyldf.com/shop/img2/img02.jpg
         * LinkUrlMobile : javascript:;
         */

        private String ID;
        private String LinkTitle;
        private String LinkPic;
        private String LinkUrlMobile;

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setLinkTitle(String LinkTitle) {
            this.LinkTitle = LinkTitle;
        }

        public void setLinkPic(String LinkPic) {
            this.LinkPic = LinkPic;
        }

        public void setLinkUrlMobile(String LinkUrlMobile) {
            this.LinkUrlMobile = LinkUrlMobile;
        }

        public String getID() {
            return ID;
        }

        public String getLinkTitle() {
            return LinkTitle;
        }

        public String getLinkPic() {
            return LinkPic;
        }

        public String getLinkUrlMobile() {
            return LinkUrlMobile;
        }
    }
}
