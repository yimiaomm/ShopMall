package cn.shoppingmall.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import cn.shoppingmall.MainActivity;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.http.Api;

import static android.provider.Settings.*;

/**
 * Created by ${易淼} on 2017/9/25.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class NetUitls {

    private static LocationManager locationManager;

    public static Map<String, String> getHashMapData(Map<String, String> map) {
        map.put(Api.TIMESTAMP, getCurrentTiem());
        map.put(Api.NONCE, getRandomNum());
        map.put(Api.SIGNATURE, getAppSign(map));
        return map;
    }

    public static String getAppSign(Map map) {

        SortedMap<String, String> sort = new TreeMap<String, String>(map);
        Set<Map.Entry<String, String>> entrySet = sort.entrySet();
        Iterator<Map.Entry<String, String>> it = entrySet.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String value = entry.getValue();
            if (TextUtils.isEmpty(value)) {
                continue;//空参数不参与签名
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(value);
            sb.append('&');
        }
        String stringSignTemp = sb.append("key=" + Api.KEY).toString();//stringSignTemp.getBytes()
        return md5(stringSignTemp).toUpperCase();
    }

    //获取手机udid  部分手机固定 部分手机没有给随机数
    public static String getDeviceId(Context context) {
        String deviceId = ((TelephonyManager) MyApplication.getAppCtx().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = Secure.getString(MyApplication.getAppCtx().getContentResolver(), Secure.ANDROID_ID);
        }
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = getRandomNum();
        }
        return deviceId;
    }

    public static String checkPermissions() {
        String gpsgood = "";
        locationManager = (LocationManager) MyApplication.getAppCtx().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager==null){return "";}
        //获取所有可用的位置提供器
        String providers = judgeProvider(locationManager);
        Location location = locationManager.getLastKnownLocation(providers);
        if(location!=null){
            //不为空,显示地理位置经纬度
            gpsgood = location.getLatitude()+","+location.getLongitude();
        }
        return gpsgood;
    }
    private  static String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if(prodiverlist.contains(LocationManager.NETWORK_PROVIDER)){
            return LocationManager.NETWORK_PROVIDER;
        }else if(prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        }else{
            Toast.makeText(MyApplication.getAppCtx(),"没有可用的位置提供器",Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商 手机型号
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND+","+android.os.Build.MODEL;
    }

    //时间戳
    public static String getCurrentTiem() {
        return String.valueOf(new Date().getTime() / 1000);
    }
    //随机数
    public static String getRandomNum() {
        return String.valueOf(Math.abs(new Random().nextInt()));
    }
    //md5加密
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().toUpperCase();
    }
}
