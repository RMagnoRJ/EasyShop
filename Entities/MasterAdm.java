package Classes.EasyShop.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Classes.EasyShop.Services.GeneralFunctions;

public class MasterAdm {
    
    // Lista de notas fiscais para arquivo e consulta

    private GeneralFunctions function = new GeneralFunctions();
    private List <Invoice> arquivoGeral = new ArrayList<>();

    public MasterAdm() {
    }

    public MasterAdm(List<Invoice> arquivoGeral) {
        this.arquivoGeral = arquivoGeral;
    }

    public List<Invoice> getArquivoGeral() {
        return arquivoGeral;
    }

    public void addNota(Invoice notaFiscal){
        arquivoGeral.add(notaFiscal);
    }

    public void arquivoGeralDeNotas(){

        int proxima;

        for (int i = 0; i < arquivoGeral.size(); i++){

            arquivoGeral.get(i).geraNota();

            System.out.println("\n         |  [ 1 ] SAIR  |  PRÓXIMA [ 2 ]  |");
            System.out.print("\n         >>> ");
            proxima = function.recebeIntMinMax(1, 2);
            if (proxima == 1) {
                i = arquivoGeral.size();
            }
            System.out.println("\n"); 
        }
    }

    public void fechaCaixa (){

        double vendasDoDia = 0.0;
        DateTimeFormatter novaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\n------------------------------------------");

        for (int i = 0; i < arquivoGeral.size(); i++){
            
            System.out.println(      " DATA: " + arquivoGeral.get(i).getDataDaCompra().format(novaData));
            System.out.printf(" VALOR (R$) %.2f%n", arquivoGeral.get(i).getPayment().getValorComDescontoOuTaxa());
            System.out.println(      " FORMA DE PAGAMENTO: " + arquivoGeral.get(i).getPayment().getFormaDePagamento());
            System.out.println("------------------------------------------");
            vendasDoDia = vendasDoDia + arquivoGeral.get(i).getPayment().getValorComDescontoOuTaxa();
        }

        
        System.out.printf("            TOTAL R$ %.2f%n", vendasDoDia);
        System.out.println("------------------------------------------\n");
        
    }

    public void buscaPorCpf (String cpf){

        boolean localizado = false;

        for (int i = 0; i < arquivoGeral.size(); i++){
    
            if (arquivoGeral.get(i).getCpf().getCpf().equals(cpf)) {
                arquivoGeral.get(i).geraNota();
                localizado = true;
            } else if (arquivoGeral.get(i).getCpf().getCpf().charAt(0) == cpf.charAt(0)){
                if(arquivoGeral.get(i).getCpf().getCpf().charAt(1) == cpf.charAt(1)){
                    if(arquivoGeral.get(i).getCpf().getCpf().charAt(2) == cpf.charAt(2)){
                        if(arquivoGeral.get(i).getCpf().getCpf().charAt(3) == cpf.charAt(3)){
                            if(arquivoGeral.get(i).getCpf().getCpf().charAt(4) == cpf.charAt(4)){
                                if(arquivoGeral.get(i).getCpf().getCpf().charAt(5) == cpf.charAt(5)){
                                    if(arquivoGeral.get(i).getCpf().getCpf().charAt(6) == cpf.charAt(6)){
                                        if(arquivoGeral.get(i).getCpf().getCpf().charAt(7) == cpf.charAt(7)){
                                            if(arquivoGeral.get(i).getCpf().getCpf().charAt(8) == cpf.charAt(8)){
                                                arquivoGeral.get(i).geraNota();
                                                localizado = true;
                                                function.PressToContinue();
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

    }

    public void buscaPorData (LocalDate buscaData){

        boolean localizado = false;

        for (int i = 0; i < arquivoGeral.size(); i++){

            LocalDateTime data = arquivoGeral.get(i).getDataDaCompra();
            LocalDate novaData = data.toLocalDate();

            if (novaData.equals(buscaData)) {
                arquivoGeral.get(i).geraNota();
                localizado = true;

                System.out.println("\n         |  [ 1 ] SAIR  |  PRÓXIMA [ 2 ]  |");
                System.out.print("\n         >>> ");
                int proxima = function.recebeIntMinMax(1, 2);

                if (proxima == 1) {
                    i = arquivoGeral.size();
                    localizado = true;
                }
            } 

        }

        if (localizado == false){
            System.out.println("\n# REGISTRO NÃO LOCALIZADO #\n");
        }

    }

    public void buscaPorValor (double buscaValor){

        
        boolean localizado = false;
        double epsilon = 0.001;

        for (int i = 0; i < arquivoGeral.size(); i++){

            double valorMinimo = buscaValor - epsilon;
            double valorRegistrado = arquivoGeral.get(i).getPayment().getValorComDescontoOuTaxa();
            double valorMaximo = valorRegistrado + epsilon;

            if (valorRegistrado >= valorMinimo && valorRegistrado <= valorMaximo) {
                arquivoGeral.get(i).geraNota();
                System.out.println("\n         |  [ 1 ] SAIR  |  PRÓXIMA [ 2 ]  |");
                System.out.print("\n         >>> ");
                int proxima = function.recebeIntMinMax(1, 2);

                if (proxima == 1) {
                    i = arquivoGeral.size();
                    localizado = true;
                }

                System.out.println("\n"); 
            }
            valorRegistrado = 0.0; 
        }

        if (localizado == false){
            System.out.println("\n# REGISTRO NÃO LOCALIZADO #\n");
        }

    }
    
}
