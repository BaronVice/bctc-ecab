package bctc.cabinet.services;

import bctc.cabinet.models.Member;
import bctc.cabinet.repositories.MemberRepository;
import bctc.cabinet.util.ServiceError;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
public abstract class MemberService<R extends MemberRepository<M>, M extends Member> {
    protected final R repository;

    @Transactional(readOnly = true)
    public List<M> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public M findOne(int id, ServiceError error){
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException(error.toString()));
    }

    @Transactional(readOnly = true)
    public M findByEmail(String email, ServiceError error){
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(error.toString()));
    }

    @Transactional
    public void save(M member){
        repository.save(member);
    }

    @Transactional
    public void update(int id, M updatedMember){
        updatedMember.setId(id);
        repository.save(updatedMember);
    }

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }
}
