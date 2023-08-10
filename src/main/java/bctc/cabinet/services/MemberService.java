package bctc.cabinet.services;

import bctc.cabinet.models.Member;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public abstract class MemberService<Repository extends JpaRepository<T, Integer>, T extends Member> {
    protected final Repository repository;

    public List<T> findAll(){
        return repository.findAll();
    }

    public T findOne(int id){
        Optional<T> foundMember = repository.findById(id);
        // Handle optional maybe...
        return foundMember.orElse(null);
    }

    @Transactional
    public void save(T member){
        repository.save(member);
    }

    // TODO: this one probably should be an abstract
    //  and need to figure out how to add and delete students for teacher (so abstract, yeah)
    @Transactional
    public void update(int id, T updatedMember){
        updatedMember.setId(id);
        repository.save(updatedMember);
    }

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }
}
