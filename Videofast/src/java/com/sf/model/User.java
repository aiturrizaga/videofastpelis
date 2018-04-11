package com.sf.model;

public class User {

    //Variables de usuario
    private String codPer, codPerView, codPerReg, codPerV;
    private String userPer, userPerView, userPerReg;
    private String passPer, passPerView, passPerReg;
    private String dniPer, dniPerView;
    private String celPer, celPerView;
    private String tipoPer, tipoPerView, tipoPerReg = "3";
    private String nomPer, nomPerView, nomPerReg;
    private String apePer, apePerView, apePerReg;
    private String emailPer, emailPerView;
    private String stylePer, styPerView;
    private String cantUser, cantUserView, cantUserReg = "0";
    private String createBy, createByView;
    private String estPer, estPerView;
    private String countPer;

    //Variables de videos
    private String codVid, codVidV;
    private String nomVid;
    private String yearVid;
    private String duraVid;
    private String desVid;
    private String ubiVid;
    private String portVid;
    private String imgVid;
    private String tagVid;
    private String codCatVid;
    private String nomCatVid;
    private String codGenVid;
    private String nomGenVid;
    private String genero;
    private String estVid;
    private int contador;
    
    //Variables de categoria
    private String codCat;
    private String nomCat;
    private String imgCat;
    private String rutaCat;
    private String estCat;

    //Variables para ver el historial
    private String codHis, fechaHis, codPerHis;
    //Variables para ver el historial
    private String codLis, codPerLis;

    public String getImgVid() {
        return imgVid;
    }

    public void setImgVid(String imgVid) {
        this.imgVid = imgVid;
    }

    public String getCodPerV() {
        return codPerV;
    }

    public void setCodPerV(String codPerV) {
        this.codPerV = codPerV;
    }

    public String getCodVidV() {
        return codVidV;
    }

    public void setCodVidV(String codVidV) {
        this.codVidV = codVidV;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public String getCodLis() {
        return codLis;
    }

    public void setCodLis(String codLis) {
        this.codLis = codLis;
    }

    public String getCodPerLis() {
        return codPerLis;
    }

    public void setCodPerLis(String codPerLis) {
        this.codPerLis = codPerLis;
    }

    public String getCodPerHis() {
        return codPerHis;
    }

    public void setCodPerHis(String codPerHis) {
        this.codPerHis = codPerHis;
    }

    public String getCodHis() {
        return codHis;
    }

    public void setCodHis(String codHis) {
        this.codHis = codHis;
    }

    public String getFechaHis() {
        return fechaHis;
    }

    public void setFechaHis(String fechaHis) {
        this.fechaHis = fechaHis;
    }

    public String getCodVid() {
        return codVid;
    }

    public void setCodVid(String codVid) {
        this.codVid = codVid;
    }

    public String getNomVid() {
        return nomVid;
    }

    public String getCelPer() {
        return celPer;
    }

    public void setCelPer(String celPer) {
        this.celPer = celPer;
    }

    public void setNomVid(String nomVid) {
        this.nomVid = nomVid;
    }

    public String getYearVid() {
        return yearVid;
    }

    public void setYearVid(String yearVid) {
        this.yearVid = yearVid;
    }

    public String getDuraVid() {
        return duraVid;
    }

    public void setDuraVid(String duraVid) {
        this.duraVid = duraVid;
    }

    public String getDesVid() {
        return desVid;
    }

    public void setDesVid(String desVid) {
        this.desVid = desVid;
    }

    public String getDniPer() {
        return dniPer;
    }

    public void setDniPer(String dniPer) {
        this.dniPer = dniPer;
    }

    public String getUbiVid() {
        return ubiVid;
    }

    public void setUbiVid(String ubiVid) {
        this.ubiVid = ubiVid;
    }

    public String getPortVid() {
        return portVid;
    }

    public void setPortVid(String portVid) {
        this.portVid = portVid;
    }

    public String getTagVid() {
        return tagVid;
    }

    public void setTagVid(String tagVid) {
        this.tagVid = tagVid;
    }

    public String getCodCatVid() {
        return codCatVid;
    }

    public void setCodCatVid(String codCatVid) {
        this.codCatVid = codCatVid;
    }

    public String getNomCatVid() {
        return nomCatVid;
    }

    public void setNomCatVid(String nomCatVid) {
        this.nomCatVid = nomCatVid;
    }

    public String getCodGenVid() {
        return codGenVid;
    }

    public void setCodGenVid(String codGenVid) {
        this.codGenVid = codGenVid;
    }

    public String getNomGenVid() {
        return nomGenVid;
    }

    public void setNomGenVid(String nomGenVid) {
        this.nomGenVid = nomGenVid;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstVid() {
        return estVid;
    }

    public void setEstVid(String estVid) {
        this.estVid = estVid;
    }

    public String getEmailPer() {
        return emailPer;
    }

    public void setEmailPer(String emailPer) {
        this.emailPer = emailPer;
    }

    public String getNomPer() {
        return nomPer;
    }

    public void setNomPer(String nomPer) {
        this.nomPer = nomPer;
    }

    public String getApePer() {
        return apePer;
    }

    public void setApePer(String apePer) {
        this.apePer = apePer;
    }

    public String getCodPer() {
        return codPer;
    }

    public void setCodPer(String codPer) {
        this.codPer = codPer;
    }

    public String getUserPer() {
        return userPer;
    }

    public void setUserPer(String userPer) {
        this.userPer = userPer;
    }

    public String getPassPer() {
        return passPer;
    }

    public void setPassPer(String passPer) {
        this.passPer = passPer;
    }

    public String getTipoPer() {
        return tipoPer;
    }

    public void setTipoPer(String tipoPer) {
        this.tipoPer = tipoPer;
    }

    public String getStylePer() {
        return stylePer;
    }

    public void setStylePer(String stylePer) {
        this.stylePer = stylePer;
    }

    public String getCantUser() {
        return cantUser;
    }

    public void setCantUser(String cantUser) {
        this.cantUser = cantUser;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCodPerView() {
        return codPerView;
    }

    public void setCodPerView(String codPerView) {
        this.codPerView = codPerView;
    }

    public String getUserPerView() {
        return userPerView;
    }

    public void setUserPerView(String userPerView) {
        this.userPerView = userPerView;
    }

    public String getPassPerView() {
        return passPerView;
    }

    public void setPassPerView(String passPerView) {
        this.passPerView = passPerView;
    }

    public String getDniPerView() {
        return dniPerView;
    }

    public void setDniPerView(String dniPerView) {
        this.dniPerView = dniPerView;
    }

    public String getCelPerView() {
        return celPerView;
    }

    public void setCelPerView(String celPerView) {
        this.celPerView = celPerView;
    }

    public String getTipoPerView() {
        return tipoPerView;
    }

    public void setTipoPerView(String tipoPerView) {
        this.tipoPerView = tipoPerView;
    }

    public String getNomPerView() {
        return nomPerView;
    }

    public void setNomPerView(String nomPerView) {
        this.nomPerView = nomPerView;
    }

    public String getApePerView() {
        return apePerView;
    }

    public void setApePerView(String apePerView) {
        this.apePerView = apePerView;
    }

    public String getEmailPerView() {
        return emailPerView;
    }

    public void setEmailPerView(String emailPerView) {
        this.emailPerView = emailPerView;
    }

    public String getStyPerView() {
        return styPerView;
    }

    public void setStyPerView(String styPerView) {
        this.styPerView = styPerView;
    }

    public String getCantUserView() {
        return cantUserView;
    }

    public void setCantUserView(String cantUserView) {
        this.cantUserView = cantUserView;
    }

    public String getCreateByView() {
        return createByView;
    }

    public void setCreateByView(String createByView) {
        this.createByView = createByView;
    }

    public String getEstPer() {
        return estPer;
    }

    public void setEstPer(String estPer) {
        this.estPer = estPer;
    }

    public String getEstPerView() {
        return estPerView;
    }

    public void setEstPerView(String estPerView) {
        this.estPerView = estPerView;
    }

    public String getCodPerReg() {
        return codPerReg;
    }

    public void setCodPerReg(String codPerReg) {
        this.codPerReg = codPerReg;
    }

    public String getUserPerReg() {
        return userPerReg;
    }

    public void setUserPerReg(String userPerReg) {
        this.userPerReg = userPerReg;
    }

    public String getPassPerReg() {
        return passPerReg;
    }

    public void setPassPerReg(String passPerReg) {
        this.passPerReg = passPerReg;
    }

    public String getNomPerReg() {
        return nomPerReg;
    }

    public void setNomPerReg(String nomPerReg) {
        this.nomPerReg = nomPerReg;
    }

    public String getApePerReg() {
        return apePerReg;
    }

    public void setApePerReg(String apePerReg) {
        this.apePerReg = apePerReg;
    }

    public String getTipoPerReg() {
        return tipoPerReg;
    }

    public void setTipoPerReg(String tipoPerReg) {
        this.tipoPerReg = tipoPerReg;
    }

    public String getCantUserReg() {
        return cantUserReg;
    }

    public void setCantUserReg(String cantUserReg) {
        this.cantUserReg = cantUserReg;
    }

    public String getCountPer() {
        return countPer;
    }

    public void setCountPer(String countPer) {
        this.countPer = countPer;
    }

    public String getCodCat() {
        return codCat;
    }

    public void setCodCat(String codCat) {
        this.codCat = codCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String getImgCat() {
        return imgCat;
    }

    public void setImgCat(String imgCat) {
        this.imgCat = imgCat;
    }

    public String getRutaCat() {
        return rutaCat;
    }

    public void setRutaCat(String rutaCat) {
        this.rutaCat = rutaCat;
    }

    public String getEstCat() {
        return estCat;
    }

    public void setEstCat(String estCat) {
        this.estCat = estCat;
    }
}
