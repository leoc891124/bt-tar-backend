package com.leoc.springboot.bttar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tar")
public class Tar {

    @Id
    private String id;
    private String year;
    private String month;
    private String areas;
    private String sede;
    private String hallazgo;
    private String typeHallazgo;
    private String tar;
    private String detalle1;
    private String detalle2;
    private String detalle3;
    private String planAccion;
    private String datePlan;
    private String state;
    private String companyId;

    public Tar() {
    }

    public Tar(String id, String year, String month, String areas, String sede,
               String hallazgo, String typeHallazgo, String tar, String detalle1, String detalle2, String detalle3,
               String planAccion, String datePlan,String state, String companyId ) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.areas = areas;
        this.sede = sede;
        this.hallazgo = hallazgo;
        this.typeHallazgo = typeHallazgo;
        this.tar = tar;
        this.detalle1 = detalle1;
        this.detalle2 = detalle2;
        this.detalle3 = detalle3;
        this.planAccion = planAccion;
        this.datePlan = datePlan;
        this.state = state;
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getHallazgo() {
        return hallazgo;
    }

    public void setHallazgo(String hallazgo) {
        this.hallazgo = hallazgo;
    }

    public String getTypeHallazgo() {
        return typeHallazgo;
    }

    public void setTypeHallazgo(String typeHallazgo) {
        this.typeHallazgo = typeHallazgo;
    }

    public String getTar() {
        return tar;
    }

    public void setTar(String tar) {
        this.tar = tar;
    }

    public String getDetalle1() {
        return detalle1;
    }

    public void setDetalle1(String detalle1) {
        this.detalle1 = detalle1;
    }

    public String getDetalle2() {
        return detalle2;
    }

    public void setDetalle2(String detalle2) {
        this.detalle2 = detalle2;
    }

    public String getDetalle3() {
        return detalle3;
    }

    public void setDetalle3(String detalle3) {
        this.detalle3 = detalle3;
    }

    public String getPlanAccion() {
        return planAccion;
    }

    public void setPlanAccion(String planAccion) {
        this.planAccion = planAccion;
    }

    public String getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(String datePlan) {
        this.datePlan = datePlan;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
