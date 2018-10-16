/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.ImagemDAO;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import javax.inject.Inject;
import br.com.caelum.vraptor.validator.Validator;
import database.GaleriaDAO;
import database.ImagemDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import modelos.Imagem;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author iapereira
 */
@Controller
public class ImagemController {

    @Inject
    private Result result;
    @Inject
    private ImagemDAO imagemDAO;
    @Inject
    private GaleriaDAO galeriaDAO;
    @Inject
    private Validator validator;

    private String DIR_DOWNLOAD = "/home/aluno/Área de Trabalho/sistema/src/main/webapp/WEB-INF/arquivos/";

    @Path("/imagem/tela_inserir/{id}")
    public void tela_inserir(int id) throws SQLException {
        result.include("galeria", galeriaDAO.selectPorId(id));
    }

    public void inserir(Imagem imagem, UploadedFile file) throws SQLException, FileNotFoundException, IOException {
        //validator.onErrorForwardTo(this).tela_inserir(imagem.getGaleria().getId());
        
        
        String extensao = file.getFileName().substring(file.getFileName().indexOf("."), file.getFileName().length());
        System.out.println(file.getContentType());
        long time = System.currentTimeMillis();
        String novo_nome = String.format("%08x%05x", time / 1000, time);
        File f = new File(DIR_DOWNLOAD + novo_nome + extensao);

        IOUtils.copyLarge(file.getFile(), new FileOutputStream(f));

        Imagem imagemAux = new Imagem(imagem.getId(), imagem.getDescricao(), f, imagem.getGaleria());
        imagemDAO.insert(imagemAux);

        result.redirectTo(GaleriaController.class).visualiza(imagemAux.getGaleria().getId());
    }

    @Path("/imagem/deletar/{id}")
    public void deletar(int id) throws SQLException, IOException {
        
        String arquivo = imagemDAO.selectPorId(id).getArquivo().getName().trim();
        File file = new File(DIR_DOWNLOAD + arquivo);   
        FileInputStream in = new FileInputStream(DIR_DOWNLOAD + arquivo);
        in.close();
        file.delete();                           
        int idGaleria = imagemDAO.selectPorId(id).getGaleria().getId();
        imagemDAO.delete(id);               
        result.redirectTo(GaleriaController.class).visualiza(idGaleria);          
    }

    @Path("/imagem/baixar/{arquivo}")
    public File baixar(String arquivo) throws SQLException {
        return new File(DIR_DOWNLOAD + arquivo);
    }

}
