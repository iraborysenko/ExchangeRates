package com.borysenko.exchangerates.retrofit;

import com.borysenko.exchangerates.model.ExchangeRates;
import com.borysenko.exchangerates.model.Rate;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 16:53
 */
public class RatesDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String rateDate = jsonObject.get("date").getAsString();
        String baseCurrency = jsonObject.get("baseCurrencyLit").getAsString();

        Rate[] rates = context.deserialize(jsonObject.get("exchangeRate"), Rate[].class);

        return new ExchangeRates(
                rateDate,
                baseCurrency,
                rates
        );
    }
}
