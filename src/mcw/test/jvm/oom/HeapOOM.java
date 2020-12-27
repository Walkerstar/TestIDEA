package mcw.test.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author mcw 2020\11\24 0024-20:26
 */
public class HeapOOM {
    static class OOMObject{ }

    public static void main(String[] args) {
        List<OOMObject> list=new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
