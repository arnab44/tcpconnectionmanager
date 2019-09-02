package com.tcpmanager.Message;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class Header implements Serializable {

    int size;
    String msgDigest;
    String fileName;
}
