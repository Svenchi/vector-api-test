import jdk.incubator.vector.VectorSpecies;
import jdk.incubator.vector.FloatVector;

public class VectorLogic {
    static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    protected static float[] addArray(float[] a, float[] b) throws Exception {
        int len_a = a.length;
        if (len_a != b.length) throw new Exception("input arrays need to be of equal length");

        float[] finalResult = new float[len_a]; //vectors need to be collected in an array after finish

        int upperBound = SPECIES.loopBound(len_a);
        int parallelInstructionsNumber = SPECIES.length();

        int i = 0;
        for (; i < upperBound; i+=parallelInstructionsNumber) {
            var v1 = FloatVector.fromArray(SPECIES, a, i);
            var v2 = FloatVector.fromArray(SPECIES, b, i);
            var result = v1.add(v2);
            result.intoArray(finalResult, i);
        }

    // tail cleanup loop
        for (; i < len_a; i++) {
            finalResult[i] = a[i] + b[i];
        }
        return finalResult;
    }

    protected static float[] addArrayOldSchool(float[] a, float[] b) throws Exception {
        int len_a = a.length;
        if (len_a != b.length) throw new Exception("input arrays need to be of equal length");
        float[] sumArray = new float[len_a];
        for (int i = 0; i < a.length; i++) {
            sumArray[i] = a[i] + b[i];
        }
        return sumArray;
    }
}
