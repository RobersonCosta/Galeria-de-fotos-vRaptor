/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import br.com.caelum.vraptor.Controller;
//import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.Validator;
import database.GaleriaDAO;
import database.ImagemDAO;
import database.UsuarioDAO;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import modelos.Galeria;
import modelos.Imagem;
import org.apache.commons.io.IOUtils;
import sessao.UsuarioSessao;

/**
 *
 * @author iapereira
 */
@Controller
public class GaleriaController {

    @Inject
    private UsuarioSessao usuarioSessao;

    @Inject
    private Result result;
    @Inject
    private GaleriaDAO galeriaDAO;
    @Inject
    private Validator validator;

    private String DIR_DOWNLOAD = "/home/aluno/Área de Trabalho/sistema/src/main/webapp/WEB-INF/arquivos/";

    public void listar() throws SQLException {
        if (usuarioSessao.isLogged()) {
            result.include("vetGaleria", galeriaDAO.selectPorIdUsuario(usuarioSessao.getUsuario().getId()));
            result.include("user", usuarioSessao.getUsuario().getLogin());
        }

        //result.include("vetGaleria",  new ArrayList());
    }

    public void tela_inserir() throws SQLException {
        if (usuarioSessao.isLogged()) {
            result.include("usuario", new UsuarioDAO().selectPorId(usuarioSessao.getUsuario().getId()));
        }
    }

    public void inserir(@Valid @NotNull Galeria galeria) throws SQLException {
        validator.onErrorForwardTo(this).tela_inserir();
        galeriaDAO.insert(galeria);
        result.redirectTo(GaleriaController.class).listar();
    }

    @br.com.caelum.vraptor.Path("/galeria/tela_editar/{id}")
    public void tela_editar(int id) throws SQLException {
        result.include("galeria", galeriaDAO.selectPorId(id));
    }

    public void editar(@Valid @NotNull Galeria galeria) throws SQLException {
        validator.onErrorForwardTo(this).tela_inserir();
        galeriaDAO.update(galeria);
        result.redirectTo(GaleriaController.class).listar();
    }

    @br.com.caelum.vraptor.Path("/galeria/deletar/{id}")
    public void deletar(int id) throws SQLException, IOException {
        ArrayList<Imagem> imagens = new ImagemDAO().selectImagemPorIdGaleria(id);
        if (imagens.size() > 0) {
            for (int i = 0; i < imagens.size(); i++) {
                String arquivo = imagens.get(i).getArquivo().getName().trim();
                File file = new File(DIR_DOWNLOAD + arquivo);
                FileInputStream in = new FileInputStream(DIR_DOWNLOAD + arquivo);
                in.close();
                file.delete();
            }
        }

        galeriaDAO.delete(id);
        result.redirectTo(GaleriaController.class).listar();
    }

    @br.com.caelum.vraptor.Path("/galeria/visualiza/{id}")
    public void visualiza(int id) throws SQLException {
        result.include("galeria", galeriaDAO.selectPorId(id));
        result.include("vetImagem", new ImagemDAO().selectImagemPorIdGaleria(id));
    }

    @br.com.caelum.vraptor.Path("/galeria/compactado/{id}")
    public Download compactado(int id) throws SQLException, IOException {
        List<Imagem> imagens = new ImagemDAO().selectImagemPorIdGaleria(id);
        if (imagens == null || imagens.isEmpty()) {
            result.redirectTo(GaleriaController.class).visualiza(id);
            return null;
        }

        List<Path> paths = new ArrayList<>();
        for (Imagem imagem : imagens) {
            java.nio.file.Path imagemPath = new File(DIR_DOWNLOAD + imagem.getArquivo().getName()).toPath();
            paths.add((Path) imagemPath);
        }

        byte buffer[] = new byte[2048];
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ZipOutputStream zos = new ZipOutputStream(baos)) {
            zos.setMethod(ZipOutputStream.DEFLATED);
            zos.setLevel(5);
            for (Path path : paths) {

                try (FileInputStream fis = new FileInputStream(path.toFile());
                        BufferedInputStream bis = new BufferedInputStream(fis)) {
                    String pathFileName = path.getFileName().toString();
                    zos.putNextEntry(new ZipEntry(pathFileName));

                    int bytesRead;
                    while ((bytesRead = bis.read(buffer)) != -1) {
                        zos.write(buffer, 0, bytesRead);
                    }

                    zos.closeEntry();
                    zos.flush();
                } catch (IOException e) {
                    result.redirectTo(GaleriaController.class).visualiza(id);
                    return null;
                }
            }
            zos.finish();
            byte[] zip = baos.toByteArray();

            Download download = new ByteArrayDownload(zip, "application/zip", "allImages.zip");
            return download;
        } catch (IOException e) {
            result.redirectTo(GaleriaController.class).visualiza(id);
            return null;
        }
    }
}
