```java
package com.analyzefarm.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PriceWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        // لینک تبلیغات
        Intent adIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://analyzefarm.ir/"));
        PendingIntent adPendingIntent = PendingIntent.getActivity(context, 0, adIntent, PendingIntent.FLAG_IMMUTABLE);
        views.setOnClickPendingIntent(R.id.adText, adPendingIntent);

        // لینک کل ویجت
        Intent widgetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://analyzefarm.ir/"));
        PendingIntent widgetPendingIntent = PendingIntent.getActivity(context, 1, widgetIntent, PendingIntent.FLAG_IMMUTABLE);
        views.setOnClickPendingIntent(R.id.widgetTitle, widgetPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);

        // دریافت قیمت‌ها
        new Thread(() -> {
            try {
                URL url = new URL("https://analyzefarm.ir/get-prices.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                connection.disconnect();

                JSONObject json = new JSONObject(response.toString());
                String chicken = json.getString("chicken");
                String chick = json.getString("chick");

                views.setTextViewText(R.id.chickenPrice, "🐔 " + chicken + " ریال");
                views.setTextViewText(R.id.chickPrice, "🐣 " + chick + " ریال");

                appWidgetManager.updateAppWidget(appWidgetId, views);
            } catch (Exception e) {
                views.setTextViewText(R.id.chickenPrice, "—");
                views.setTextViewText(R.id.chickPrice, "—");
                appWidgetManager.updateAppWidget(appWidgetId, views);
            }
        }).start();
    }
}
