package org.example.loan.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.loan.entity.ERole;
import org.example.loan.entity.Role;
import org.example.loan.repository.RoleRepository;
import org.example.loan.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrsave(ERole role) {
        Optional<Role> optionalRole = roleRepository.findByRole(role);
        if(optionalRole.isPresent()) {
            return optionalRole.get();
        }

        Role currentRole = Role.builder()
                .role(role)
                .build();


        return roleRepository.save(currentRole);
    }
}
