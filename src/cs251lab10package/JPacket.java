package cs251lab10package;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class JPacket implements Serializable {
    JMessage core;
    Timestamp timestamp;
    public JPacket(JMessage input) {
        timestamp = new Timestamp((new Date()).getTime());
        core = input;
    }
}
