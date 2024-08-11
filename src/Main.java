import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
//        disregard  benchmarking, i bet the garbage collector jumps in between will have to fix this
        int[] sizes = new int[]{1000, 10_000, 100_000,1_000_000, 10_000_000, 50_000_000, 100_000_000, 150_000_000};
        for (int i : sizes){
            generate_arrays_and_sum(i);
        }
    }

    private static float[] generate_random_float_array(int size){
        var rand = new Random();
        float[] vals = new float[size];
        for (int i = 0; i < size; i++) {
             vals[i] = rand.nextFloat();
        }
        return vals;
    };

    private static void generate_arrays_and_sum(int array_len) throws Exception {
        System.out.printf("running test for len: %,d%n", array_len);
        float[] vec_a = generate_random_float_array(array_len);
        float[] vec_b = generate_random_float_array(array_len);

        long startTime = System.currentTimeMillis();
        var c = VectorLogic.addArray(vec_a, vec_b);
        long midTime = System.currentTimeMillis();
        var d = VectorLogic.addArrayOldSchool(vec_a, vec_b);
        long endTime = System.currentTimeMillis();
        System.out.printf("SIMD way: %d%nold fashioned: %d%n%n", (midTime-startTime), (endTime-midTime));
    }
}