
package Models;

import DAO.DBController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Materia {
    private String nome;
    private String duracao;
    private String horario;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
    
    public void adicionarMateria() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        
        dados.put("nome", getNome());
        dados.put("duracao",getDuracao());
        dados.put("horario", getHorario());
        
        db.conectar();
        db.insert("materia", dados);
        db.desconectar();
    }   
            
    public void atualizarMateria() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        Map<String,String> where = new HashMap<>();
        
        dados.put("nome", getNome());
        dados.put("duracao",getDuracao());
        dados.put("horario", getHorario());
        where.put("id", String.valueOf(getId()));
        
        db.conectar();
        db.update("materia", dados, where);
        db.desconectar();
    }
    
     public static List<Materia> buscarMateria() throws Exception {
        DBController db = new DBController();
        ResultSet rset;
        List<Materia> materia = new ArrayList<>();
                
        db.conectar();
        rset = db.executeQuery("SELECT * FROM materia");
            
        try {
            while (rset.next()) {
                Materia m = new Materia();
                m.setId(rset.getInt("id"));                
                m.setNome(rset.getString("nome"));
                m.setHorario(rset.getString("horario")); 
                m.setDuracao(rset.getString("duracao"));                 
                
                materia.add(m);
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao percorrer resultados!");
        }
            
        db.desconectar();
                
        return materia;
    }
     
     @Override
     public String toString(){
         return this.getId() + ": " + this.getNome();
     }
}
