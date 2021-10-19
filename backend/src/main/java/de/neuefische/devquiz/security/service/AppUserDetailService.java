package de.neuefische.devquiz.security.service;

import de.neuefische.devquiz.security.model.AppUser;
import de.neuefische.devquiz.security.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AppUserDetailService implements UserDetailsService {

    private final AppUserRepo appUserRepo;

    @Autowired
    public AppUserDetailService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    //1.variante mit optionals

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<AppUser> optionalAppUser = appUserRepo.findById(username);
//        if (optionalAppUser.isPresent()){
//            return User
//                    .withUsername(username)
//                    .password(optionalAppUser.get().getPassword())
//                    .password(optionalAppUser.get().)
//                    .authorities("user").build();
//        }
//        else { throw new UsernameNotFoundException("Username does not exists: " +username);}
//    }

    // 2.variante mit builder
@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepo.findById(username)
                .map(appUser -> User
                        .withUsername(username)
                        .password(appUser.getPassword())
                        .authorities("user")
                        .build())
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist: "+username));
    }
}
