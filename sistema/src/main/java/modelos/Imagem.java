/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.File;
import javax.validation.constraints.NotNull;

/**
 *
 * @author iapereira
 */
public class Imagem {
    
    
    private int id;    
    @NotNull(message = "{imagem.descricao.vazio}")
    private String descricao;
    
    @NotNull(message = "{imagem.arquivo.vazio}")
    private File arquivo;
        
    private Galeria galeria;

    public Imagem(int id, String descricao, File arquivo, Galeria galeria) {
        this.id = id;
        this.descricao = descricao;
        this.arquivo = arquivo;
        this.galeria = galeria;
    }
    public Imagem(int id, String descricao, Galeria galeria) {
        this.id = id;
        this.descricao = descricao;
        this.galeria = galeria;
    }

    
    public Imagem() {
    }
       
    public Galeria getGaleria() {
        return galeria;
    }

    public void setGaleria(Galeria galeria) {
        this.galeria = galeria;
    }
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
