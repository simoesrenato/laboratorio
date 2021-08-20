/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.projeto.pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author renato
 */
public class PessoaDAO {

    //como nossa classe é a única e sabemos que ela vai ser chamada nesta execução, não há problema em já instanciar a classe.
    private static final PessoaDAO instance = new PessoaDAO();

    public static PessoaDAO getInstance() {
        return instance;
    }

    public void adicionar(Pessoa p) {
        try {
            Connection conn = MySqlConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into pessoa values (null,?,?,?,?)");
            ps.setString(1, p.getNome());
            ps.setString(2, p.getTelefone());
            ps.setInt(3, p.getIdade());
            ps.setString(4, p.getCidade());
            ps.execute();
            conn.close();
            System.out.printf("Pessoa %s inserida com sucesso\n", p.getNome());
        } catch (SQLException ex) {
            System.out.printf("Erro ao inserir pessoa %s\n%s\n\n", p.getNome(), ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void remover(Pessoa p) {
        try {
            Connection conn = MySqlConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("delete from pessoa where id=?");
            ps.setInt(1, p.getId());
            ps.execute();
            conn.close();
            System.out.printf("Pessoa %s - %s removida com sucesso\n", p.getId(), p.getNome());
        } catch (SQLException ex) {
            System.out.printf("Erro ao alterar remover %s\n%s\n\n", p.getNome(), ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void alterar(Pessoa p) {
        try {
            Connection conn = MySqlConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("update pessoa set nome=?, telefone=?, idade=?, cidade=? where id=?");
            ps.setString(1, p.getNome());
            ps.setString(2, p.getTelefone());
            ps.setInt(3, p.getIdade());
            ps.setString(4, p.getCidade());
            ps.setInt(5, p.getId());

            ps.execute();
            conn.close();
            System.out.printf("Pessoa %s - %s atualizada com sucesso\n", p.getId(), p.getNome());
        } catch (SQLException ex) {
            System.out.printf("Erro ao alterar pessoa %s\n%s\n\n", p.getNome(), ex.getMessage());
            ex.printStackTrace();
        }

    }

    public ArrayList<Pessoa> buscarPorNome(String nome) {
        ArrayList<Pessoa> resultado = new ArrayList<>();
        try {

            Connection conn = MySqlConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from pessoa where nome like '%" + nome + "%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setTelefone(rs.getString(3));
                p.setIdade(rs.getInt(4));
                p.setCidade(rs.getString(5));
                resultado.add(p);
            }

            conn.close();

        } catch (SQLException ex) {
            System.out.printf("Erro ao buscar pessoa por nome %s\n%s\n\n", nome, ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;
    }

    public ArrayList<Pessoa> buscarPorIdade(int idade) {
        ArrayList<Pessoa> resultado = new ArrayList<>();
        try {

            Connection conn = MySqlConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from pessoa where idade=?");
            ps.setInt(1, idade);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setTelefone(rs.getString(3));
                p.setIdade(rs.getInt(4));
                p.setCidade(rs.getString(5));
                resultado.add(p);
            }

            conn.close();

        } catch (SQLException ex) {
            System.out.printf("Erro ao buscar pessoa por idade %s\n%s\n\n", idade, ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;
    }

    
}
