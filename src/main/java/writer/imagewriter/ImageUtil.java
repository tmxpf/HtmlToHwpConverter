package writer.imagewriter;

import java.util.HashSet;
import java.util.Random;

public class ImageUtil {

    private static HashSet<Long> instanceIdList = new HashSet<>();

    public static long getInstanceId() {
        Random generator = new Random();
        generator.setSeed(System.currentTimeMillis());
        long instanceId = generator.nextLong() % 10000000000L;
        int linstanceListSize = instanceIdList.size();

        if(instanceId < 0) {
            instanceId *= -1;
        }

        instanceIdList.add(instanceId);

        if(linstanceListSize == instanceIdList.size()) {
            getInstanceId();
        }

        return instanceId;
    }

    public static String getStreamName(int streamIndex, String imageExt) {
        return "Bin" + String.format("%04X", streamIndex) + "." + imageExt;
    }

}
