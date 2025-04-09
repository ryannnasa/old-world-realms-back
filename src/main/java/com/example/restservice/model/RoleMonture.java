package com.example.restservice.model;

public class RoleMonture {
    private int idRoleMonture;
    private String roleMonture;

    public RoleMonture() {
    }

    public RoleMonture(int idRoleMonture, String roleMonture) {
        this.idRoleMonture = idRoleMonture;
        this.roleMonture = roleMonture;
    }

    public int getIdRoleMonture() {return idRoleMonture;}
    public void setIdRoleMonture(int idRoleMonture) {this.idRoleMonture = idRoleMonture;}

    public String getRoleMonture() {return roleMonture;}
    public void setRoleMonture(String roleMonture) {this.roleMonture = roleMonture;}
}
