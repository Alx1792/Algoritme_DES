import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        boolean text_incorrecte= true;
        boolean contra_incorrecta= true;
        String binari1="";
        String binari2="";

        while(text_incorrecte) {
            System.out.println("Introdueix text a encriptar");
            String text = scan.nextLine();
            binari1 = omplirText(textBinari(text));
            System.out.println(binari1);
            if(binari1.length()==64){
                text_incorrecte=false;
            }
        }
        while(contra_incorrecta) {
            System.out.println("Introdueix text a encriptar");
            String contra= scan.nextLine();
            binari2 = omplirText(textBinari(contra));
            if(binari2.length()==64){
                contra_incorrecta=false;
            }
        }
        //CLAU
        String bit = omplirText(binari1); //a encriptar
        String k = binari2;//clau
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

        for(int i=0;i<16;i++) {
            String rA=R.get(R.size()-1);//
            String lA =L.get(L.size()-1);//
            System.out.println(String.format("R%s = %s",i,rA));
            System.out.println(String.format("L%s = %s",i, lA));
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
                eR=eR+rA.charAt(num-1);
            }
            System.out.println(String.format("E%s = %s",i,eR));
            String keR=XOR(eR,K.get(i));
            System.out.println(String.format("K1+E(R%s) = %s",i,keR));
            ArrayList<String> B=new ArrayList<>();
            for (int j = 0; j < keR.length(); j+=6) {
                String grups=keR.substring(j,j+6);
                B.add(grups);
                System.out.println(String.format("B%s = %s",B.size(),grups));

            }
            String Bs=sBox(B);
            System.out.println(Bs);

            int[] P = {
                    16, 7, 20, 21,
                    29, 12, 28, 17,
                    1, 15, 23, 26,
                    5, 18, 31, 10,
                    2, 8, 24, 14,
                    32, 27, 3, 9,
                    19, 13, 30, 6,
                    22, 11, 4, 25
            };
            String f="";
            for(int num:P){
                f=f+Bs.charAt(num-1);

            }
            System.out.println("f ="+ f);
            String keL=XOR(lA,f);
            R.add(keL);
            System.out.println(String.format("R%s = %s",i+1,keL));
            L.add(rA);
            System.out.println(String.format("L%s = %s",i+1,rA));
        }
        String IPfinal=R.get(R.size()-1)+L.get(L.size()-1);
        System.out.println("RL16= "+IPfinal);
        int[] IP1 = {
                40, 8, 48, 16, 56, 24, 64, 32,
                39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30,
                37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28,
                35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26,
                33, 1, 41,  9, 49, 17, 57, 25
        };
        String textFin=""; //per fi
        for(int num: IP1){
            textFin=textFin+IPfinal.charAt(num-1);
        }
        System.out.println("Text encriptat: "+textFin);
    }
    public static String left_Shift(String chunk,int pos){
        String clau="";
        Integer []shifts2={3,4,5,6,7,8,10,11,12,13,14,15};
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
        int dec=Integer.parseInt(bin, 2);
        return dec;
    }
    public static String sBox(ArrayList<String> B){
        String resultat="";
        int[][] S1 = {
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        };

        int[][] S2 = {
                {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
        };

        int[][] S3 = {
                {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
        };

        int[][] S4 = {
                {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
        };

        int[][] S5 = {
                {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
        };

        int[][] S6 = {
                {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
        };

        int[][] S7 = {
                {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
        };

        int[][] S8 = {
                {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
        };

        ArrayList<int[][]> S=new ArrayList<>();
        S.add(S1);
        S.add(S2);
        S.add(S3);
        S.add(S4);
        S.add(S5);
        S.add(S6);
        S.add(S7);
        S.add(S8);

        for(int i=0;i<B.size();i++){
            String Bn= B.get(i);
            int[][]Sn=S.get(i);
            int X=binariDecimal(Bn.substring(1,Bn.length()-1));
            int Y=binariDecimal(Bn.substring(0,1)+Bn.substring(Bn.length() - 1, Bn.length()));
            resultat=resultat +decimalBinari(Integer.toString(Sn[Y][X]));

        }
        return resultat;
    }
    public static String decimalBinari(String dec){
        String binari=Integer.toBinaryString(Integer.parseInt(dec));
        if(binari.length()<4){
            while(binari.length()<4){
                binari="0"+binari;
            }
        }
        return binari;
    }
    public static String textBinari(String text) {
        String binari = "";
        for (char c : text.toCharArray()) {
            binari += String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
        }
        return binari.trim(); //
    }
    public static String omplirText(String text) {
        while(text.length()<64){
            text="0"+text;
        }
        return text; //
    }


}