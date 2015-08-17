package nyc.jackcook.fox;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChartsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("FOX", "sent http request");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://foxwallet.elasticbeanstalk.com/chart", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String response = new String(responseBody, "UTF-8");
                    JSONArray values = new JSONObject(response).getJSONArray("values");
                    JSONArray smavalues = new JSONObject(response).getJSONArray("sma");

                    ArrayList<Entry> entries = new ArrayList<>();
                    ArrayList<Entry> smaentries = new ArrayList<>();
                    ArrayList<String> days = new ArrayList<>();

                    for (int i = 0; i < values.length(); i++) {
                        JSONObject value = (JSONObject) values.get(i);
                        String date = value.getString("x");
                        float price = (float) value.getDouble("y");

                        Entry entry = new Entry(price, i);
                        entries.add(entry);

                        days.add(i, date);
                    }

                    LineDataSet dataSet = new LineDataSet(entries, "BTC to USD");
                    dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
                    dataSet.setColor(getResources().getColor(R.color.main));
                    dataSet.setCircleSize(0);

                    for (int i = 0; i < smavalues.length(); i++) {
                        JSONObject value = (JSONObject) smavalues.get(i);
                        float price = (float) value.getDouble("y");

                        Entry entry = new Entry(price, i);
                        smaentries.add(entry);
                    }

                    LineDataSet dataSet1 = new LineDataSet(smaentries, "Simple Moving Average");
                    dataSet1.setAxisDependency(YAxis.AxisDependency.LEFT);
                    dataSet1.setColor(getResources().getColor(R.color.sma));
                    dataSet1.setCircleSize(0);

                    ArrayList<LineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(dataSet);
                    dataSets.add(dataSet1);

                    final LineData data = new LineData(days, dataSets);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LineChart lineChart = (LineChart) getView().findViewById(R.id.valueChart);
                            lineChart.setData(data);
                            lineChart.setDescription(getResources().getString(R.string.chart_description));
                            lineChart.invalidate();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("FOX", "failed http request, " + statusCode);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charts, container, false);

        return view;
    }
}
