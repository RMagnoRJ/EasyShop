package Classes.EasyShop.Services;

public class Debito implements PayMethod {
    
    public String geraAviso (){

        String aviso = "Taxa de 1%";
        return aviso;
    }

    public Double geraTaxa (){
        
        double descontoTaxa = 0.01;
        return descontoTaxa;
    }


    public Double payMethod(Double valor){

        double valorDoDescontro = valor * geraTaxa();
        double valorDaCompraComDesconto = valor + valorDoDescontro;
        return valorDaCompraComDesconto;

    }

}
