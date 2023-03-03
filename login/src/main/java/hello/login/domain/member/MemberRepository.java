package hello.login.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save member = {}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        List<Member> all = findAll();
        return all.stream()
            .filter(m -> m.getLoginId().equals(loginId))
            .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // public void update(Long memberId, Member updateParam) {
    //     Member findMember = findById(memberId);
    //     findMember.setItemName(updateParam.getItemName());
    //     findMember.setPrice(updateParam.getPrice());
    //     findMember.setQuantity(updateParam.getQuantity());
    // }

    public void clearStore() {
        store.clear();
    }

    
}
