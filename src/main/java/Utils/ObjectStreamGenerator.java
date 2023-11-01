package Utils;

import java.io.Serializable;

public class ObjectStreamGenerator  implements Serializable{

    private String ip, payload;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
