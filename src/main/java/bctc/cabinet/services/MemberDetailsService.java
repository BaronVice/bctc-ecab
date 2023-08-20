package bctc.cabinet.services;

import bctc.cabinet.models.Member;
import bctc.cabinet.repositories.MemberRepository;
import bctc.cabinet.security.MemberDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
public abstract class MemberDetailsService<D extends MemberDetails<M>, R extends MemberRepository<M>, M extends Member>
        implements UserDetailsService {
//    private final R repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<M> member = repository.findByUsername(username);
//        if (member.isEmpty())
//            throw new UsernameNotFoundException("User not found");
//        return new D(member.get());    }
}
