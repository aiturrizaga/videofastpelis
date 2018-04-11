package com.sf.controller;

import com.sf.dao.UserDao;
import com.sf.model.User;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    //Variables de usuario
    User usuario = new User();
    private String usu, pwd;
    private String passAct, passNew;
    private String styleColor;
    private List<User> lstUsers;
    private List<User> lstUsersFilter;
    private List<User> lstUsersprov;
    private List<User> lstUsersProvFilter;
    private boolean btnAdd = false;
    private boolean verifica = true;

    //Variables de videos
    private User videoSelected;
    private User catgSelected;
    private User historySelected;
    private User listaSelected;
    private User userProvSelected;
    private User userAdmSelected;
    private List<User> lstMovies;
    private List<User> lstBsqMovies;
    private List<User> lstMoviesXCat;
    private List<User> lstCategoria;
    private List<User> lstMoviesSimilar;
    private List<User> lstProxEstrenos;
    private List<User> lstHistorial;
    private List<User> lstMiLista;
    private List<User> lstCbCat;
    private List<User> lstCbGen;
    private String busqueda;
    private boolean msg = false;

    @PostConstruct
    public void init() {
        try {
            setStyleColor(usuario.getStylePer());
            listarMovies();
            listaCbCategorias();
            listaCbGeneros();
            listarUsersActivos();
            listaCategorias();
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*-------------- Metodos para Videos --------------*/
    
    //Lista de categorias con estado = 'A'
    public void listaCategorias() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            lstCategoria = dao.listarCategorias();
        } catch (Exception e) {
            throw e;
        }
    }

    //Lista de videos por categorias
    public void listaVideosXCat() throws SQLException {
        UserDao dao;
        try {
            dao = new UserDao();
            lstMoviesXCat = dao.listarVideosXCat(catgSelected.getCodCat());
        } catch (SQLException e) {
            throw e;
        }
    }

    //Listar videos buscados
    public void listarBusquedaVideos() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            lstBsqMovies = dao.listarBusquedaVideo(busqueda);
            FacesContext.getCurrentInstance().getExternalContext().redirect("search.xhtml");
        } catch (Exception e) {
            throw e;
        }
    }

    //Listar todas las peliculas
    public void listarMovies() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            lstMovies = dao.listarVideos();
        } catch (Exception e) {
            throw e;
        }
    }

    //Listar peliculas similares a las que estas viendo
    public void listarMovieSimilar() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            lstMoviesSimilar = dao.listarVideoSimilar(videoSelected.getCodCatVid(), videoSelected.getCodVid());
        } catch (Exception e) {
            throw e;
        }
    }

    //Listar los proximos estrenos de peliculas
    public void listarProxEstrenos() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            lstProxEstrenos = dao.listarProximosEstrenos();
        } catch (Exception e) {
            throw e;
        }
    }

    //Agregar video al historial
    public void agregarHistorial() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.agregarHistorial(usuario.getCodPer(), videoSelected.getCodVid());
        } catch (Exception e) {
            throw e;
        }
    }

    //Eliminar historial uno por uno
    public void eliminarHistorial() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.eliminarHistorial(historySelected.getCodHis(), historySelected.getCodPerHis());
            listarHistorial();
        } catch (Exception e) {
            throw e;
        }
    }

    //Eliminar todo el historial del usuario
    public void eliminarTodoHistorial() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.eliminarTodoHistorial(usuario.getCodPer());
            listarHistorial();
        } catch (Exception e) {
            throw e;
        }
    }

    //Listar el historial
    public void listarHistorial() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            lstHistorial = dao.listarHistorial(usuario.getCodPer());
            FacesContext.getCurrentInstance().getExternalContext().redirect("historial.xhtml");
        } catch (SQLException e) {
            throw e;
        }
    }

    //Agregar a mi lista
    public void agregarAmiLista() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.agregarAmiLista(usuario.getCodPer(), videoSelected.getCodVid());
            verificaMiLista();
        } catch (Exception e) {
            throw e;
        }
    }

    //Listar mi lista de videos
    public void listarMiLista() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            lstMiLista = dao.listarMiLista(usuario.getCodPer());
            FacesContext.getCurrentInstance().getExternalContext().redirect("lista.xhtml");
        } catch (SQLException e) {
            throw e;
        }
    }

    //Eliminar un video de mi lista
    public void eliminarMiLista() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.eliminarMiLista(listaSelected.getCodLis(), listaSelected.getCodPerLis());
            listarMiLista();
        } catch (Exception e) {
            throw e;
        }
    }

    //Enlazar un nuevo video
    public void enlazarVideo() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.enlazarVideo(usuario);
            listarMovies();
            limpiarVariableVideos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUBIDO", "Exitosamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No se enlazo el video"));
        }
    }

    //Autocompletar lo que escribe el usuario
    public List<String> autocompleteTextVid(String query) throws SQLException {
        UserDao dao = new UserDao();
        return dao.queryAutoCompleteVideo(query);
    }

    //Listar combobox de categorias
    public void listaCbCategorias() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            setLstCbCat(dao.listaCbCategorias());
        } catch (Exception e) {
            throw e;
        }
    }

    //Listar combobox de generos
    public void listaCbGeneros() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            setLstCbGen(dao.listaCbGeneros());
        } catch (Exception e) {
            throw e;
        }
    }

    //Limpiar variables
    public void limpiarVariableVideos() {
        usuario.setNomVid(null);
        usuario.setYearVid(null);
        usuario.setDuraVid(null);
        usuario.setDesVid(null);
        usuario.setUbiVid(null);
        usuario.setPortVid(null);
        usuario.setCodCatVid(null);
        usuario.setCodGenVid(null);
    }

    //Verifica si el video existe en mi lista
    public void verificaMiLista() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            verifica = dao.verificarMiLista(usuario.getCodPer(), videoSelected.getCodVid());
        } catch (Exception e) {
            throw e;
        }
    }

    /*-------------- Metodos para Usuario --------------*/
    public void inicioSession() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            usuario = dao.acceder(usu, pwd);
            if (usuario != null) {
                if (usuario.getEstPer().equals("I")) {
                    msg = true;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tu usuario esta deshabilitado"));
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Videofast/faces/view/content/home.xhtml");
                    listarUsersActivosProv();
                    countCantUsers();
                }
            } else {
                msg = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario o Contraseña incorrecto"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de conexion"));
            throw e;
        }
    }

    public void logoutSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Videofast/");
    }

    public void securitySession() throws IOException {
        User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (us == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Videofast/");
        }
    }

    public void securityLogin() throws IOException {
        User us = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (us != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Videofast/faces/view/content/home.xhtml");
        }
    }

    //Actualizar usuario personal
    public void updateUser() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.updateUser(usuario);
            listarUsersActivos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DATOS ACTUALIZADOS", "Exitosamente."));
        } catch (Exception e) {
            throw e;
        }
    }

    //Crear un nuevo usuario
    public void addUser() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.addUser(usuario);
            listarUsersActivos();
            listarUsersActivosProv();
            countCantUsers();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "USUARIO CREADO", "Exitosamente."));
        } catch (SQLException e) {
            throw e;
        }
    }

    //Actualizar los usuarios del proveedor
    public void updateOtherUserProv() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.updateOtherUser(userProvSelected);
            listarUsersActivosProv();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "USUARIO ACTUALIZADO", "Exitosamente."));
        } catch (Exception e) {
            throw e;
        }
    }

    //Actualizar los usuarios del administrador
    public void updateOtherUserAdm() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.updateOtherUser(userAdmSelected);
            listarUsersActivos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "USUARIO ACTUALIZADO", "Exitosamente."));
        } catch (Exception e) {
            throw e;
        }
    }

    //Contar la cantidad de usuarios asignados a los proveedores
    public void countCantUsers() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.countCantUsers(usuario);
            if (usuario.getCountPer().equals(usuario.getCantUser())) {
                btnAdd = true;
            } else {
                btnAdd = false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //Validar y cambiar la contraseña del usuario 
    public void validarAndChangePassword() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            if (usuario.getPassPer().equals(passAct)) {
                dao.cambiarContraseña(passNew, usuario.getCodPer());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONTRASEÑA ACTUALIZADA", "Exitosamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña actual no es correcta", ""));
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    //Cambiar el color del background dark o light
    public void cambiarStyleColor() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            if (usuario.getStylePer().equals("light")) {
                dao.cambiarStyleColor("dark", usuario.getCodPer());
                usuario.setStylePer("dark");
            } else if (usuario.getStylePer().equals("dark")) {
                dao.cambiarStyleColor("light", usuario.getCodPer());
                usuario.setStylePer("light");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //Lista los usuarios activos
    public void listarUsersActivos() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            lstUsers = dao.listarUserActive();
        } catch (Exception e) {
            throw e;
        }
    }

    //Lista los usuarios inactivos
    public void listarUsersInactivos() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            lstUsers = dao.listarUserInactive();
        } catch (Exception e) {
            throw e;
        }
    }

    //Lista los usuarios activos del proveedor
    public void listarUsersActivosProv() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            lstUsersprov = dao.listarUserActiveProv(usuario.getCodPer());
        } catch (Exception e) {
            throw e;
        }
    }

    //Inhabilitar usuarios del proveedor
    public void inhabilitarUserProv() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.inhabilitarUser(userProvSelected.getCodPerView());
            listarUsersActivosProv();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, userProvSelected.getNomPerView(), "Inhabilitado."));
        } catch (Exception e) {
            throw e;
        }
    }

    //Habilitar usuarios del proveedor
    public void habilitarUserProv() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.habilitarUser(userProvSelected.getCodPerView());
            listarUsersActivosProv();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, userProvSelected.getNomPerView(), "Habilitado."));
        } catch (Exception e) {
            throw e;
        }
    }

    //Inhabilitar usuarios del Administrador
    public void inhabilitarUserAdm() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.inhabilitarUser(userAdmSelected.getCodPerView());
            listarUsersActivos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, userAdmSelected.getNomPerView(), "Inhabilitado."));
        } catch (Exception e) {
            throw e;
        }
    }

    //Habilitar usuarios del Administrador
    public void habilitarUserAdm() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.habilitarUser(userAdmSelected.getCodPerView());
            listarUsersActivos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, userAdmSelected.getNomPerView(), "Habilitado."));
        } catch (Exception e) {
            throw e;
        }
    }

//------- Getter and Setter Video -------
    public List<User> getLstMiLista() {
        return lstMiLista;
    }

    public void setLstMiLista(List<User> lstMiLista) {
        this.lstMiLista = lstMiLista;
    }

    public User getListaSelected() {
        return listaSelected;
    }

    public void setListaSelected(User listaSelected) {
        this.listaSelected = listaSelected;
    }

    public User getHistorySelected() {
        return historySelected;
    }

    public void setHistorySelected(User historySelected) {
        this.historySelected = historySelected;
    }

    public User getVideoSelected() {
        return videoSelected;
    }

    public void setVideoSelected(User videoSelected) {
        this.videoSelected = videoSelected;
    }

    public List<User> getLstHistorial() {
        return lstHistorial;
    }

    public void setLstHistorial(List<User> lstHistorial) {
        this.lstHistorial = lstHistorial;
    }

    public List<User> getLstMovies() {
        return lstMovies;
    }

    public void setLstMovies(List<User> lstMovies) {
        this.lstMovies = lstMovies;
    }

    public List<User> getLstProxEstrenos() {
        return lstProxEstrenos;
    }

    public void setLstProxEstrenos(List<User> lstProxEstrenos) {
        this.lstProxEstrenos = lstProxEstrenos;
    }

    public List<User> getLstMoviesSimilar() {
        return lstMoviesSimilar;
    }

    public void setLstMoviesSimilar(List<User> lstMoviesSimilar) {
        this.lstMoviesSimilar = lstMoviesSimilar;
    }

    public List<User> getLstCbCat() {
        return lstCbCat;
    }

    public void setLstCbCat(List<User> lstCbCat) {
        this.lstCbCat = lstCbCat;
    }

    public List<User> getLstCbGen() {
        return lstCbGen;
    }

    public void setLstCbGen(List<User> lstCbGen) {
        this.lstCbGen = lstCbGen;
    }

    public List<User> getLstCategoria() {
        return lstCategoria;
    }

    public void setLstCategoria(List<User> lstCategoria) {
        this.lstCategoria = lstCategoria;
    }

    public List<User> getLstMoviesXCat() {
        return lstMoviesXCat;
    }

    public void setLstMoviesXCat(List<User> lstMoviesXCat) {
        this.lstMoviesXCat = lstMoviesXCat;
    }

    public User getCatgSelected() {
        return catgSelected;
    }

    public void setCatgSelected(User catgSelected) {
        this.catgSelected = catgSelected;
    }

    public List<User> getLstBsqMovies() {
        return lstBsqMovies;
    }

    public void setLstBsqMovies(List<User> lstBsqMovies) {
        this.lstBsqMovies = lstBsqMovies;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public boolean isVerifica() {
        return verifica;
    }

    public void setVerifica(boolean verifica) {
        this.verifica = verifica;
    }

//------- Getter and Setter Usuario -------
    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public User getUserProvSelected() {
        return userProvSelected;
    }

    public void setUserProvSelected(User userProvSelected) {
        this.userProvSelected = userProvSelected;
    }

    public User getUserAdmSelected() {
        return userAdmSelected;
    }

    public void setUserAdmSelected(User userAdmSelected) {
        this.userAdmSelected = userAdmSelected;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    public String getPassAct() {
        return passAct;
    }

    public void setPassAct(String passAct) {
        this.passAct = passAct;
    }

    public String getPassNew() {
        return passNew;
    }

    public void setPassNew(String passNew) {
        this.passNew = passNew;
    }

    public String getStyleColor() {
        return styleColor;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }

    public List<User> getLstUsers() {
        return lstUsers;
    }

    public void setLstUsers(List<User> lstUsers) {
        this.lstUsers = lstUsers;
    }

    public List<User> getLstUsersFilter() {
        return lstUsersFilter;
    }

    public void setLstUsersFilter(List<User> lstUsersFilter) {
        this.lstUsersFilter = lstUsersFilter;
    }

    public List<User> getLstUsersprov() {
        return lstUsersprov;
    }

    public void setLstUsersprov(List<User> lstUsersprov) {
        this.lstUsersprov = lstUsersprov;
    }

    public List<User> getLstUsersProvFilter() {
        return lstUsersProvFilter;
    }

    public void setLstUsersProvFilter(List<User> lstUsersProvFilter) {
        this.lstUsersProvFilter = lstUsersProvFilter;
    }

    public boolean isBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(boolean btnAdd) {
        this.btnAdd = btnAdd;
    }

}
