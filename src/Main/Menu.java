import java.util.Scanner;

public class Menu {
    public static void main(String[] args){


        boolean bucle= true;

        while(bucle){
            Scanner scan=new Scanner(System.in);
            System.out.println("Menu Algorisme DES");
            System.out.println("1-Encriptar");
            System.out.println("2-Desencriptar (ha de haver algo encriptat)");
            System.out.println("3-Sortir");
            System.out.println("Elegeix opcio: ");
            int opcio=scan.nextInt();
        switch (opcio) {
            case 1:
                Main.encriptar();
                break;
            case 2:
                Main.desencriptar();
                break;
            case 3:
               System.exit(1);
                break;
            default:
                System.out.println("Invalid");
        }
        }


    }
}
