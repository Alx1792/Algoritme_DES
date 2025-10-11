import java.util.ArrayList;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Pas 1
        //CLAU
        String bit = "0000000100100011010001010110011110001001101010111100110111101111"; //a encriptar
       // String L=bit.substring(0,32);
        //String R=bit.substring(32,64);

        String k = "0001001100110100010101110111100110011011101111001101111111110001";//clau
        System.out.println("Clau principal "+k);
        int[]PC1 = {
                57, 49, 41, 33, 25, 17, 9,
                1, 58, 50, 42, 34, 26, 18,
                10, 2, 59, 51, 43, 35, 27,
                19, 11, 3, 60, 52, 44, 36,
                63, 55, 47, 39, 31, 23, 15,
                7, 62, 54, 46, 38, 30, 22,
                14, 6, 61, 53, 45, 37, 29,
                21, 13, 5, 28, 20, 12, 4
        };

        String kplus="";

        for (int num : PC1){
            kplus= kplus + k.charAt(num-1);
        }
        System.out.println("Clau permutada: "+kplus);
        String c0=kplus.substring(0,28);
        System.out.println("C0 ="+c0);
        String d0=kplus.substring(28,56);
        System.out.println("D0 =" +d0);
        //System.out.println(left_Shift("1100001100110010101010111111",10));

        ArrayList<String> C= new ArrayList<>();
        ArrayList<String> D= new ArrayList<>();
        C.add(c0);
        D.add(d0);

        for(int i=1;i<17;i++){
            C.add(left_Shift(C.get(C.size()-1), i));
            D.add(left_Shift(D.get(D.size()-1), i));
            System.out.println(String.format("C%s = %s",i, C.get(C.size()-1)));
            System.out.println(String.format("D%s = %s",i, D.get(D.size()-1)));
        }
        ArrayList<String> K= new ArrayList<>();
        int[] PC2 = {
                14, 17, 11, 24, 1, 5,
                3, 28, 15, 6, 21, 10,
                23, 19, 12, 4, 26, 8,
                16, 7, 27, 20, 13, 2,
                41, 52, 31, 37, 47, 55,
                30, 40, 51, 45, 33, 48,
                44, 49, 39, 56, 34, 53,
                46, 42, 50, 36, 29, 32
        };
        for (int i=1;i<C.size();i++){
            String kT= C.get(i)+ D.get(i);
            String kR="";
            for (int num : PC2){
                kR=kR +kT.charAt(num-1);
            }
            System.out.println(String.format("K%s = %s",i,kR));
            K.add(kR);
        }

        //Pas 2
        //TEXT


        String bitIP="";
        int[] IP = {
                58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17, 9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7
        };
        for (int num : IP){
            bitIP=bitIP+bit.charAt(num-1);
        }
        String L0=bitIP.substring(0,32);
        String R0=bitIP.substring(32,64);

        ArrayList<String> L =new ArrayList<>();
        ArrayList<String> R =new ArrayList<>();
        L.add(L0);
        R.add(R0);

        for(int i=0;i<1;i++) {
            String rT=R.get(R.size()-1);//
            int[] E = {
                    32, 1, 2, 3, 4, 5,
                    4, 5, 6, 7, 8, 9,
                    8, 9, 10, 11, 12, 13,
                    12, 13, 14, 15, 16, 17,
                    16, 17, 18, 19, 20, 21,
                    20, 21, 22, 23, 24, 25,
                    24, 25, 26, 27, 28, 29,
                    28, 29, 30, 31, 32, 1
            };

            String eR = "";
            for (int num : E) {
                eR=eR+rT.charAt(num-1);
            }
            System.out.println("E(R0)= "+eR);
            String keR=XOR(eR,K.get(i));
            System.out.println("XOR= "+keR);
            ArrayList<String> B=new ArrayList<>();
            for (int j = 0; j < keR.length(); j+=6) {
                String grups=keR.substring(j,j+6);
                B.add(grups);
                System.out.println(String.format("B%s = %s",B.size(),grups));

            }

        }
        int[][] S1 = {
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}

        };

    }

    public static String left_Shift(String chunk,int pos){
        String clau="";
        Integer []shifts2={3,4,5,7,8,10,11,12,13,14,15};
        ArrayList<Integer> shift=new ArrayList<>(Arrays.asList(shifts2));
        for(int i=0;i<chunk.length();i++){
            if(shift.contains(pos)) {
                if (i == chunk.length() - 1) {
                    clau = clau + chunk.charAt(1);
                } else if (i == chunk.length() - 2) {
                    clau = clau + chunk.charAt(0);
                } else {
                    clau = clau + chunk.charAt(i + 2);
                }
            }
            else{
                if(i==chunk.length()-1){
                    clau= clau+chunk.charAt(0);
                }
                else{
                    clau=clau+chunk.charAt(i+1);
                }
            }
        }
        return clau;
    }
    public static String XOR(String chunk,String clau) {
        String xor="";

        for(int i=0;i<chunk.length();i++){
            if(chunk.charAt(i)==clau.charAt(i)){
                xor=xor+"0";
            }
            else{
                xor=xor+"1";
            }

        }
        return xor;
    }
    public static int binariDecimal(String bin) {
        Integer dec=Integer.parseInt(bin, 2);
        return dec;
    }


}