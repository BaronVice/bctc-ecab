package bctc.cabinet.repositories;

import bctc.cabinet.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository // ??
public interface MemberRepository<T extends Member> extends JpaRepository<T, Integer> {
    Optional<T> findByEmail(String email);
}
