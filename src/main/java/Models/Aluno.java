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

public class Aluno extends Pessoa {
    private String codigo;      
    
    public void adicionarUsuario() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        
        dados.put("nome", getNome());
        dados.put("codigo", this.codigo );
        dados.put("email", getEmail());
        dados.put("senha", getSenha());
        dados.put("imagem", getImagem());
        
        db.conectar();
        db.insert("aluno", dados);
        db.desconectar();
    }
        
    public void atualizarUsuario() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        Map<String,String> where = new HashMap<>();
        
        dados.put("nome", getNome());
        dados.put("codigo", this.codigo );
        dados.put("email", getEmail());
        dados.put("senha", getSenha());
        dados.put("imagem", getImagem());
        where.put("id", String.valueOf(getId()));
        
        db.conectar();
        db.update("aluno", dados, where);
        db.desconectar();
    }
    
     public static List<Aluno> buscaProdutos() throws Exception {
        DBController db = new DBController();
        ResultSet rset;
        List<Aluno> aluno = new ArrayList<>();
                
        db.conectar();
        rset = db.executeQuery("SELECT * FROM aluno");
            
        try {
            while (rset.next()) {
                Aluno p = new Aluno();
                p.setId(rset.getInt("id"));
                p.setNome(rset.getString("nome"));
                p.setEmail(rset.getString("email")); 
                p.setSenha(rset.getString("senha"));
                p.setImagem(rset.getString("imagem")); 
                
                aluno.add(p);
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao percorrer resultados!");
        }
            
        db.desconectar();
                
        return aluno;
    }    
    
}
