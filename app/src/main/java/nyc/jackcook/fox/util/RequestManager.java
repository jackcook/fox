package nyc.jackcook.fox.util;

import android.content.Context;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestManager {

    public static Context context;
    public static String baseURL = "http://192.168.2.113:3000";

    public static double btcBalance;
    public static double usdBalance;

    public static void getBalance(final Runnable runnable) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(context, baseURL + "/cbbalance" + tokens(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String response = new String(responseBody, "UTF-8");
                    JSONObject obj = new JSONArray(response).getJSONObject(0);

                    double balance_btc = Double.parseDouble(obj.getJSONObject("balance").getString("amount"));
                    double balance_usd = Double.parseDouble(obj.getJSONObject("native_balance").getString("amount"));

                    RequestManager.btcBalance = balance_btc;
                    RequestManager.usdBalance = balance_usd;

                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public static void refreshTokens(final Runnable runnable) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(context, baseURL + "/cbrefresh" + tokens(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String response = new String(responseBody, "UTF-8");
                    JSONObject data = new JSONObject(response);

                    String access = data.getString("access_token");
                    String refresh = data.getString("refresh_token");

                    RequestManager.setAccessToken(access);
                    RequestManager.setRefreshToken(refresh);

                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public static String tokens() {
        return "?token=" + accessToken() + "&refresh=" + refreshToken();
    }

    public static String accessToken() {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("access", null);
    }

    public static String refreshToken() {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("refresh", null);
    }

    public static void setAccessToken(String token) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("access", token).commit();
    }

    public static void setRefreshToken(String token) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("refresh", token).commit();
    }
}
