package com.packtpub.mmj.booking.domain.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BookingMessageChannels {

    public final static String BOOKING_ORDER_OUTPUT = "bookingOrderOutput";

    @Output(BOOKING_ORDER_OUTPUT)
    MessageChannel bookingOrderOutput();
}
