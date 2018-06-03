package com.piedweb.randomnetworkport.database;

public class PortDbSchema {
    public static final class PortTable {
        public static final String NAME = "port";
    }
    public static final class Columns {
        public static final String ID = "id";
        public static final String NUMBER = "number";
        public static final String PROTOCOL = "protocol";
        public static final String TCP_PROTO_VALUE = "tcp";
        public static final String UDP_PROTO_VALUE = "udp";
    }
}
