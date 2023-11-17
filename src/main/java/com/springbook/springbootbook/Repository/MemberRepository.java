package com.springbook.springbootbook.Repository;

import com.springbook.springbootbook.enetity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
