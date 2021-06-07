public class SuperPow {
    // a^(int[] b) = a^(b[0]) * a^(10 b[1]) * a^(100 b[2]) * ... = a^(b[0]) * (a^10)^(b[1]) * (a^100)^(b[2]) * ...
    private static final int M = 1337;
    
    public int superPow(int a, int[] b) {
        a %= M;
        int res = 1;
        for (int i = b.length - 1; i >= 0; i--) {// b.length
            res = res * pow(a, b[i]) % M;// time: log b[i]
            a = pow(a, 10);// time: log 10
        }
        return res;
    }
    
    private int pow(int a, int b) {
        int res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res = res * a % M;
            }
            b >>= 1;
            a = a * a % M;
        }
        return res;
    }
}
