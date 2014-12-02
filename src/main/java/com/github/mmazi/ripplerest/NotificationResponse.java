package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationResponse extends RippleResponse<Notification> {

    private Notification notification;

    public NotificationResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public Notification getNotification() {
        return notification;
    }

    @Override
    public Notification getValue() {
        return getNotification();
    }
}
