import java.io.BufferedWriter;
import java.io.FileWriter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String textEnc="src/resources/encripta.txt";


        try(BufferedWriter bw=new BufferedWriter(new FileWriter(textEnc))){
        
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}