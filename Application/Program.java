package Classes.EasyShop.Application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Classes.EasyShop.Entities.Cpf;
import Classes.EasyShop.Entities.IdNota;
import Classes.EasyShop.Entities.Invoice;
import Classes.EasyShop.Entities.ListaDeProdutos;
import Classes.EasyShop.Entities.MasterAdm;
import Classes.EasyShop.Entities.PayInfo;
import Classes.EasyShop.Enum.PaymentMode;
import Classes.EasyShop.Services.GeneralFunctions;





public class Program {
    
    @SuppressWarnings("unlikely-arg-type")
    public static void main(String[] args) {
        
        // Recapitulação seção 16 - Interfaces

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(  "                    EASY CALC v 1.0                  ");
        System.out.println(  "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n\n");
        Scanner inn = new Scanner (System.in);
        MasterAdm arquivoGeral = new MasterAdm();
        GeneralFunctions function = new GeneralFunctions();
        String operador;
        Double compra;
        boolean shop;
        int mostraCpf;
        String recebeCpf="";
        boolean purchase;
        boolean program = true;
        int menu;
        while (program == true){
            menu=0;
            System.out.println("\n       **********");
            System.out.println("       |  MENU  |");
            System.out.println("       **********\n");
            System.out.print("\n[1] Registrar compras\n" +
            "[2] Buscar Nota Fiscal\n" +
            "[3] Arquivo Geral de Nota Fiscal\n" +
            "[4] Fechamento\n" +
            "[5] Encerrar Programa\n\n");
            menu = function.recebeIntMinMaxVariavel(">", 1, 5);

            switch (menu) {
                
                case 1:
                // ROTINA DE VENDA
                
                purchase = true;

                while (purchase == true){
                    ListaDeProdutos listaDeCompras = new ListaDeProdutos();
                    IdNota numeroDaNota;
                    Cpf cadastraCpf = new Cpf("não especificado");
                    PayInfo payment;
                    
                    System.out.println("\n\t*-*-*");
                    System.out.println(  "\t CPF ");
                    System.out.println(  "\t*-*-*\n");
                    System.out.print("Cadastra CPF na nota:\n" +
                    "[1] SIM\n"+
                    "[2] NÃO\n");
                    mostraCpf = function.recebeIntMinMaxVariavel("\n> ", 1, 2);

                    if (mostraCpf == 1){
                    System.out.print("\nDigite o CPF (XXXXXXXXXxx):");
                    recebeCpf = function.checkStringLength(11);
                    cadastraCpf = new Cpf(recebeCpf);
                    }

                    System.out.println("\n--------------------------------------------------\n");

                    System.out.println("\n                    *-*-*-*-*                     ");
                    System.out.println(  "                     COMPRAS                      ");
                    System.out.println(  "                    *-*-*-*-*                     \n");
                    double multiplicador;
                    int quantidadeDeItens=0;
                    shop = true;
                    while (shop == true){
                                                                
                        compra = function.recebeDoubleVariavel("                                        $ ");
                        
                        operador = function.checkOperador("[ + ]     [ - ]     [ * ]     [ = ]   >>> ");

                        if (operador.charAt(0) == '+'){
                            quantidadeDeItens = quantidadeDeItens + 1;
                            listaDeCompras.addProduto(compra);
                            System.out.println("\n\t  $ " + String.format("%.2f", listaDeCompras.geratotal()));

                        } else if (operador.charAt(0) == '-'){
                            listaDeCompras.removeProduto();
                            quantidadeDeItens = quantidadeDeItens - 1;

                        } else if (operador.charAt(0) == '*'){
                            System.out.print(  "\n                              [ * ]   >>> ");
                            multiplicador = function.recebeInt();

                            for (int i = 1; i <= multiplicador; i++){
                                listaDeCompras.addProduto(compra);
                            }
                            compra = compra * multiplicador;
                            //int resultado = (int) ( 0 + multiplicador);
                            //quantidadeDeItens = quantidadeDeItens + resultado;
                            System.out.printf("\n                              [ = ]     $  %.2f %n", compra);
                            
                            System.out.println("\n\t  $ " + String.format("%.2f", listaDeCompras.geratotal()) + "\n");

                            operador = function.checkOperador("[ + ]                         [ = ]   >>> ");

                            if (operador.charAt(0) == '='){
                                shop = false;
                            } else {
                                System.out.println();
                                shop = true;
                            }

                        } else if (operador.charAt(0) == '='){
                            quantidadeDeItens = quantidadeDeItens + 1;
                            listaDeCompras.addProduto(compra);
                            System.out.println("\n\t  $ " + String.format("%.2f", listaDeCompras.geratotal()) + "\n");
                            shop = false;
                        }
                    }
                    System.out.println("\n--------------------------------------------------\n");
                    System.out.println("             <<< TOTAL >>>              R$ " + String.format("%.2f", listaDeCompras.geratotal()));
                    System.out.println("\n--------------------------------------------------\n");
                    System.out.println("\nForma de pagamento: \n");

                    int i = 1;
                    for (PaymentMode mode : PaymentMode.values()){
                        System.out.println("["+i+"] " + mode);
                        i++;
                    }
                    System.out.print("\n> ");
                    int formaDePagamento = function.recebeIntMinMax(1, 4);
                    payment = new PayInfo(formaDePagamento, listaDeCompras.geratotal());
                    numeroDaNota = new IdNota(7);
                    LocalDateTime dataHoraDaCompra = LocalDateTime.now();

                    Invoice notaFiscalDaCompra = new Invoice(numeroDaNota, 
                    cadastraCpf, dataHoraDaCompra, listaDeCompras, payment);
                    arquivoGeral.addNota(notaFiscalDaCompra);
                    
                    notaFiscalDaCompra.geraNota();
                    
                    int continuaCaixa = 0;
                    System.out.print("\n\n   " ); function.PressToContinue();

                    System.out.println("\n--------------------------------------------------\n");
                    System.out.println("\n\t  *-*-*-*-*");
                    System.out.println(  "\t    CAIXA  ");
                    System.out.println(  "\t  *-*-*-*-*\n");
                    System.out.println("[1] Nova compra");
                    System.out.println("[2] Voltar ao MENU");
                    continuaCaixa = function.recebeIntMinMaxVariavel("> ", 1, 2);
                    if ( continuaCaixa == 1){
                        purchase = true;
                        compra = 0.0;
                        recebeCpf="";
                    } else {
                        purchase = false;
                        break;
                    }
                }
                break;

                case 2:
                    // BUSCA DE NOTA FISCAL PELO CPF, ou, DATA
                    int busca=0;
                    while (busca < 3){
                        System.out.println("\n\t*-*-*-*");
                        System.out.println(  "\t BUSCA ");
                        System.out.println(  "\t*-*-*-*\n");
                        System.out.print("Realizar busca por:\n" +
                        "[1] CPF\n"+
                        "[2] Data\n"+
                        "[3] Valor da nota\n"+
                        "[4] Voltar ao menu\n");
                        busca = function.recebeIntMinMaxVariavel("> ", 1, 4);
                        boolean localizado = false;

                        if (busca == 1){
                            System.out.print("\nDigite o CPF (XXXXXXXXX-xx): ");
                            String buscaCpf = function.checkStringLength(11);
    
                            for (int i = 0; i < arquivoGeral.getArquivoGeral().size(); i++){
    
                                if (arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().equals(buscaCpf)) {
                                    arquivoGeral.getArquivoGeral().get(i).geraNota();
                                    localizado = true;
                                } else if (arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(0) == buscaCpf.charAt(0)){
                                    if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(1) == buscaCpf.charAt(1)){
                                        if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(2) == buscaCpf.charAt(2)){
                                            if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(3) == buscaCpf.charAt(3)){
                                                if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(4) == buscaCpf.charAt(4)){
                                                    if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(5) == buscaCpf.charAt(5)){
                                                        if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(6) == buscaCpf.charAt(6)){
                                                            if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(7) == buscaCpf.charAt(7)){
                                                                if(arquivoGeral.getArquivoGeral().get(i).getCpf().getCpf().charAt(8) == buscaCpf.charAt(8)){
                                                                    arquivoGeral.getArquivoGeral().get(i).geraNota();
                                                                    localizado = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } 
                            }
                            if (localizado == false){
                                System.out.println("\n# CPF NÃO LOCALIZADO #\n");
                            }
                        } else if (busca == 2) {
                            System.out.println("\nPreencha os campos abaixo: \n");
                            System.out.print("DIA: ");
                            int dia = function.recebeIntMinMax(1, 31);
                            System.out.print("\nMES: ");
                            int mes = function.recebeIntMinMax(1, 12);
                            System.out.print("\nANO: ");
                            int ano = function.recebeInt();
                            LocalDate buscaData = LocalDate.of(ano, mes, dia);
                            localizado = false;
                            for (int i = 0; i < arquivoGeral.getArquivoGeral().size(); i++){
    
                                if (arquivoGeral.getArquivoGeral().get(i).getDataDaCompra().equals(buscaData)) {
                                    arquivoGeral.getArquivoGeral().get(i).geraNota();
                                    localizado = true;
                                } 
                            }
                            if (localizado == false){
                                System.out.println("\n# REGISTRO NÃO LOCALIZADO #\n");
                            }
                        } else if (busca == 3){
                            System.out.print("\nDigite o valor da nota: R$ ");
                            double valor = function.recebeDoubleVariavel("R$ ");
                            localizado = false;
                            for (int i = 0; i < arquivoGeral.getArquivoGeral().size(); i++){
    
                                if (arquivoGeral.getArquivoGeral().get(i).getPayment().getValorComDescontoOuTaxa() == valor) {
                                    arquivoGeral.getArquivoGeral().get(i).geraNota();
                                    System.out.println("\n|  [ 1 ] ENCERRAR  |  PRÓXIMA [ 2 ]  |");
                                    System.out.print("[  ] ");
                                    int proxima = function.recebeIntMinMax(1, 2);
                                    if (proxima == 1) {
                                        i = arquivoGeral.getArquivoGeral().size();
                                        localizado = true;
                                    }
                                    System.out.println("\n"); 
                                } 
                            }
                            if (localizado == false){
                                System.out.println("\n# REGISTRO NÃO LOCALIZADO #\n");
                            }
                        } 
                    }
                    break;

                case 3:
                    // ARQUIVO GERAL DE NOTAS
                    System.out.println("\n\t        *-*-*-*-*-*-*-*");
                    System.out.println(  "\t         ARQUIVO GERAL ");
                    System.out.println(  "\t        *-*-*-*-*-*-*-*\n");
                    int proxima;
                    for (int i = 0; i < arquivoGeral.getArquivoGeral().size(); i++){
    
                        arquivoGeral.getArquivoGeral().get(i).geraNota();

                        System.out.println("\n         |  [ 1 ] SAIR  |  PRÓXIMA [ 2 ]  |");
                        System.out.print("\n         >>> ");
                        proxima = function.recebeIntMinMax(1, 2);
                        if (proxima == 1) {
                            i = arquivoGeral.getArquivoGeral().size();
                        }
                        System.out.println("\n"); 
                    }
                    break;

                case 4:
                    // FECHAMENTO DE VENDAS
                    System.out.println("\n\t*-*-*-*-*-*-*-*");
                    System.out.println(  "\t FECHAMENTO ");
                    System.out.println(  "\t*-*-*-*-*-*-*-*\n");
                    double vendasDoDia=0.0;
                    DateTimeFormatter novaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.println("\n---------------------------------");
                    for (int i = 0; i < arquivoGeral.getArquivoGeral().size(); i++){

                        System.out.print("| DATA: ");
                        arquivoGeral.getArquivoGeral().get(i).getDataDaCompra().format(novaData);
                        System.out.print(" | Valor (R$) ");
                        arquivoGeral.getArquivoGeral().get(i).getPayment().getValorComDescontoOuTaxa();
                        System.out.println(" |");
                        vendasDoDia = vendasDoDia + arquivoGeral.getArquivoGeral().get(i).getPayment().getValorComDescontoOuTaxa();
                    }
                    System.out.println("---------------------------------");
                    System.out.printf("Total recebido R$ %.2f", vendasDoDia);
                    System.out.println("---------------------------------\n");
                    function.PressToContinue();
                    break;

                case 5:
                    program = false;
                    break;

                default:
                    System.out.println("# ERROR #");
                    break;
            }
        }
        System.out.println("\n\t*-*-*-*-*-*-*");
        System.out.println(  "\t  ENCERRADO  ");
        System.out.println(  "\t*-*-*-*-*-*-*\n");
        inn.close();
    }
}
