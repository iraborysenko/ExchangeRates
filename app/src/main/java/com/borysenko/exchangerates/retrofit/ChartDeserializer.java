package com.borysenko.exchangerates.retrofit;


import com.borysenko.exchangerates.model.ChartData;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/12/18
 * Time: 11:14
 */
public class ChartDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String rateDate = jsonObject.get("date").getAsString();
        float nbRate = 0.0f;
        float saleRate = 0.0f;
        float purchaseRate = 0.0f;

        JsonArray array = (JsonArray) jsonObject.get("exchangeRate");
        for (JsonElement item:array) {
            JsonObject usd = item.getAsJsonObject();
            if(usd.has("currency") && usd.get("currency").getAsString().equals("USD")) {
                nbRate = usd.get("purchaseRateNB").getAsFloat();
                saleRate = usd.get("saleRate").getAsFloat();
                purchaseRate = usd.get("purchaseRate").getAsFloat();
                break;
            }
        }

        return new ChartData(
                rateDate,
                nbRate,
                saleRate,
                purchaseRate
        );
    }
}
