package org.example.loan.service;

import org.example.loan.entity.Enum.ERole;
import org.example.loan.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
