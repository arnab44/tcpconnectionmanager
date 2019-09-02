package com.tcpmanager.Message;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Builder
public class Message implements Comparable<Message> , Serializable {
    Header header;
    String messageDigest;
    String payLoad;

    public int compareTo(Message m) {
        double t = this.getPayLoad().length();
        double u = m.getPayLoad().length();
        return t < u ? -1 : t == u ? 0 : 1;
    }
}
