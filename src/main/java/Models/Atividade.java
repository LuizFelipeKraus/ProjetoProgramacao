/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controller.DBController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lukra
 */
public class Atividade {
    private int id;
    private String descricao;
    private String date;
    private String titulo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Atividade() {
    }
    
    public void adicionarUsuario() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        
        dados.put("nome", this.titulo);
        dados.put("codigo", this.descricao );
        dados.put("email", this.date);
                
        db.conectar();
        db.insert("atividade", dados);
        db.desconectar();
    }
        
    public void atualizarUsuario() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        Map<String,String> where = new HashMap<>();
        
        dados.put("nome", this.titulo);
        dados.put("codigo", this.descricao );
        dados.put("email", this.date);
        where.put("id", String.valueOf(getId()));
        
        db.conectar();
        db.update("atividade", dados, where);
        db.desconectar();
    }
    
     public static List<Atividade> buscaProdutos() throws Exception {
        DBController db = new DBController();
        ResultSet rset;
        List<Atividade> atividade = new ArrayList<>();
                
        db.conectar();
        rset = db.executeQuery("SELECT * FROM atividade");
            
        try {
            while (rset.next()) {
                Atividade p = new Atividade();
                p.setId(rset.getInt("id"));
                p.setTitulo(rset.getString("titulo"));
                p.setDescricao(rset.getString("descricao")); 
                p.setDate(rset.getString("date"));                 
                
                atividade.add(p);
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao percorrer resultados!");
        }
            
        db.desconectar();
                
        return atividade;
    }
}
