package vectors;

import jdk.incubator.vector.FloatVector;

public interface IArray {
    public float[] add(IArray otherFloatVArray) throws Exception;


    public float[] multiply(IArray otherArray) throws Exception;

    public float[] getArray();
}
