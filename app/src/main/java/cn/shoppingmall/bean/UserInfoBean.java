package cn.shoppingmall.bean;


/**
 * Created by ${易淼} on 2017/9/28.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class UserInfoBean {

    /**
     * success : true
     * msg : 操作成功
     * data : {"UserId":"U104046","UserName":"","UserTel":"15036145858","Email":"","IdenCard":"","UserAddress":"","BankName":"","BankAccount":null,"UserStatus":1,"EM":0,"EM2":0,"EM3":0,"EM4":0,"EM5":0,"EM6":0,"EM7":0,"EM8":0,"EM9":0,"EM10":0,"EM_First":0,"LevelId":10,"LevelId2":10,"LevelId3":1,"Iden":0,"RegTime":"2017-09-28T09:37:16.637","StartTime":"2017-09-28T09:37:16.66","EndTime":"2037-09-28T09:37:16.66","ActUserId":"admin","RefUserId":"admin","MyUsers":0,"MyTotalMoney":0,"TeamUsers":1,"TeamTotalMoney":0,"MyAreaUsers":0,"MyAreaTotalMoney":0,"ActUserName":"系统管理","RefUserName":"系统管理","CountyName":null,"CityName":null,"ProName":null,"LevelName":"VIP会员","Money1":0,"Money2":0,"Money3":0,"Money4":0,"SettLayer1":0,"SettLayer2":0,"Range1":0,"Range2":0,"Quota1":0,"Quota2":0,"Rebate":1,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJVMTA0MDQ2IiwiaWF0IjoxNTA2NTY5MDIyfQ.RTnJMLNQQRLLnL6jNnSNdzXPmQdfGSrf3VjV-Wm-YSs"}
     * errcode : DATA_EMPTY
     */

    private String success;
    private String msg;
    private DataEntity data;
    private String errcode;

    public String getSuccess() {
        return success;
    }

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


    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public String getErrcode() {
        return errcode;
    }


}
