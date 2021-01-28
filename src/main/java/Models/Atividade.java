
package Models;

import DAO.DBController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Atividade {
    private int id;
    private String descricao;
    private String date;
    private String titulo;
    private String materia_id;

    public int getId() {
        return id;
    }

    public String getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(String materia_id) {
        this.materia_id = materia_id;
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
    
    public void adicionarAtividade() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        
        dados.put("titulo", this.titulo);
        dados.put("date", this.date);
        dados.put("descricao", this.descricao );
        dados.put("materia_id", this.materia_id );
        
                
        db.conectar();
        db.insert("atividade", dados);
        db.desconectar();
    }
        
    public void atualizarAtividade() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        Map<String,String> where = new HashMap<>();
        
        dados.put("titulo", this.titulo);
        dados.put("date", this.date);
        dados.put("descricao", this.descricao);
        dados.put("materia_id", this.materia_id);        
        where.put("id", String.valueOf(getId()));
        
        db.conectar();
        db.update("atividade", dados, where);
        db.desconectar();
    }
    
     public static List<Atividade> buscarAtividade() throws Exception {
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
                p.setDate(rset.getString("date"));
                p.setDescricao(rset.getString("descricao")); 
                p.setMateria_id(rset.getString("materia_id"));
                                
                
                atividade.add(p);
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao percorrer resultados!");
        }
            
        db.desconectar();
                
        return atividade;
    }
     
     public static List<Atividade> buscarAtividade(int num) throws Exception {
        DBController db = new DBController();
        ResultSet rset;
        List<Atividade> atividade = new ArrayList<>();
                
        db.conectar();
        rset = db.executeQuery("SELECT * FROM atividade Where materia_id = " + num);
            
        try {
            while (rset.next()) {
                Atividade p = new Atividade();
                p.setId(rset.getInt("id"));
                p.setTitulo(rset.getString("titulo"));
                p.setDate(rset.getString("date"));
                p.setDescricao(rset.getString("descricao")); 
                p.setMateria_id(rset.getString("materia_id"));
                                
                
                atividade.add(p);
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao percorrer resultados!");
        }
            
        db.desconectar();
                
        return atividade;
    }
}
