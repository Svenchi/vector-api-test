package vectors.impl;

import vectors.IArray;

public class OldSchoolArray implements IArray {
    private final float[] myArray;

    public OldSchoolArray(float[] array) {
        myArray = array;
    }

    @Override
    public float[] add(IArray otherArray) throws Exception {
        int len_a = myArray.length;
        float[] otherInnerArray = otherArray.getArray();
        if (len_a != otherInnerArray.length) throw new Exception("input arrays need to be of equal length");
        float[] sumArray = new float[len_a];
        for (int i = 0; i < len_a; i++) {
            sumArray[i] = myArray[i] + otherInnerArray[i];
        }
        return sumArray;
    }


    @Override
    public float[] multiply(IArray otherArray) throws Exception {
        int len_a = myArray.length;
        float[] otherInnerArray = otherArray.getArray();
        if (len_a != otherInnerArray.length) throw new Exception("input arrays need to be of equal length");
        float[] multiplyArray = new float[len_a];
        for (int i = 0; i < len_a; i++) {
            multiplyArray[i] = myArray[i] * otherInnerArray[i];
        }
        return multiplyArray;
    }

    @Override
    public float[] getArray() {
        return myArray;
    }
}
