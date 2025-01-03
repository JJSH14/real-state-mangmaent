package com.example.loborems.interfaces;

import com.example.loborems.models.Permission;

import java.util.List;

public interface PermissionDOA {

    public void save(Permission permission);        // Saves a permission
    public void update(Permission permission);     // Updates a permission
    public void delete(Permission permission);     // Deletes a permission
    public List<Permission> getAll();              // Retrieves all permissions
    public Permission findPermission(int id);      // Finds a permission by ID
    public List<Permission> findByRoleId(int roleId); // Finds permissions by a role ID
}
