package Classes.EasyShop.Services;

import java.util.Random;
import java.util.Scanner;

public class GeneralFunctions {
    
    private Scanner inn = new Scanner (System.in);

   
    public int addRegistro(int reg){

        Integer registro;
        
        int [] numvec = new int[reg];
        String numStr = "";

        for (int i = 0; i < reg; i++){
            Random ram = new Random();
            int numRam = ram.nextInt(1,9);
            numvec[i] = numRam;
        }
        
        for (int i = 0; i < reg; i++){
            numStr = numStr + String.valueOf(numvec[i]);
        }
    
        registro = Integer.valueOf(numStr);
        return registro;
    }

    public String checkStringMinVariavel (String variavel, int limiteMin){

        String string = "";
        boolean tryAgain = true;

        while (tryAgain == true){

            System.out.print(variavel + ": " );
            string = inn.nextLine();
            if (string.length() < limiteMin){
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                "Digite no MÍNIMO " + limiteMin + " \n" + 
                                "caracteres referente ao cadastro!\n");
            } else {
                tryAgain = false;
                break;
            }
        }

        return string;
    }

    public String checkStringMaxVariavel (String variavel, int limiteMax){

        String string = "";
        boolean tryAgain = true;

        while (tryAgain == true){

            System.out.print(variavel + ": " );
            string = inn.nextLine();
            if (string.length() > limiteMax){
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                "Digite no MÁXIMO " + limiteMax + " \n" + 
                                "caracteres referente ao cadastro!\n");
            } else {
                tryAgain = false;
                break;
            }
        }

        return string;
    }

    public String checkStringMinMaxVariavel (String variavel, int limiteMin, int limiteMax){

        String string = "";
        boolean tryAgain = true;

        while (tryAgain == true){

            System.out.print(variavel + ": " );
            string = inn.nextLine();
            if (string.length() < limiteMin || string.length() > limiteMax){
                System.out.println("\n\n### ERROR ### \nOperação INVÁLIDA!\n" +
                                        variavel.toUpperCase() + " deve ser no MÍNIMO " + (limiteMin) + 
                                        "\ne o no MÁXIMO " + (limiteMax) +" !\n");
            } else {
                tryAgain = false;
                break;
            }
        }

        return string;
    }

    public boolean isNumeric(String str){
        if (str != null && str.matches("[0-9.]+")){
            return true;
        } else {
            return false;
        }
    }

    public Integer recebeIntMinMaxVariavel (String variavel, int limiteMin, int limiteMax){

        Integer numConv = -1;
        boolean testaNum = true;
        while (testaNum == true){
            System.out.print(variavel + " ");
            String teste = inn.nextLine();

            if (isNumeric(teste) == true){
                
                if (Integer.parseInt(teste) >= limiteMin && Integer.parseInt(teste) <= limiteMax){
                    numConv = Integer.parseInt(teste);
                    testaNum = false;
                } else {
                    System.out.println("\n\n    # ERROR # \n" +
                                        "Digite números entre \n" +  
                                        "      " + limiteMin + " e " + limiteMax +" \n");
                }
                
            }  else if (isNumeric(teste) == false) {
                System.out.println("\n\n    # ERROR # \n" +
                                   "Digite apenas NÚMEROS\n");
                teste = "";
            }
        }
        return numConv;

    }

    public Integer recebeIntMinMax (int limiteMin, int limiteMax){

        Integer numConv = -1;
        boolean testaNum = true;
        while (testaNum == true){
            System.out.print(" ");
            String teste = inn.nextLine();

            if (isNumeric(teste) == true){
                
                if (Integer.parseInt(teste) >= limiteMin && Integer.parseInt(teste) <= limiteMax){
                    numConv = Integer.parseInt(teste);
                    testaNum = false;
                } else {
                    System.out.println("\n\n    # ERROR # \n" +
                                        "Digite números entre \n" +  
                                        "      " + limiteMin + " e " + limiteMax +" \n");
                }
                
            }  else if (isNumeric(teste) == false) {
                System.out.println("\n\n    # ERROR # \n" +
                                   "Digite apenas NÚMEROS\n");
                teste = "";
            }
        }
        return numConv;

    }

    public Integer recebeInt (){

        Integer numConv = -1;
        boolean testaNum = true;
        while (testaNum == true){
            System.out.print(" ");
            String teste = inn.nextLine();

            if (isNumeric(teste) == true){
                numConv = Integer.parseInt(teste);
                testaNum = false;
                
            }  else if (isNumeric(teste) == false) {
                System.out.println("\n\n    # ERROR # \n" +
                                   "Digite apenas NÚMEROS\n");
                teste = "";
            }
        }
        return numConv;

    }
   

    public Double recebeDoubleVariavel (String variavel){

        Double numConvertido = -999.85979;
        boolean testaNumero = true;

        while (testaNumero == true){

            System.out.print(variavel + " ");
            String testaString = inn.nextLine();

            try {
                numConvertido = Double.parseDouble(testaString);
                if (numConvertido != -999.859799){
                    // Esse IF verificar se numConv recebeu o valor novo de TESTE e o
                    // converteu para DOUBLE. Caso contrário, ele permanecerá com 
                    // o valor inicial da variavel Double (-999.859799).
                    testaNumero = false;
                } else {
                    testaNumero = true;
                }
                
            } catch (NumberFormatException e) {
                System.out.print("\n\n      # ERROR # \n" +
                                      "Digite apenas NÚMEROS!\n"+
                                      "Ex.: 8.9, 9, 10.742, 0, 3\n");
                                   testaString = "";
            }
        }
        System.out.println();
        return numConvertido;
    }

   

    public void pause(String letreiro, char etc, int repete, int tempo) throws InterruptedException{
        
        System.out.print(letreiro);

        for (int i = 0; i < repete; i++){
            Thread.sleep(tempo);
            System.out.print(etc);
        }

    }

    public void PressToContinue(){
        System.out.print("Pressione qualquer tecla para continuar... ");
        @SuppressWarnings("unused")
        String wait = inn.nextLine();
        System.out.println();
    }

    
  

    public String checkStringLength(int tamanho) {

        boolean testaStr = true;

        System.out.print(" ");
        String recebeStr = inn.nextLine();

        while (testaStr == true){

            if (recebeStr.length() < tamanho){
                System.out.println("\n\n         # ERROR # \n" + 
                                    " \n       CPF INVÁLIDO\n" +
                                       "Digite apenas os 11 NÚMEROS!\n" +
                                       "        XXXXXXXXXxx        \n");
                System.out.print("\n> ");
                recebeStr = inn.nextLine();
            }  else if (recebeStr.length() > tamanho) {
                System.out.println("\n\n         # ERROR # \n" + 
                                    " \n       CPF INVÁLIDO\n" +
                                       "Digite apenas os 11 NÚMEROS!\n" +
                                       "        XXXXXXXXXxx        \n");
                System.out.print("\n> ");
                recebeStr = inn.nextLine();
            } else if (recebeStr.length() == tamanho){
                testaStr = false;
            }
        }
        return recebeStr;
    }


    public String checkOperador(String variavel){

        boolean testaStr = true;
        String recebeStr="";

        while (testaStr == true){

            System.out.print(variavel + " ");
            recebeStr = inn.nextLine();

            if (recebeStr == null || recebeStr == "" || recebeStr == " "){
                System.out.println("\n   #          ERRO no OPERADOR          #");
                System.out.println(  "   #           [+] [-] [=] [*]          #\n");
                testaStr = true;
            } else if (recebeStr.charAt(0) == '+'){
                testaStr = false;
            }  else if (recebeStr.charAt(0) == '-') {
                testaStr = false;
            } else if (recebeStr.charAt(0) == '*'){
                testaStr = false;
            } else if (recebeStr.charAt(0) == '='){
                testaStr = false;
            } else {
                System.out.println("\n   #          ERRO no OPERADOR          #");
                System.out.println(  "   #           [+] [-] [=] [*]          #\n");
                testaStr = true;
            }
        }
        return recebeStr;
    }
}
