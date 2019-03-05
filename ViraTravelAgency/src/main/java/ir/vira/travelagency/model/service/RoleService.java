package ir.vira.travelagency.model.service;

import ir.vira.travelagency.model.entity.Role;
import ir.vira.travelagency.model.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleService implements CrudService<Role> {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {

        return roleRepository.save(role);

    }

    @Override
    public List<Role> list() {

        return roleRepository.findAllByDeleteDateIsNullOrderByIdDesc();

    }

    @Override
    public Role remove(Long id) {

        Role temp = roleRepository.getOne(id);
        temp.setDeleteDate(new Date());
        return roleRepository.save(temp);

    }

    @Override
    public Role edit(Role role) {

        return roleRepository.save(role);

    }

    @Override
    public Role get(Long id) {

        return roleRepository.getById(id);

    }
}
