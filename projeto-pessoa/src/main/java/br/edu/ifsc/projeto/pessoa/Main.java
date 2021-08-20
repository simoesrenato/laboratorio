/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.projeto.pessoa;

import java.util.ArrayList;

/**
 *
 * @author renato
 */
public class Main {

    public static void main(String[] args) {
        //adiciona 10 pessoas no banco 

        for (int i = 0; i < 10; i++) {
            Pessoa p1 = new Pessoa();
            p1.setId(2);
            p1.setNome("Renato" + i);
            p1.setTelefone("123123" + i);
            p1.setIdade(10 + i);
            p1.setCidade("Lages" + i);
            PessoaDAO.getInstance().adicionar(p1);
        }
        String nome = "renato";

        ArrayList<Pessoa> pessoaPorNome = PessoaDAO.getInstance().buscarPorNome("renato");
        System.out.println("Pessoas com nome " + nome);
        for (Pessoa pessoa : pessoaPorNome) {
            System.out.println(pessoa);
        }

        System.out.println("====");

        int idade = 12;

        System.out.printf("Pessoas com %s anos\n", idade);

        ArrayList<Pessoa> pessoaPorIdade = PessoaDAO.getInstance().buscarPorIdade(idade);
        for (Pessoa pessoa : pessoaPorIdade) {

            System.out.println(pessoa);

        }
        //Demais operações
        System.out.println("Alterando pessoas com idade impar");
        ArrayList<Pessoa> todas = PessoaDAO.getInstance().buscarPorNome("");
        for (Pessoa pessoa : todas) {
            System.out.println(pessoa);
            if (pessoa.getIdade() % 2 == 1) {
                //adiciona a palavra impar no final do nome da pessoa
                pessoa.setNome(pessoa.getNome() + " impar");
                PessoaDAO.getInstance().alterar(pessoa);
                System.out.println("\t Alterando " + pessoa);
            }

        }

        System.out.println("Removendo pessoas com idade par");
        todas = PessoaDAO.getInstance().buscarPorNome("");
        for (Pessoa pessoa : todas) {
            System.out.println(pessoa);
            if (pessoa.getIdade() % 2 == 0) {
                //remove as pessoas que tem idades pares
                PessoaDAO.getInstance().remover(pessoa);
                System.out.println("\t Removendo " + pessoa);
            }
        }
    }
}
