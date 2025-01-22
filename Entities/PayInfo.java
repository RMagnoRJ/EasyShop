package Classes.EasyShop.Entities;

import Classes.EasyShop.Enum.PaymentMode;
import Classes.EasyShop.Services.Cash;
import Classes.EasyShop.Services.Credito;
import Classes.EasyShop.Services.Debito;
import Classes.EasyShop.Services.PayMethod;
import Classes.EasyShop.Services.Pix;

public class PayInfo {
    
    private PaymentMode formaDePagamento;
    private Double valorDaCompra;
    private Double valorComDescontoOuTaxa;
    private String aviso;
    private Double descontoTaxa;

    
    public PayInfo() {
    }


    public PayInfo(Integer formaDePagamento, Double valorDaCompra) {

        this.valorDaCompra = valorDaCompra;
        PayMethod metodoDePagamento = new Pix();

        if (formaDePagamento == 1){
            this.formaDePagamento = PaymentMode.PIX;

        } else if (formaDePagamento == 2){
            this.formaDePagamento = PaymentMode.DINHEIRO;
            metodoDePagamento = new Cash();

        } else if (formaDePagamento == 3){
            this.formaDePagamento = PaymentMode.DEBITO;
            metodoDePagamento = new Debito();

        } else if (formaDePagamento == 4){
            this.formaDePagamento = PaymentMode.CREDITO;
            metodoDePagamento = new Credito();
            
        }

        this.valorComDescontoOuTaxa = metodoDePagamento.payMethod(getValorDaCompra());
        this.aviso = metodoDePagamento.geraAviso();
        this.descontoTaxa = valorDaCompra * metodoDePagamento.geraTaxa();

    }


    public PaymentMode getFormaDePagamento() {
        return formaDePagamento;
    }

    public Double getValorDaCompra() {
        return valorDaCompra;
    }

    public String getAviso() {
        return aviso;
    }

    public Double getDescontoTaxa() {
        return descontoTaxa;
    }

    public Double getValorComDescontoOuTaxa() {
        return valorComDescontoOuTaxa;
    }
    
}
