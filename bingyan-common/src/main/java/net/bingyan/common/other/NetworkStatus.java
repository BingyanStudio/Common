package net.bingyan.common.other;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import net.bingyan.common.Common;

/**
 * Created by 2bab on 15/5/7.
 * 设备网络环境获取
 */
public class NetworkStatus {

    public static final int NETWORK_CLASS_2_G = 1;
    public static final int NETWORK_CLASS_3_G = 2;
    public static final int NETWORK_CLASS_4_G = 3;
    private static final int NETWORK_TYPE_UNAVAILABLE = -1;
    // private static final int NETWORK_TYPE_MOBILE = -100;
    private static final int NETWORK_TYPE_WIFI = -101;
    private static final int NETWORK_CLASS_WIFI = -101;
    private static final int NETWORK_CLASS_UNAVAILABLE = -1;
    private static final int NETWORK_CLASS_UNKNOWN = 0;

    /*详细的网络信息*/
    private static final int NETWORK_TYPE_UNKNOWN = 0;
    private static final int NETWORK_TYPE_GPRS = 1;
    private static final int NETWORK_TYPE_EDGE = 2;
    private static final int NETWORK_TYPE_UMTS = 3;
    private static final int NETWORK_TYPE_CDMA = 4;
    private static final int NETWORK_TYPE_EVDO_0 = 5;
    private static final int NETWORK_TYPE_EVDO_A = 6;
    private static final int NETWORK_TYPE_1xRTT = 7;
    private static final int NETWORK_TYPE_HSDPA = 8;
    private static final int NETWORK_TYPE_HSUPA = 9;
    private static final int NETWORK_TYPE_HSPA = 10;
    private static final int NETWORK_TYPE_IDEN = 11;
    private static final int NETWORK_TYPE_EVDO_B = 12;
    private static final int NETWORK_TYPE_LTE = 13;
    private static final int NETWORK_TYPE_EHRPD = 14;
    private static final int NETWORK_TYPE_HSPAP = 15;

    /**
     * 获取设备网络环境
     */
    private static NetworkInfo getNetworkStatus() {
        ConnectivityManager cm = (ConnectivityManager) Common.context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 检测网络连接
     *
     * @return 当前是否有网络连接
     */
    public static boolean isConnected() {
        NetworkInfo netInfo = getNetworkStatus();
        Log.e("network check", (netInfo != null && netInfo.isConnectedOrConnecting()) + "");
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * 检测网络状态是否为 Wifi
     *
     * @return 当前是否为Wifi网络
     */
    public static boolean isWifi() {
        NetworkInfo netInfo = getNetworkStatus();
        return netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 通配检测，有无网络，是否WIFI，移动网络的那种（2G、3G、4G），未知网络情况
     *
     * @return 对应的 int 值
     */
    public static int getMobileNetWorkType() {
        int networkType = NETWORK_TYPE_UNKNOWN;
        try {
            final NetworkInfo network = getNetworkStatus();
            if (network != null && network.isAvailable()
                    && network.isConnected()) {
                int type = network.getType();
                if (type == ConnectivityManager.TYPE_WIFI) {
                    networkType = NETWORK_TYPE_WIFI;
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    TelephonyManager telephonyManager = (TelephonyManager) Common
                            .context.getSystemService(
                                    Context.TELEPHONY_SERVICE);
                    networkType = telephonyManager.getNetworkType();
                }
            } else {
                networkType = NETWORK_TYPE_UNAVAILABLE;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return getNetworkClassByType(networkType);
    }

    /**
     * 归类移动网络
     */
    private static int getNetworkClassByType(int networkType) {
        switch (networkType) {
            case NETWORK_TYPE_UNAVAILABLE:
                return NETWORK_CLASS_UNAVAILABLE;
            case NETWORK_TYPE_WIFI:
                return NETWORK_CLASS_WIFI;
            case NETWORK_TYPE_GPRS:
            case NETWORK_TYPE_EDGE:
            case NETWORK_TYPE_CDMA:
            case NETWORK_TYPE_1xRTT:
            case NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case NETWORK_TYPE_UMTS:
            case NETWORK_TYPE_EVDO_0:
            case NETWORK_TYPE_EVDO_A:
            case NETWORK_TYPE_HSDPA:
            case NETWORK_TYPE_HSUPA:
            case NETWORK_TYPE_HSPA:
            case NETWORK_TYPE_EVDO_B:
            case NETWORK_TYPE_EHRPD:
            case NETWORK_TYPE_HSPAP:
                return NETWORK_CLASS_3_G;
            case NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }
}