
package Models;

import DAO.DBController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Aluno extends Pessoa {     

    public void adicionarAluno() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        
        dados.put("nome", getNome());       
        dados.put("email", getEmail());       
        dados.put("imagem", getImagem());
        
        db.conectar();
        db.insert("aluno", dados);
        db.desconectar();
    }
        
    public void atualizarAluno() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        Map<String,String> where = new HashMap<>();
        
        dados.put("nome", getNome());        
        dados.put("email", getEmail());        
        dados.put("imagem", getImagem());
        where.put("id", String.valueOf(getId()));
        
        db.conectar();
        db.update("aluno", dados, where);
        db.desconectar();
    }
    
     public static List<Aluno> buscarAluno() throws Exception {
        DBController db = new DBController();
        ResultSet rset;
        List<Aluno> aluno = new ArrayList<>();
                
        db.conectar();
        rset = db.executeQuery("SELECT * FROM aluno");
            
        try {
            while (rset.next()) {
                Aluno a = new Aluno();
                a.setId(rset.getInt("id"));
                a.setNome(rset.getString("nome"));                
                a.setEmail(rset.getString("email"));                 
                a.setImagem(rset.getString("imagem")); 
                
                aluno.add(a);
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao percorrer resultados!");
        }
            
        db.desconectar();
                
        return aluno;
    }    
    
}
