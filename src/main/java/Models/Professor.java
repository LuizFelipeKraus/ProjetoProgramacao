
package Models;

import DAO.DBController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Professor extends Pessoa{
    private String formacao;
    private String Senha;    

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
    
     public void adicionarProfessor() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();
        
        dados.put("nome", getNome());
        dados.put("formacao", getFormacao());
        dados.put("email", getEmail());
        dados.put("senha", getSenha());
        dados.put("imagem", getImagem());
        
        db.conectar();
        db.insert("professor", dados);
        db.desconectar();
    }   
     
    public boolean login(String email, String senha) throws Exception {
        DBController db = new DBController();        
        db.conectar();         
        boolean check = false;
        ResultSet rset;
        rset = db.executeQuery("SELECT * FROM professor WHERE email = "+ "'"+email+ "'" +" and "
                    + "senha = "+"'"+senha+"'");
             
        try {             
            if(rset.next()) {              
                check =  true; 
            }
        }catch (SQLException ex) {
            throw new Exception("Erro ao percorrer resultados!");
            
        }           
        db.desconectar();
        
        return check;
    }
    
    public static Professor buscaProfessor(String email, String senha) throws Exception {
        DBController db = new DBController();
        ResultSet rset;
        Professor professor = new Professor();
        Professor p = new Professor();       
        db.conectar();
        rset = db.executeQuery("SELECT * FROM professor WHERE email = "+ "'"+email+ "'" +" and "
                    + "senha = "+"'"+senha+"'");
        
        try {            
                if(rset.next()){                   
                    p.setId(rset.getInt("id"));
                    p.setNome(rset.getString("nome"));
                    p.setFormacao(rset.getString("formacao"));
                    p.setEmail(rset.getString("email")); 
                    p.setSenha(rset.getString("senha"));
                    p.setImagem(rset.getString("imagem")); 
                } 
                //professor.add(p);
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao percorrer resultados!");
        }
            
        db.desconectar();
                
        return p;
    }
    
    
}
