package Classes.EasyShop.Services;

public class Credito implements PayMethod {

    public String geraAviso (){

        String aviso = "Taxa de 5%";
        return aviso;
    }

    public Double geraTaxa (){
        
        double descontoTaxa = 0.05;
        return descontoTaxa;
    }


    public Double payMethod(Double valor){

        double valorDoDescontro = valor * geraTaxa();
        double valorDaCompraComDesconto = valor + valorDoDescontro;
        return valorDaCompraComDesconto;

    }

}
