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
public class Professor extends Pessoa{
    private String codigo;      

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public void adicionarUsuario() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        
        dados.put("nome", getNome());
        dados.put("codigo", this.codigo );
        dados.put("email", getEmail());
        dados.put("senha", getSenha());
        dados.put("imagem", getImagem());
        
        db.conectar();
        db.insert("professor", dados);
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
        db.update("professor", dados, where);
        db.desconectar();
    }
    
     public static List<Professor> buscaProdutos() throws Exception {
        DBController db = new DBController();
        ResultSet rset;
        List<Professor> professor = new ArrayList<>();
                
        db.conectar();
        rset = db.executeQuery("SELECT * FROM professor");
            
        try {
            while (rset.next()) {
                Professor p = new Professor();
                p.setId(rset.getInt("id"));
                p.setNome(rset.getString("nome"));
                p.setEmail(rset.getString("email")); 
                p.setSenha(rset.getString("senha"));
                p.setImagem(rset.getString("imagem")); 
                
                professor.add(p);
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao percorrer resultados!");
        }
            
        db.desconectar();
                
        return professor;
    }
}
