package Classes.EasyShop.Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    

    private IdNota notaFiscal;
    private Cpf cpf;
    private LocalDateTime dataDaCompra;
    private DateTimeFormatter showDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private ListaDeProdutos compras;
    private PayInfo payment;
    
    public Invoice() {
    }

    public Invoice( IdNota notaFiscal, 
                    Cpf cpf, 
                    LocalDateTime dataDaCompra, 
                    ListaDeProdutos compras, 
                    PayInfo payment ) {

        this.notaFiscal = notaFiscal;
        this.cpf = cpf;
        this.dataDaCompra = dataDaCompra;
        this.compras = compras;
        this.payment = payment;
 
    }

    public IdNota getNotaFiscal() {
        return notaFiscal;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public LocalDateTime getDataDaCompra() {
        return dataDaCompra;
    }

    public ListaDeProdutos getCompras() {
        return compras;
    }

    public PayInfo getPayment() {
        return payment;
    }

    public DateTimeFormatter getShowDataHora() {
        return showDataHora;
    }


    

 

    public void geraNota(){

        System.out.println("\n         ----------------------------------        ");
        System.out.println(  "          Nota Fiscal nº " + notaFiscal.getNumeroDaNota());
        System.out.println("         ----------------------------------        ");
        System.out.println(  "          Data da compra: " + getDataDaCompra().format(showDataHora));
        System.out.println(  "          CPF: " + cpf.getCpf());
        System.out.println("         ----------------------------------       ");
        System.out.println("                Item         Valor (R$)");
        String dir = "[", esq = "]"; 
        for (int i = 0 ; i < compras.getProdutos().size(); i++){
            System.out.printf("                %s%d%s           %.2f%n", dir, (i+1), esq, compras.getProdutos().get(i));
        }
        System.out.println("         ----------------------------------        ");
        System.out.println(  "          Quantidade de itens: " + getCompras().getProdutos().size());
        System.out.println(  "          Total R$ " + String.format("%.2f", getCompras().geratotal()));
        System.out.println("         ----------------------------------        ");
        System.out.println(  "          Forma de pagamento: " + getPayment().getFormaDePagamento());
        System.out.printf("          %s : R$ %.2f%n", getPayment().getAviso(), getPayment().getDescontoTaxa());
        System.out.println(  "          Total a pagar: R$ " + String.format("%.2f", getPayment().getValorComDescontoOuTaxa()));
        System.out.println("         ----------------------------------        \n");
        
    }



}
