package Controler;

import Entidades.*;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class EstoqueControler {
    Map<String, Bebida> todasBebidas;

    public EstoqueControler() throws IOException {
        todasBebidas = importTodasBebidas();
    }

    private Map<String, Bebida> importTodasBebidas() throws IOException {
        Map<String, Bebida> aux = new TreeMap<>();
        BufferedReader br = null;
        br = new BufferedReader(new FileReader("arquivos/bebidas.txt"));
        String linha = "";
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            if (dados[0].equals("Agua")) {
                Agua agua = new Agua(dados[1], Integer.parseInt(dados[2]));
                aux.put(agua.getMarca(), agua);
            } else if (dados[0].equals("Refrigerante")) {
                Refri refrigerante = new Refri(dados[1], Integer.parseInt(dados[2]));
                aux.put(refrigerante.getMarca(), refrigerante);
            } else if (dados[0].equals("Suco")) {
                Suco suco = new Suco(dados[1], Integer.parseInt(dados[2]));
                aux.put(suco.getMarca(), suco);
            } else if (dados[0].equals("Alcoolica")) {
                Alcoolica aloolica = new Alcoolica(dados[1], Integer.parseInt(dados[2]));
                aux.put(aloolica.getMarca(), aloolica);
            }
        }
        return aux;
    }

    public void cadastrarNovaBebida(Bebida bebida) throws IOException {
        if (todasBebidas.containsKey(bebida.getMarca())) {
            int qntd = todasBebidas.get(bebida.getMarca()).getQuantidade();
            bebida.incrementa(qntd);
            todasBebidas.put(bebida.getMarca().toUpperCase(), bebida);
        } else {
            todasBebidas.put(bebida.getMarca().toUpperCase(), bebida);
        }
        recadastro();
    }

    public boolean venderBebida(String marca, int qntd) throws IOException {
        if (todasBebidas.containsKey(marca)) {
            Bebida bebida = todasBebidas.get(marca);
            if (bebida.getQuantidade() >= qntd) {
                bebida.decrementa(qntd);
                System.out.println("Venda realizada com sucesso!");
                todasBebidas.put(marca, bebida);
                recadastro();
                return true;
            } else {
                System.out.println("Não há bebidas suficientes!");
            }
        }
        return false;
    }

    private void recadastro() throws IOException {
        File file = new File("arquivos/bebidas.txt");
        BufferedWriter bww = null;
        try {
            bww = new BufferedWriter(new FileWriter(file));
            bww.write(""); // limpa o arquivo
        } catch (IOException e) {
            e.printStackTrace();
        }
        //cadastra novamente
        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(file, true));
        for (Bebida b : todasBebidas.values()) {
            try {
                bw.write(b.getClass().getSimpleName() + ";" + b.getMarca()
                        + ";" + b.getQuantidade() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bw.close();
    }

    public Map<String, Bebida> getTodasBebidas() {
        return todasBebidas;
    }
}
