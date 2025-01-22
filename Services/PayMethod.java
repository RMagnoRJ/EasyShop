package Classes.EasyShop.Services;

public interface PayMethod {

    public String geraAviso ();

    public Double geraTaxa ();
    
    public Double payMethod(Double valor);
}
