import java.util.Random;

public class DGEMM {

    public static void main(String[] args) {
        int n = 1024; // size of the matrices
        double[][] A = generateRandomMatrix(n, n);
        double[][] B = generateRandomMatrix(n, n);
        double[][] C = new double[n][n]; // result matrix

        for(int i = 0 ; i < 5; i++){
        long start_time = System.nanoTime();
        dgemm(n, n, n, A, B, C);
        long end_time = System.nanoTime();

        long elasped_time = end_time - start_time; 
        double elasped_time_in_ms = elasped_time/1_000_000.0;

        System.out.printf("Tempo total para dgemm = %.2f ms%n", elasped_time_in_ms);
        }
    }


    public static double[][] generateRandomMatrix(int rows, int cols) {
        Random random = new Random();
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextDouble(); // random values between 0 and 1
            }
        }
        return matrix;
    }

    public static void dgemm(int M, int N, int K, double[][] A, double[][] B, double[][] C) {
        // // Initialize C matrix with beta scaling
        // for (int i = 0; i < M; i++) {
        //     for (int j = 0; j < N; j++) {
        //         C[i][j] = beta * C[i][j];
        //     }
        // } se nao der pau é porque isso daqui nao importa

        // Perform DGEMM without cache blocking
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                double sum = 0.0;
                for (int k = 0; k < K; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] += sum;
            }
        }
    }
}

