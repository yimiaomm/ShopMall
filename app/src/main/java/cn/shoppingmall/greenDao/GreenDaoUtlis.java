package cn.shoppingmall.greenDao;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import cn.shoppingmall.MyApplication;
import cn.shoppingmall.activity.LoginActivity;
import cn.shoppingmall.bean.AddressBean;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.utils.ToastUtils;


/**
 * Created by ${易淼} on 2017/9/4.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class GreenDaoUtlis {
    private final static String dbName = "UserBean";
    private static GreenDaoUtlis mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public GreenDaoUtlis(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }


    public static GreenDaoUtlis getInstance(Context context) {
        if (mInstance == null) {
            synchronized (GreenDaoUtlis.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoUtlis(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /*
      * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(DataEntity user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DataEntityDao userDao = daoSession.getDataEntityDao();
        userDao.insert(user);
    }
    public DataEntity query(){

            DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
            DaoSession daoSession = daoMaster.newSession();
            DataEntityDao userDao = daoSession.getDataEntityDao();
            QueryBuilder<DataEntity> qb = userDao.queryBuilder();
        if (qb.list().size()<=0){
            ToastUtils.showToast("尚未登陆");
            Context context = MyApplication.getAppCtx();
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intent);
            return null;
        }
            DataEntity dataEntity = qb.list().get(0);
            return dataEntity;
    }

    public DataEntity queryDefult(){

        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DataEntityDao userDao = daoSession.getDataEntityDao();
        QueryBuilder<DataEntity> qb = userDao.queryBuilder();
        if (qb.list().size()<=0){
            return null;
        }
        DataEntity dataEntity = qb.list().get(0);
        return dataEntity;
    }
    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(DataEntity user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DataEntityDao userDao = daoSession.getDataEntityDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(DataEntity user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DataEntityDao userDao = daoSession.getDataEntityDao();
        userDao.update(user);
    }
}
