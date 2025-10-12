import java.util.Scanner;

public class Menu {
    public static void main(String[] args){
        System.out.println("Menu Algorisme DES");
        System.out.println("1-Encriptar");
        System.out.println("2-Desencriptar (ha de haver algo encriptat)");
        System.out.println("3-Sortir");
        System.out.println("Elegeix opció");

        boolean bucle= true;

        while(bucle){
            Scanner scan=new Scanner(System.in);
            int opcio=scan.nextInt();
        switch (opcio) {
            case 1:
                Main.encriptar();
                break;
            case 2:
                System.out.println("");
                break;
            case 3:
                String binari1="38729462374237896423462934623978642739846239i4623978462934623946239874623974623789463279846237984692349826346982";
                int nChunksText= (int) (Math.ceil((double) binari1.length()/64));
                System.out.println(nChunksText);
                break;
            default:
                System.out.println("Invalid");

        }
        }


    }
}
