public class Conta {

    private int numeroConta;
    private String titular;
    private double saldo;

    public Conta(int numeroConta, String titular){

        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = 0.0;
    }

    public int getNumeroConta(){
        return numeroConta;
    }

    public String getTitular(){
        return titular;
    }

    public double getSaldo(){
        return saldo;
    }

    public void depositar(double valor){
        if(valor > 0){
            saldo += valor;
        }
        else{
            System.out.println("Valor inválido");
        }
    }

    public boolean sacar(double valor){
        if(valor > 0 && valor <= saldo){
            saldo -= valor;
            System.out.println("Saque realizado com sucesso");
            return true;
        }
        else{
            System.out.println("Saldo insuficiente ou valor inválido");
            return false;
        }
    }

    public boolean transferir(double valor, Conta destino){
        if(destino == null){
            System.out.println("Conta de destino inválida");
            return false;
        }

        else if(valor <= 0){
            System.out.println("Valor inválido");
            return false;
        }

        else if(valor > saldo){
            System.out.println("Saldo insuficiente");
            return false;
        }
        else {
            this.saldo -= valor;
            destino.depositar(valor);

            System.out.println("Transferência realizada com sucesso.");
            return true;
        }
    }
}
