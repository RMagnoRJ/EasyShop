package Classes.EasyShop.Services;

public class Cash implements PayMethod {
    
    public String geraAviso (){

        String aviso = "Desconto de 2%";
        return aviso;
    }

    public Double geraTaxa (){
        
        double descontoTaxa = 0.02;
        return descontoTaxa;
    }


    public Double payMethod(Double valor){

        double valorDoDescontro = valor * geraTaxa();
        double valorDaCompraComDesconto = valor - valorDoDescontro;
        return valorDaCompraComDesconto;

    }
}
