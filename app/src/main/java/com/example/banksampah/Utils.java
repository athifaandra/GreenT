package com.example.banksampah;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String convertMillisToDateString(long millis) {
        // Create a Date object from milliseconds
        Date date = new Date(millis);

        // Define the desired date pattern
        String pattern = "dd/MM/yyyy HH:mm";

        // Create a SimpleDateFormat object with the specified pattern and locale
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());

        // Format the date and return the formatted string
        return simpleDateFormat.format(date);
    }

    public static String formatPrice(long amount) {
        // Create a NumberFormat for the specified locale and currency
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        // Set the currency to Indonesian Rupiah
        currencyFormatter.setCurrency(Currency.getInstance("IDR"));

        // Format the amount and return the formatted string
        return currencyFormatter.format(amount);
    }
}
