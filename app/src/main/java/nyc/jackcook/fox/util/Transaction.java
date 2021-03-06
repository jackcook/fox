package nyc.jackcook.fox.util;

import org.json.JSONException;
import org.json.JSONObject;

public class Transaction {

    public String id;
    public String type;
    public String status;
    public double btcAmount;
    public double usdAmount;
    public String date;
    public String recipient;

    public Transaction(JSONObject object) {
        try {
            this.id = object.getString("id");
            this.type = object.getString("type");
            this.status = object.getString("status");
            this.btcAmount = Double.parseDouble(object.getJSONObject("amount").getString("amount"));
            this.usdAmount = Double.parseDouble(object.getJSONObject("native_amount").getString("amount"));
            this.date = object.getString("created_at");

            try {
                this.recipient = object.getJSONObject("to").getString("address");
            } catch (JSONException e) {
                this.recipient = "Coinbase Transaction";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
