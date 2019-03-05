package ir.vira.travelagency.managedBean;

import ir.vira.travelagency.model.entity.NaturalPerson;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


//@Transactional
@ViewScoped
@ManagedBean
public class NaturalPersonCrudBean extends BaseCrudBean<NaturalPerson> {
    public NaturalPersonCrudBean() throws IllegalAccessException, InstantiationException {
        super(NaturalPerson.class);
    }
}
