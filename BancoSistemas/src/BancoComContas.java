import java.util.ArrayList;
import java.util.Scanner;

public class BancoComContas {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        ArrayList<Conta> contas = new ArrayList<>();

        boolean executando = true;
        int proximoNumero = 1;

        while(executando){

            System.out.println("\n=========== Banco ===========");
            System.out.println("1. Criar conta");
            System.out.println("2. Listar contas");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Transferir");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){

                case 1:
                    System.out.print("Nome do titular: ");
                    String nome = scanner.nextLine();

                    Conta novaConta = new Conta(proximoNumero, nome);
                    contas.add(novaConta);

                    System.out.print("Conta criada com sucesso. Conta número: " + proximoNumero);
                    proximoNumero++;
                    break;

                case 2:
                  if(contas.isEmpty()){
                      System.out.println("Nenhuma conta cadastrada");
                      break;
                  }
                  else{
                      System.out.println();
                      for(Conta conta : contas){
                          System.out.printf("Conta %d | Titular %s | Saldo $%.2f%n",
                                            conta.getNumeroConta(), conta.getTitular(), conta.getSaldo());
                      }
                  }
                  break;

                case 3:
                    System.out.print("Número da conta: ");
                    int numDep = scanner.nextInt();

                    Conta contaDep = buscarConta(contas, numDep);

                    if(contaDep != null){
                        System.out.print("Valor do depósito: ");
                        double valor = scanner.nextDouble();
                        contaDep.depositar(valor);
                        System.out.println("Depósito realizado com sucesso.");
                        System.out.println("\nSaldo após depósito: ");
                        System.out.printf("Conta (%s) - Saldo: $%.2f%n", contaDep.getTitular(), contaDep.getSaldo());
                    }
                    else{
                        System.out.println("Conta não encontrada");
                    }
                    break;

                case 4:
                    System.out.print("Número da conta: ");
                    int numSaq = scanner.nextInt();

                    Conta contaSaq = buscarConta(contas, numSaq);

                    if(contaSaq != null){
                        System.out.print("Valor do saque: ");
                        double valor = scanner.nextDouble();
                        contaSaq.sacar(valor);
                    }
                    else{
                        System.out.print("Conta não encontrada");
                    }
                    break;

                case 5:
                    System.out.print("Conta de origem: ");
                    int origemNum = scanner.nextInt();

                    System.out.print("Conta de destino: ");
                    int destinoNum = scanner.nextInt();

                    Conta origem = buscarConta(contas, origemNum);
                    Conta destino = buscarConta(contas, destinoNum);

                    if(origem == destino){
                        System.out.println("Não é possivel transferir para a mesma conta.");
                        break;
                    }

                    if(origem == null || destino == null){
                        System.out.println("Conta de destino ou origem não encontrada");
                        break;
                    }
                    else{
                        System.out.print("Valor da transferência: ");
                        double valor = scanner.nextDouble();

                        // realiza a transferência
                        boolean transferido = origem.transferir(valor, destino);

                        if(transferido){
                            System.out.println("\nSaldo após a transferência: ");
                            System.out.printf("\nConta de origem (%s) - Saldo: %.2f%n", origem.getTitular(), origem.getSaldo());
                            System.out.printf("Conta de destino (%s) - Saldo: %.2f%n", destino.getTitular(), destino.getSaldo());
                        }
                        break;
                    }

                case 6:
                    executando = false;
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }

        scanner.close();
    }

    // método auxiliar
    public static Conta buscarConta(ArrayList<Conta> contas, int numeroConta){
        for(Conta conta : contas){
            if(conta.getNumeroConta() == numeroConta){
                return conta;
            }
        }
        return null;
    }
}
