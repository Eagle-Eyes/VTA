package ir.vira.travelagency.configuration;

import ir.vira.travelagency.model.entity.Account;
import ir.vira.travelagency.model.entity.Role;
import ir.vira.travelagency.model.entity.enumeration.RoleEnum;
import ir.vira.travelagency.model.repository.AccountRepository;
import ir.vira.travelagency.model.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class AppInitializer implements InitializingBean, DisposableBean {//, WebApplicationInitializer {

    private static final Logger logger = LoggerFactory.getLogger(AppInitializer.class);

    private Environment env;
    private TransactionTemplate transactionTemplate;
    private RoleRepository roleRepository;
    private AccountRepository accountRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppInitializer(Environment env, TransactionTemplate transactionTemplate, RoleRepository roleRepository, AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.env = env;
        this.transactionTemplate = transactionTemplate;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void afterPropertiesSet() {

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                updateApplicationRoles();
                initializeSystemAccount();
            }
        });

    }

    private void updateApplicationRoles() {
        logger.trace("update app roleEnums ...");
        Set<RoleEnum> roleEnums = new HashSet(Arrays.asList(RoleEnum.values()));
        for (RoleEnum roleName : roleEnums) {
            if (!roleRepository.existsByDisplayNameIgnoreCase(roleName.name())) {
                Role role = new Role();
                role.setDisplayName(roleName.name());
                logger.trace(String.format("Role '%s' was added.", roleName.name()));
                roleRepository.save(role);
            }
        }
    }

    private void initializeSystemAccount() {

        String appName = env.getProperty("spring.application.name");
        String appDomainName = env.getProperty("spring.application.domain");

        Set<RoleEnum> roleEnums = new HashSet(Arrays.asList(RoleEnum.values()));

        for (RoleEnum roleEnum : roleEnums) {

            Role role = roleRepository.findByDisplayNameIgnoreCase(roleEnum.name());

            if (!accountRepository.existsByAccountNameIgnoreCase(role.getDisplayName())) {

                String adminDisplayName = String.format("%s [%s]", role.getDisplayName(), appName.toUpperCase());
                String accountName = role.getDisplayName().replace(" ", "_");
                String email = role.getDisplayName().replace(" ", "_").concat("@").concat(appDomainName);

                Account account = new Account();
                account.setAccountName(accountName);
                account.setEmail(email);
                account.setDisplayName(adminDisplayName);
                account.setPassword(bCryptPasswordEncoder.encode(role.getDisplayName()));
                account.setRoles(new HashSet<>());
                account.getRoles().add(role);

                accountRepository.save(account);
                logger.trace(String.format("Account '%s' was added.", account.getDisplayName()));

            }
        }
    }

    @Override
    public void destroy() throws Exception {

    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
//        root.register(AppConfiguration.class);
//        servletContext.addListener(new ContextLoaderListener(root));
//
//    }
}
