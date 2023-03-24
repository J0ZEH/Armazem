package Gui;

import Controler.EstoqueControler;
import Entidades.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        EstoqueControler armazem = new EstoqueControler();
        while (true) {
            System.out.println("1 - Cadastrar Bebida");
            System.out.println("2 - Verificar Estoque");
            System.out.println("3 - Vender Bebida");
            Scanner sc = new Scanner(System.in);
            String opc = sc.next();
            switch (opc) {
                case "1":
                    //cadastrar nova bebida
                    System.out.println("Digite a marca da bebida");
                    String nome = sc.next();
                    nome = nome.toUpperCase();
                    System.out.println("1 - Agua \n2 - Refri \n3 - Alcoolica \n4 - Suco");
                    System.out.println("Digite o tipo da bebida");
                    opc = sc.next();
                    System.out.println("Digite a quantidade de bebidas:");
                    int qtd = sc.nextInt();
                    switch (opc) {
                        case "1" -> armazem.cadastrarNovaBebida(new Agua(nome, qtd));
                        case "2" -> armazem.cadastrarNovaBebida(new Refri(nome, qtd));
                        case "3" -> armazem.cadastrarNovaBebida(new Alcoolica(nome, qtd));
                        case "4" -> armazem.cadastrarNovaBebida(new Suco(nome, qtd));
                    }
                    break;
                case "2":
                    //estoque
                    System.out.println("----Estoque----");
                    System.out.println("Marca\tQuantidade");
                    System.out.println("---------------");
                    for (Bebida key : armazem.getTodasBebidas().values()) {
                        System.out.println(key.getMarca() + " - " + key.getQuantidade());
                    }
                    System.out.println("---------------");
                    break;
                case "3":
                    //vendas
                    for (Bebida key : armazem.getTodasBebidas().values()) {
                        System.out.println(key.getMarca() + " - " + key.getQuantidade());
                    }
                    System.out.println("Escolha a marca da bebida:");
                    String marca = sc.next().toUpperCase();
                    System.out.println("Digite a quantidade de bebidas pra vender:");
                    int qtdVenda = sc.nextInt();
                    armazem.venderBebida(marca, qtdVenda);
                    break;
            }
        }

    }
}
