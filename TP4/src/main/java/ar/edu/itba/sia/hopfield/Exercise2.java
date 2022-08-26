package ar.edu.itba.sia.hopfield;

public class Exercise2 {
    public static void main (String[] args){
        Hopfield hopfield = new Hopfield();
        int[] j = new int[]{
                1 , 1 ,1 , 1 ,1,
                -1,-1 ,-1, 1, -1,
                -1, -1, -1,1, -1,
                1 , -1 ,-1,1 ,-1,
                1 ,1 ,1 ,-1, -1
        };
        int[] m = new int[]{
                1 ,-1 ,-1 ,-1 ,1,
                1,  1 ,-1, 1, 1,
                1, -1, 1 ,-1, 1,
                1 ,-1 ,-1 ,-1 ,1,
                1 ,-1 ,-1 ,-1, 1
        };
        int[] c = new int[]{
                1 ,1 ,1 ,1 ,1,
                1,-1 ,-1, -1, -1,
                1, -1, -1 ,-1, -1,
                1 , -1 ,-1 ,-1 ,-1,
                1 ,1 ,1 ,1, 1
        };
        int[] a = new int[]{
                -1 ,-1 ,1,-1 ,-1,
                -1, 1 ,-1, 1, -1,
                -1, 1,  1 ,1, -1,
                -1, 1 ,-1 ,1 ,-1,
                -1 ,1 ,-1 ,1, -1
        };
        hopfield.train(j);
        hopfield.train(m);
        hopfield.train(c);
        hopfield.train(a);
        int[] jAltered = new int[]{
                1 ,1 ,1 ,1 ,1,
                -1,-1 ,-1, 1, -1,
                -1, -1, -1 ,1, -1,
                -1 , -1 ,-1 ,1 ,1,
                1 ,1 ,1 ,-1, -1
        };
        int[] mAltered = new int[]{
                1 ,-1 ,-1 ,-1 ,1,
                1,  1 ,-1, 1, 1,
                1, 1, -1 ,-1, 1,
                1 ,-1 ,-1 ,-1 ,1,
                1 ,-1 ,-1 ,-1, 1
        };
        int[] cAltered = new int[]{
                1 ,1 ,-1 ,1 ,1,
                1,1 ,-1, -1, -1,
                1, -1, -1 ,-1, -1,
                -1 , -1 ,-1 ,-1 ,-1,
                1 ,1 ,1 ,1, 1
        };
        int[] aAltered = new int[]{
                -1 ,-1 ,1,-1 ,-1,
                -1, 1 ,-1, 1, -1,
                -1, 1,  1 ,1, -1,
                -1, 1 ,-1 ,1 ,-1,
                1 ,-1 ,-1 ,-1, 1
        };
        hopfield.run(jAltered);
        hopfield.run(mAltered);
        hopfield.run(cAltered);
        hopfield.run(aAltered);

        hopfield.getWeightsMatrix();
        hopfield.vis();

        int[] noisy = new int[]{
                1 ,1 ,1 ,1 ,1,
                1,1 ,1,1, 1,
                1, 1, 1 ,1, 1,
                1 , 1 ,1 ,1 ,1,
                1 ,1 ,1 ,1, 1
        };
        hopfield.run(noisy);
        int[] noisy2 = new int[]{
                1 ,1 ,1 ,1 ,1,
                1,1 ,1,1, 1,
                1, 1, 1 ,1, 1,
                1 , 1 ,1 ,1 ,1,
                1 ,1 ,1 ,-1, 1
        };
        hopfield.run(noisy2);
        int[] i = new int[]{
                1 ,  1 ,1 ,1 , 1,
                -1, -1 ,1,-1, -1,
                -1, -1, 1,-1, -1,
                -1, -1 ,1,-1 ,-1,
                1 ,  1 ,1 ,1,  1
        };
        hopfield.run(i);
    }
}
