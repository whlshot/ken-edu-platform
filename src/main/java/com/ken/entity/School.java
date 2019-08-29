package com.ken.entity;


/**
 * @author yhq
 * @date 2019/1/17
 */
public class School {

    private Integer id;
    private String schoolName;//学校名称
    private String address;//学校地址
    private String schoolCode;//学校代码
    private String proCode;//省
    private String cityCode;//市
    private String countyCode;//区县
    private String schoolSystem;//学制
    private String schoolSystemDetail;//学制详情
    private String eduStage;//教育阶段
    private String contactName;//联系人
    private String contactPhone;//联系方式
    private String contactEmail;//联系邮件
    private String schoolNature;//办学性质

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getSchoolSystem() {
        return schoolSystem;
    }

    public void setSchoolSystem(String schoolSystem) {
        this.schoolSystem = schoolSystem;
    }

    public String getSchoolSystemDetail() {
        return schoolSystemDetail;
    }

    public void setSchoolSystemDetail(String schoolSystemDetail) {
        this.schoolSystemDetail = schoolSystemDetail;
    }

    public String getEduStage() {
        return eduStage;
    }

    public void setEduStage(String eduStage) {
        this.eduStage = eduStage;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getSchoolNature() {
        return schoolNature;
    }

    public void setSchoolNature(String schoolNature) {
        this.schoolNature = schoolNature;
    }
}
