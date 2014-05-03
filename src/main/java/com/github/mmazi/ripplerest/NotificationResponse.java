package com.github.mmazi.ripplerest;

public class NotificationResponse extends RippleResponse<Notification> {

    private Notification notification;

    public Notification getNotification() {
        return notification;
    }

    @Override
    public Notification getValue() {
        return getNotification();
    }
}
