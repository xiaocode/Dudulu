package com.dudulu.app.db;

/**
 * Created by Vincent on 4/14/14.
 */
public class Game extends BaseGame {

    private String download_url;
    private int version_code;
    private String version_name;
    private String package_name;
    private String package_size;
    private int updated_time;
    private String intro;
    private String intro_gallery;
    private int company;
    private int localization;
    private int min_SDK;
    private int target_SDK;

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_size() {
        return package_size;
    }

    public void setPackage_size(String package_size) {
        this.package_size = package_size;
    }

    public int getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(int updated_time) {
        this.updated_time = updated_time;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro_gallery() {
        return intro_gallery;
    }

    public void setIntro_gallery(String intro_gallery) {
        this.intro_gallery = intro_gallery;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    public int getLocalization() {
        return localization;
    }

    public void setLocalization(int localization) {
        this.localization = localization;
    }

    public int getMin_SDK() {
        return min_SDK;
    }

    public void setMin_SDK(int min_SDK) {
        this.min_SDK = min_SDK;
    }

    public int getTarget_SDK() {
        return target_SDK;
    }

    public void setTarget_SDK(int target_SDK) {
        this.target_SDK = target_SDK;
    }
}
