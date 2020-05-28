package com.tindermusical;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.List;

public class NotificacaoUtil {
    private static final String M_CH_ID = "M_CH_ID";

    public static void createNotificationChannel(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(M_CH_ID,
                    "M_CH_ID", NotificationManager.IMPORTANCE_LOW);
            channel.setDescription("This is M_CH_ID");

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
    }

    private static PendingIntent getPendingIntent(Context context, Intent intent, int id){
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(new Intent(context, MainActivity.class));
        stackBuilder.addNextIntent(intent);
        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        return p;
    }

    public static void create(Context context, Intent intent, String contentTitle, String contentText, int id){
        PendingIntent p = getPendingIntent(context, intent, id);
        createNotificationChannel(context);
        Notification notification = new NotificationCompat.Builder(context, M_CH_ID)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(p).build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(context); //(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        nm.notify(id, notification);
    }

    public static void createMulti(Context context, Intent intent, String contentTitle, String contentText, int id, List<String> lines) {
        PendingIntent p = getPendingIntent(context, intent, id);
        int size = lines.size();
        NotificationCompat.InboxStyle inboxStyle = new  NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(contentTitle);
        for (String s:lines){
            inboxStyle.addLine(s);
        }
        inboxStyle.setSummaryText(contentText);

        Notification notification = new NotificationCompat.Builder(context)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(p)
                .setNumber(size)
                .setStyle(inboxStyle).build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(context); //(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        nm.notify(id, notification);

    }


        public static void cancell(Context context, int id){
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(id);

    }

    public static void cancellAll(Context context){
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancelAll();
    }
}