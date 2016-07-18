package com.quartzx.datacollector.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rex on 2016/7/17.
 */
public class UserData {
    public UserData(){
        _devices = new ArrayList<>();
    }

    private Boolean _success;

    public Boolean getSuccess() {
        return _success;
    }

    public void setSuccess(Boolean success) {
        _success = success;
    }

    private String _name;

    public String getUsername() {
        return _name;
    }

    public void setUsername(String name) {
        _name = name;
    }

    private String _password;

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    private List<String> _devices;

    public List<String> getDevices() {
        return _devices;
    }

    public void setDevices(List<String> devices) {
        _devices = devices;
    }
}
