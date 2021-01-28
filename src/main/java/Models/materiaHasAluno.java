
package Models;

import DAO.DBController;
import java.util.HashMap;
import java.util.Map;

public class materiaHasAluno {
     private String idMateria;
     private String idAluno;

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    
     
     public void adicionarMateriaHasAluno() throws Exception {
        DBController db = new DBController();
        Map<String,String> dados = new HashMap<>();        
        dados.put("materia_id", getIdMateria());            
        dados.put("aluno_id",  getIdAluno());        
        db.conectar();
        db.insert("materia_has_aluno", dados);
        db.desconectar();
    } 

    
    
}
