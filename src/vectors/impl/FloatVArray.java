package vectors.impl;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

public class FloatVArray {

    private final float[] myArray;
    static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    public FloatVArray(float[] array){
        myArray = array;
    }

    public float[] add(FloatVArray otherFloatVArray) throws Exception {
        float[] otherArray = otherFloatVArray.getArray();
        int len_a = myArray.length;
        if (len_a != otherArray.length) throw new Exception("input arrays need to be of equal length");

        float[] finalResult = new float[len_a]; //vectors need to be collected in an array after finish

        int upperBound = SPECIES.loopBound(len_a);
        int parallelInstructionsNumber = SPECIES.length();

        int i = 0;
        for (; i < upperBound; i+=parallelInstructionsNumber) {
            var v1 = FloatVector.fromArray(SPECIES, myArray, i);
            var v2 = FloatVector.fromArray(SPECIES, otherArray, i);
            var result = v1.add(v2);
            result.intoArray(finalResult, i);
        }

    // tail cleanup loop
        for (; i < len_a; i++) {
            finalResult[i] = myArray[i] + otherArray[i];
        }
        return finalResult;
    }

    public float[] multiply(FloatVArray otherFloatVArray) throws Exception {
        float[] otherArray = otherFloatVArray.getArray();
        int len_a = myArray.length;
        if (len_a != otherArray.length) throw new Exception("input arrays need to be of equal length");

        float[] finalResult = new float[len_a]; //vectors need to be collected in an array after finish

        int upperBound = SPECIES.loopBound(len_a);
        int parallelInstructionsNumber = SPECIES.length();

        int i = 0;
        for (; i < upperBound; i+=parallelInstructionsNumber) {
            var v1 = FloatVector.fromArray(SPECIES, myArray, i);
            var v2 = FloatVector.fromArray(SPECIES, otherArray, i);
            var result = v1.mul(v2);
            result.intoArray(finalResult, i);
        }

    // tail cleanup loop
        for (; i < len_a; i++) {
            finalResult[i] = myArray[i] + otherArray[i];
        }
        return finalResult;
    }

    public float[] getArray() {
        return myArray;
    }
}
