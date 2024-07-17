package org.example.loan.service;

import org.example.loan.entity.ERole;
import org.example.loan.entity.Role;

public interface RoleService {
    Role getOrsave(ERole role);
}
