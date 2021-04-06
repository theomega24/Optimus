package me.notom3ga.optimus.util.version;

public class ProtocolVersion {
    public static String toString(int version) {
        switch (version) {
            case 754:
                return "1.16.4/1.16.5";
            default:
                return "Unknown: (" + version + ")";
        }
    }
}
