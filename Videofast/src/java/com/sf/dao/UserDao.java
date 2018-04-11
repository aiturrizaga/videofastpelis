package com.sf.dao;

import com.sf.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao {

    /*------------------ Metodos para Usuario ------------------*/
    //INICIAR SESION (Loguearse)
    public User acceder(String usu, String pwd) throws Exception {
        this.Conexion();
        User user = null;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Personas WHERE (userPer like ? OR emailPer like ?) AND passPer like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, usu);
            ps.setString(2, usu);
            ps.setString(3, pwd);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setCodPer(rs.getString("codPer"));
                user.setUserPer(rs.getString("userPer"));
                user.setPassPer(rs.getString("passPer"));
                user.setDniPer(rs.getString("dniPer"));
                user.setCelPer(rs.getString("celPer"));
                user.setNomPer(rs.getString("nomPer"));
                user.setApePer(rs.getString("apePer"));
                user.setEmailPer(rs.getString("emailPer"));
                user.setTipoPer(rs.getString("tipoPer"));
                user.setStylePer(rs.getString("stylePer"));
                user.setCreateBy(rs.getString("createBy"));
                user.setCantUser(rs.getString("cantUser"));
                user.setEstPer(rs.getString("estPer"));
            }
            return user;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    //Actualizar datos del usuario
    public void updateUser(User us) throws Exception {
        this.Conexion();
        try {
            String sql = "UPDATE Personas SET nomPer=?,apePer=?,dniPer=?,emailPer=?,celPer=? WHERE codPer = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, us.getNomPer());
            ps.setString(2, us.getApePer());
            ps.setString(3, us.getDniPer());
            ps.setString(4, us.getEmailPer());
            ps.setString(5, us.getCelPer());
            ps.setString(6, us.getCodPer());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Agregar un nuevo usuario
    public void addUser(User us) throws SQLException {
        this.Conexion();
        try {
            String sql = "INSERT INTO Personas(codPer,nomPer,apePer,userPer,passPer,stylePer,createBy,cantUser,tipoPer,estPer)"
                    + " VALUES(null,?,?,?,?,'light',?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, us.getNomPerReg());
            ps.setString(2, us.getApePerReg());
            ps.setString(3, us.getUserPerReg());
            ps.setString(4, us.getPassPerReg());
            ps.setString(5, us.getCodPer());
            ps.setString(6, us.getCantUserReg());
            ps.setString(7, us.getTipoPerReg());
            ps.setString(8, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void updateOtherUser(User us) throws Exception {
        this.Conexion();
        try {
            String sql = "UPDATE Personas SET nomPer=?,apePer=?,userPer=?,cantUser=? WHERE codPer = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, us.getNomPerView());
            ps.setString(2, us.getApePerView());
            ps.setString(3, us.getUserPerView());
            ps.setString(4, us.getCantUserView());
            ps.setString(5, us.getCodPerView());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Cambiar la contraseña del usuario
    public void cambiarContraseña(String passNew, String codPer) throws Exception {
        this.Conexion();
        try {
            String sql = "UPDATE Personas set passPer = ? WHERE codPer = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, passNew);
            ps.setString(2, codPer);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Contar la cantidad de usuarios asignados a otros usuarios
    public void countCantUsers(User user) throws Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT COUNT(createBy) as countPer FROM Personas WHERE createBy = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, user.getCodPer());
            rs = ps.executeQuery();
            rs.next();
            user.setCountPer(rs.getString("countPer"));
        } catch (SQLException e) {
            throw e;
        }
    }

    //Cambiar el stylo del usuario dark o light
    public void cambiarStyleColor(String stylePer, String codPer) throws Exception {
        this.Conexion();
        try {
            String sql = "UPDATE Personas set stylePer = ? WHERE codPer = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, stylePer);
            ps.setString(2, codPer);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Listar usuarios Activos
    public List<User> listarUserActive() throws Exception {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM Personas";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            User us;
            while (rs.next()) {
                us = new User();
                us.setCodPerView(rs.getString("codPer"));
                us.setNomPerView(rs.getString("nomPer"));
                us.setApePerView(rs.getString("apePer"));
                us.setDniPerView(rs.getString("dniPer"));
                us.setEmailPerView(rs.getString("emailPer"));
                us.setUserPerView(rs.getString("userPer"));
                us.setCelPerView(rs.getString("celPer"));
                us.setCreateByView(rs.getString("createBy"));
                us.setCantUserView(rs.getString("cantUser"));
                us.setTipoPerView(rs.getString("tipoPer"));
                us.setEstPerView(rs.getString("estPer"));
                lista.add(us);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    //Listar usuarios inactivos (NO USADO)
    public List<User> listarUserInactive() throws Exception {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM Personas WHERE estPer = 'I'";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            User us;
            while (rs.next()) {
                us = new User();
                us.setCodPerView(rs.getString("codPer"));
                us.setNomPerView(rs.getString("nomPer"));
                us.setApePerView(rs.getString("apePer"));
                us.setDniPerView(rs.getString("dniPer"));
                us.setEmailPerView(rs.getString("emailPer"));
                us.setUserPerView(rs.getString("userPer"));
                us.setCelPerView(rs.getString("celPer"));
                us.setCreateByView(rs.getString("createBy"));
                us.setCantUserView(rs.getString("cantUser"));
                us.setTipoPerView(rs.getString("tipoPer"));
                us.setEstPerView(rs.getString("estPer"));
                lista.add(us);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    //Listar usuarios Activos del proveedor
    public List<User> listarUserActiveProv(String codPer) throws Exception {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM Personas WHERE createBy = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codPer);
            rs = ps.executeQuery();
            lista = new ArrayList();
            User us;
            while (rs.next()) {
                us = new User();
                us.setCodPerView(rs.getString("codPer"));
                us.setNomPerView(rs.getString("nomPer"));
                us.setApePerView(rs.getString("apePer"));
                us.setDniPerView(rs.getString("dniPer"));
                us.setEmailPerView(rs.getString("emailPer"));
                us.setUserPerView(rs.getString("userPer"));
                us.setCelPerView(rs.getString("celPer"));
                us.setCreateByView(rs.getString("createBy"));
                us.setCantUserView(rs.getString("cantUser"));
                us.setTipoPerView(rs.getString("tipoPer"));
                us.setEstPerView(rs.getString("estPer"));
                lista.add(us);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    //Inhabilitar usuarios
    public void inhabilitarUser(String codPer) throws Exception {
        this.Conexion();
        try {
            String sql = "UPDATE Personas SET estPer = 'I' WHERE codPer = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codPer);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Habilitar usuarios
    public void habilitarUser(String codPer) throws Exception {
        this.Conexion();
        try {
            String sql = "UPDATE Personas SET estPer = 'A' WHERE codPer = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codPer);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    /*------------------ Metodos para Videos ------------------*/
    //LISTA DE VIDEOS DISPONIBLES (estVid = 'A')
    public List<User> listarVideos() throws Exception {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_LIST_VIDEOS";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            User mov;
            while (rs.next()) {
                mov = new User();
                mov.setCodVid(rs.getString("codvideo"));
                mov.setNomVid(rs.getString("nomvideo"));
                mov.setYearVid(rs.getString("fechavid"));
                mov.setDuraVid(rs.getString("duravideo"));
                mov.setDesVid(rs.getString("desvideo"));
                mov.setUbiVid(rs.getString("ubivideo"));
                mov.setPortVid(rs.getString("portada"));
                mov.setImgVid(rs.getString("imgvideo"));
                mov.setTagVid(rs.getString("tagvideo"));
                mov.setCodCatVid(rs.getString("codcategoria"));
                mov.setNomCatVid(rs.getString("nomcategoria"));
                mov.setCodGenVid(rs.getString("codgenero"));
                mov.setNomGenVid(rs.getString("nomgenero"));
                mov.setGenero(rs.getString("genero"));
                mov.setEstVid(rs.getString("estvideo"));
                lista.add(mov);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    //Lista de videos parecidos al que se esta viendo
    public List<User> listarVideoSimilar(String cc, String cv) throws Exception {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_LIST_VIDEOS WHERE codcategoria = ? AND estvideo = 'A' AND codvideo != ? LIMIT 10";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, cc);
            ps.setString(2, cv);
            rs = ps.executeQuery();
            lista = new ArrayList();
            User mov;
            while (rs.next()) {
                mov = new User();
                mov.setCodVid(rs.getString("codvideo"));
                mov.setNomVid(rs.getString("nomvideo"));
                mov.setYearVid(rs.getString("fechavid"));
                mov.setDuraVid(rs.getString("duravideo"));
                mov.setDesVid(rs.getString("desvideo"));
                mov.setUbiVid(rs.getString("ubivideo"));
                mov.setPortVid(rs.getString("portada"));
                mov.setImgVid(rs.getString("imgvideo"));
                mov.setTagVid(rs.getString("tagvideo"));
                mov.setCodCatVid(rs.getString("codcategoria"));
                mov.setNomCatVid(rs.getString("nomcategoria"));
                mov.setCodGenVid(rs.getString("codgenero"));
                mov.setNomGenVid(rs.getString("nomgenero"));
                mov.setGenero(rs.getString("genero"));
                mov.setEstVid(rs.getString("estvideo"));
                lista.add(mov);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    //LISTA DE VIDEOS PROXIMOS A ESTRENARSE (estVid = 'P')(NO USADO)
    public List<User> listarProximosEstrenos() throws Exception {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_LIST_PROXIMOS";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            User mov;
            while (rs.next()) {
                mov = new User();
                mov.setCodVid(rs.getString("codvideo"));
                mov.setNomVid(rs.getString("nomvideo"));
                mov.setPortVid(rs.getString("portada"));
                mov.setCodCatVid(rs.getString("codcategoria"));
                mov.setNomCatVid(rs.getString("nomcategoria"));
                mov.setCodGenVid(rs.getString("codgenero"));
                mov.setNomGenVid(rs.getString("nomgenero"));
                mov.setGenero(rs.getString("genero"));
                mov.setEstVid(rs.getString("estvideo"));
                lista.add(mov);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }
    
    //Listar videos por busqueda del nombre
    public List<User> listarBusquedaVideo(String nomVid) throws Exception {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_LIST_VIDEOS WHERE nomvideo like ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, "%" + nomVid + "%");
            rs = ps.executeQuery();
            lista = new ArrayList();
            User bsq;
            while(rs.next()){
                bsq = new User();
                bsq.setCodVid(rs.getString("codvideo"));
                bsq.setNomVid(rs.getString("nomvideo"));
                bsq.setYearVid(rs.getString("fechavid"));
                bsq.setDuraVid(rs.getString("duravideo"));
                bsq.setDesVid(rs.getString("desvideo"));
                bsq.setUbiVid(rs.getString("ubivideo"));
                bsq.setPortVid(rs.getString("portada"));
                bsq.setImgVid(rs.getString("imgvideo"));
                bsq.setTagVid(rs.getString("tagvideo"));
                bsq.setCodCatVid(rs.getString("codcategoria"));
                bsq.setNomCatVid(rs.getString("nomcategoria"));
                bsq.setCodGenVid(rs.getString("codgenero"));
                bsq.setNomGenVid(rs.getString("nomgenero"));
                bsq.setGenero(rs.getString("genero"));
                bsq.setEstVid(rs.getString("estvideo"));
                lista.add(bsq);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    //LISTA DE CATEGORIAS CON ESTADO = 'A'
    public List<User> listarCategorias() throws Exception {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM Categoria WHERE estCat = 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            User cat;
            while (rs.next()) {
                cat = new User();
                cat.setCodCat(rs.getString("codCat"));
                cat.setNomCat(rs.getString("nomCat"));
                cat.setImgCat(rs.getString("imgCat"));
                cat.setRutaCat(rs.getString("rutaCat"));
                cat.setEstCat(rs.getString("estCat"));
                lista.add(cat);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    //LISTA DE VIDEOS POR CODIGO DE CATEGORIAS
    public List<User> listarVideosXCat(String codCat) throws SQLException {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_LIST_VIDEOS WHERE codcategoria = ? AND estvideo = 'A'";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codCat);
            rs = ps.executeQuery();
            lista = new ArrayList();
            User cat;
            while (rs.next()) {
                cat = new User();
                cat.setCodVid(rs.getString("codvideo"));
                cat.setNomVid(rs.getString("nomvideo"));
                cat.setYearVid(rs.getString("fechavid"));
                cat.setDuraVid(rs.getString("duravideo"));
                cat.setDesVid(rs.getString("desvideo"));
                cat.setUbiVid(rs.getString("ubivideo"));
                cat.setPortVid(rs.getString("portada"));
                cat.setImgVid(rs.getString("imgvideo"));
                cat.setTagVid(rs.getString("tagvideo"));
                cat.setCodCatVid(rs.getString("codcategoria"));
                cat.setNomCatVid(rs.getString("nomcategoria"));
                cat.setCodGenVid(rs.getString("codgenero"));
                cat.setNomGenVid(rs.getString("nomgenero"));
                cat.setGenero(rs.getString("genero"));
                cat.setEstVid(rs.getString("estvideo"));
                lista.add(cat);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    //AGREGAR LOS VIDEOS VISTOS AL HISTORIAL
    public void agregarHistorial(String codper, String codvid) throws Exception {
        this.Conexion();
        try {
            String sql = "INSERT INTO Historial(codHis,fechaHis,codPer,codVid) SELECT null,now(),?,? FROM dual\n"
                    + "WHERE NOT EXISTS (SELECT codPer,codVid FROM Historial WHERE codPer = ? AND codVid = ? LIMIT 1);";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codper);
            ps.setString(2, codvid);
            ps.setString(3, codper);
            ps.setString(4, codvid);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Listar el historial del usuario
    public List<User> listarHistorial(String codPer) throws SQLException {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_LIST_HISTORIAL WHERE codigoper like ? ORDER BY fechahis desc";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, codPer);
            rs = ps.executeQuery();
            lista = new ArrayList();
            User mov;
            while (rs.next()) {
                mov = new User();
                mov.setCodHis(rs.getString("codigohis"));
                mov.setFechaHis(rs.getString("fechahis"));
                mov.setCodPerHis(rs.getString("codigoper"));
                mov.setCodVid(rs.getString("codigovid"));
                mov.setNomVid(rs.getString("nomvideo"));
                mov.setYearVid(rs.getString("fechavid"));
                mov.setDuraVid(rs.getString("duravideo"));
                mov.setDesVid(rs.getString("desvideo"));
                mov.setUbiVid(rs.getString("ubivideo"));
                mov.setPortVid(rs.getString("portada"));
                mov.setImgVid(rs.getString("imgvideo"));
                mov.setTagVid(rs.getString("tagvideo"));
                mov.setCodCatVid(rs.getString("codcategoria"));
                mov.setNomCatVid(rs.getString("nomcategoria"));
                mov.setCodGenVid(rs.getString("codgenero"));
                mov.setNomGenVid(rs.getString("nomgenero"));
                mov.setGenero(rs.getString("genero"));
                mov.setEstVid(rs.getString("estvideo"));
                lista.add(mov);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    //Eliminar historial seleccionado del usuario
    public void eliminarHistorial(String codHis, String codPer) throws Exception {
        this.Conexion();
        try {
            String sql = "DELETE FROM Historial WHERE codHis = ? and codPer = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codHis);
            ps.setString(2, codPer);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Eliminar todo el historial del usuario
    public void eliminarTodoHistorial(String codPer) throws Exception {
        this.Conexion();
        try {
            String sql = "DELETE FROM Historial WHERE codPer = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codPer);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Agregar un video a la Lista del usuario
    public void agregarAmiLista(String codper, String codvid) throws Exception {
        this.Conexion();
        try {
            String sql = "INSERT INTO Lista(codList,codPer,codVid) VALUES(null,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codper);
            ps.setString(2, codvid);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Listar la Lista de videos guardados del usuario
    public List<User> listarMiLista(String codPer) throws SQLException {
        List<User> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_LIST_MILISTA WHERE codigoper like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, codPer);
            rs = ps.executeQuery();
            lista = new ArrayList();
            User mov;
            while (rs.next()) {
                mov = new User();
                mov.setCodLis(rs.getString("codigolis"));
                mov.setCodPerLis(rs.getString("codigoper"));
                mov.setCodVid(rs.getString("codigovid"));
                mov.setNomVid(rs.getString("nomvideo"));
                mov.setYearVid(rs.getString("fechavid"));
                mov.setDuraVid(rs.getString("duravideo"));
                mov.setDesVid(rs.getString("desvideo"));
                mov.setUbiVid(rs.getString("ubivideo"));
                mov.setPortVid(rs.getString("portada"));
                mov.setImgVid(rs.getString("imgvideo"));
                mov.setTagVid(rs.getString("tagvideo"));
                mov.setCodCatVid(rs.getString("codcategoria"));
                mov.setNomCatVid(rs.getString("nomcategoria"));
                mov.setCodGenVid(rs.getString("codgenero"));
                mov.setNomGenVid(rs.getString("nomgenero"));
                mov.setGenero(rs.getString("genero"));
                mov.setEstVid(rs.getString("estvideo"));
                lista.add(mov);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    //Eliminar video guardado de la Lista del usuario
    public void eliminarMiLista(String codLis, String codPer) throws Exception {
        this.Conexion();
        try {
            String sql = "DELETE FROM Lista WHERE codList = ? and codPer = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codLis);
            ps.setString(2, codPer);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Code blank
    public boolean verificarMiLista(String codper, String codvid) throws Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT COUNT(codList) AS contador FROM Lista WHERE codPer like ? and codVid like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, codper);
            ps.setString(2, codvid);
            rs = ps.executeQuery();
            rs.next();
            int valor = rs.getInt("contador");
            return valor == 0;
        } catch (SQLException e) {
            throw e;
        }
    }

    //Enlazar un nuevo video para mostrarlo en la web
    public void enlazarVideo(User mov) throws Exception {
        this.Conexion();
        try {
            String sql = "INSERT INTO Videos(codVid,nomVid,yearVid,duraVid,desVid,ubiVid,portVid,tagVid,codCat,codGen,estVid) VALUES(null,?,?,?,?,?,?,'N',?,?,'A')";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, mov.getNomVid());
            ps.setString(2, mov.getYearVid());
            ps.setString(3, mov.getDuraVid());
            ps.setString(4, mov.getDesVid());
            ps.setString(5, mov.getUbiVid());
            ps.setString(6, mov.getPortVid());
            ps.setString(7, mov.getCodCatVid());
            ps.setString(8, mov.getCodGenVid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    //Busca el codigo de lo que se ha ingresado desde el autocomplete
    public String leerVideo(String a) throws Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT codVid FROM Videos WHERE nomVid = ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("codVid");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    //Autocompleta lo que el usuario escribe
    public List<String> queryAutoCompleteVideo(String a) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> lista;
        try {
            String sql = "SELECT nomVid FROM Videos WHERE nomVid LIKE UPPER(?) AND estVid = 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getString("nomVid"));
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    //Listar combobox de categorias
    public ArrayList<User> listaCbCategorias() throws Exception {
        try {
            ArrayList<User> lista = new ArrayList<>();
            ResultSet rs;
            this.Conexion();
            String sql = "SELECT codCat,nomCat FROM Categoria WHERE estCat = 'A'";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User cat = new User();
                cat.setCodCatVid(rs.getString("codCat"));
                cat.setNomCatVid(rs.getString("nomCat"));
                lista.add(cat);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    //Listar combobox de categorias
    public ArrayList<User> listaCbGeneros() throws Exception {
        try {
            ArrayList<User> lista = new ArrayList<>();
            ResultSet rs;
            this.Conexion();
            String sql = "SELECT codGen,nomGen FROM Genero WHERE estGen = 'A'";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User gen = new User();
                gen.setCodGenVid(rs.getString("codGen"));
                gen.setNomGenVid(rs.getString("nomGen"));
                lista.add(gen);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
